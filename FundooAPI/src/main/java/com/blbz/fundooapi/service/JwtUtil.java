package com.blbz.fundooapi.service;

import com.blbz.fundooapi.entiry.UserInfo;
import com.blbz.fundooapi.exception.InvalidUserException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public interface JwtUtil {
    String generateJwt(String userEmail,String url);
    String generateJwt(String userEmail,int expire,String url);
    UserInfo validateHeader(String jwtHeader) throws InvalidUserException;
    boolean isValid();

    String userName();

    JwtUtil loadJwt(String token);
    Claims getClaims();
}
