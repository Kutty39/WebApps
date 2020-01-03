package com.blbz.fundooapi.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String encoder(String str) {
        return encoder.encode(str);
    }

    public static boolean passwordMatcher(String dtoPas,String dbPas){
        return encoder.matches(dtoPas,dbPas);
    }
}
