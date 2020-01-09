package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.LoginDto;
import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.entiry.UserInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    String forgotPasswordMail(String email);
    void updatePassword(String jwt,String pas);

    List<UserInfo> getAllUser(HttpServletRequest httpServletRequest);
}
