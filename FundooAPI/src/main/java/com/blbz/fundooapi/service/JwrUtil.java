package com.blbz.fundooapi.service;

import com.blbz.fundooapi.responce.JwtResponce;

public interface JwrUtil {
    JwtResponce generateJwt(String userEmail);

    boolean isValid();

    String userName();
}
