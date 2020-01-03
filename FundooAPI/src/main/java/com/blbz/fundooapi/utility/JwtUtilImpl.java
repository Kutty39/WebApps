package com.blbz.fundooapi.utility;

import com.blbz.fundooapi.responce.JwtResponce;
import com.blbz.fundooapi.service.JwrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource(value = "jwt.properties", ignoreResourceNotFound = true)
@Getter
public class JwtUtilImpl implements JwrUtil {
    @Value("${jwt.expiry.time.sec}")
    private long EXPIRY_TIME;
    @Value("${jwt.secret}")
    private String MY_KEY;

    private   String userEmail = null;
    private  boolean isValid = false;


    public JwtUtilImpl() {
    }

    public JwtUtilImpl(String token) {
        Claims claims = Jwts.parser().setSigningKey(MY_KEY).parseClaimsJws(token).getBody();
        userEmail = claims.getSubject();
        isValid = claims.getExpiration().before(new Date());
    }

    @Override
    public JwtResponce generateJwt(String userEmail) {
        return new JwtResponce("Login Successfull",200,Jwts.builder()
                .setClaims(null)
                .setSubject(userEmail)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .signWith(SignatureAlgorithm.HS512, MY_KEY).compact());
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String userName() {
        return userEmail;
    }
}