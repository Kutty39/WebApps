package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.RegisterDto;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void registerUser(RegisterDto registerDto);
    boolean checkEmail(String email);
}
