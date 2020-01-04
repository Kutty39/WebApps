package com.blbz.fundooapi.service;

import org.springframework.stereotype.Component;

@Component
public interface JwrUtil {
    String generateJwt(String userEmail);
    String generateJwt(String userEmail,int expire);

    boolean isValid();

    String userName();

    void loadJwt(String token);
}
