package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.LoginDto;
import com.blbz.fundooapi.dto.MsgDto;
import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.entiry.UserInfo;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public interface UserService {
    void registerUser(RegisterDto registerDto);
    boolean checkEmail(String email);
    UserInfo getUser(String useremail);
    boolean passwordMatcher(LoginDto loginDto);
    void sendActivationMail(MsgDto msgDto) throws MessagingException;
}
