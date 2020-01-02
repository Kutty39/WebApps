package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.entiry.UserInfo;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void registerUser(RegisterDto registerDto);
    boolean checkEmail(String email);
    UserInfo getUser(String useremail);
}
