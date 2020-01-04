package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.dto.LoginDto;
import com.blbz.fundooapi.dto.MsgDto;
import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.entiry.UserInfo;
import com.blbz.fundooapi.entiry.UserStatus;
import com.blbz.fundooapi.repository.UserRepo;
import com.blbz.fundooapi.service.Publisher;
import com.blbz.fundooapi.service.UserService;
import com.blbz.fundooapi.service.UserStatusService;
import com.blbz.fundooapi.utility.Util;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDate;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private ModelMapper mapper = new ModelMapper();
    private UserRepo userRepo;
    private UserStatusService userStatusService;
    private Util util;
    private Publisher publisher;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserStatusService userStatusService, Util util, Publisher publisher) {
        this.userRepo = userRepo;
        this.userStatusService = userStatusService;
        this.util = util;
        this.publisher = publisher;
    }


    @Override
    public void registerUser(RegisterDto registerDto) {
        registerDto.setPas(util.encoder(registerDto.getPas()));
        UserInfo userInfo = mapper.map(registerDto, UserInfo.class);
        UserStatus status = userStatusService.getByStatus("Active");
        userInfo.setUserStatus(status);
        userInfo.setUserCreatedOn(LocalDate.now());
        log.info(status.toString());
        log.info(userInfo.toString());
        userRepo.save(userInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkEmail(String email) {
        return userRepo.findByEid(email) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUser(String useremail) {
        return userRepo.findByEid(useremail);
    }

    @Override
    public boolean passwordMatcher(LoginDto loginDto) {
        UserInfo userInfo = userRepo.findByEid(loginDto.getUsername());
        if (userInfo != null) {
            return util.passwordMatcher(loginDto.getPassword(), userInfo.getPas());
        } else {
            return false;
        }
    }

    @Override
    public void sendActivationMail(MsgDto msgDto) throws MessagingException {
        msgDto.setMsg("<html lang=\"en\">\n" +
                "<body>\n" +
                "<p>\n" +
                "    Hi {name},\n" +
                "    <br>\n" +
                "        Thanks for register with us!!.\n" +
                "    please click the below button to activate your account.\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <a type=\"button\" style=\"background-color: aqua;border: black;padding: 0.5rem\" href=\"http://localhost:8080/activate?tk={jwt}\">Activate</a>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <b>\n" +
                "    Thanks,\n" +
                "    <br>\n" +
                "    BLBZ, Bangalore.\n" +
                "    </b>\n" +
                "</p>\n" +
                "</body>\n" +
                "</html>".replace("{name}", msgDto.getName()).replace("{jwt}", msgDto.getJwt()));

        publisher.produceMsg(msgDto);
    }

}
