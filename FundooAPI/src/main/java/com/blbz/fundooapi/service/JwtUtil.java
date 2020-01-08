package com.blbz.fundooapi.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public interface JwtUtil {
    String generateJwt(String userEmail,String url);
    String generateJwt(String userEmail,int expire,String url);

    boolean isValid();

    String userName();

    JwtUtil loadJwt(String token);
    Claims getClaims();
}
