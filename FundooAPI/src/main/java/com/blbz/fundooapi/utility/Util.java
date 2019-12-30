package com.blbz.fundooapi.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {
    public static String encoder(String str) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(str);
    }
}
