package com.blbz.fundooapi.utility;

import com.blbz.fundooapi.dto.BlockedJwt;
import com.blbz.fundooapi.dto.MsgDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class Util {
    private JavaMailSender sender;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public Util(JavaMailSender sender) {
        this.sender = sender;
    }

    public String encoder(String str) {
        return encoder.encode(str);
    }

    public boolean passwordMatcher(String dtoPas, String dbPas) {

        return encoder.matches(dtoPas, dbPas);
    }

    public void sendEmail(MsgDto msgDto) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(msgDto.getEmail());
        helper.setText(msgDto.getMsg(), true);
        helper.setSubject(msgDto.getSubject());
        sender.send(message);
    }

    public String getMsg() {
        try {
            File file=ResourceUtils.getFile("classpath:files/emailMsg.txt");
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            return null;
        }
    }

    @Bean
    public BlockedJwt getBlockedJwt() {
        Gson gson = new Gson();
        try {
            File file =ResourceUtils.getFile("classpath:files/block.json");
            if (!file.createNewFile()) {
                String blockedJwtString = new String(Files.readAllBytes(file.toPath()));
                System.out.println(blockedJwtString);
                return gson.fromJson(blockedJwtString, BlockedJwt.class);
            } else {
                return new BlockedJwt();
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
            return new BlockedJwt();
        }

    }

    public void writeBlockJwt(BlockedJwt blockedJwt) {
        Gson gson = new Gson();
        try {
            File file=ResourceUtils.getFile("classpath:files/block.json");
            FileWriter writer = new FileWriter(file);
            System.out.println(gson.toJson(blockedJwt));
            writer.write(gson.toJson(blockedJwt));
            writer.flush();
            String blockedJwtString = new String(Files.readAllBytes(file.toPath()));
            System.out.println(blockedJwtString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
