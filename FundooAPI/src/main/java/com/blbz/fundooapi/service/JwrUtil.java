package com.blbz.fundooapi.service;

import com.blbz.fundooapi.model.AuthenticationResponce;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwrUtil {
    AuthenticationResponce generateJwt(UserDetails userDetailser);
}
