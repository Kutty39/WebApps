package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.LoginDto;
import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.entiry.UserInfo;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    String registerUser(RegisterDto registerDto);
    boolean checkEmail(String email);
    UserInfo getUser(String useremail);
    boolean passwordMatcher(LoginDto loginDto);
    String sendActivationMail( String eid, String msgDto);
    String userActivate(String jwt);

    String loginUser(String username);

    void blockedJwt(String jwt);
}
