package com.blbz.fundooapi.utility;

import com.blbz.fundooapi.dto.MsgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Util {

    @Autowired
    private JavaMailSender sender;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
        helper.setText(msgDto.getMsg(),true);
        helper.setSubject(msgDto.getSubject());
        sender.send(message);
    }

}
