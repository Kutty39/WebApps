package com.blbz.fundooapi.utility;

import com.blbz.fundooapi.service.JwrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@PropertySource(value = "jwt.properties", ignoreResourceNotFound = true)
@Getter
@NoArgsConstructor
public class JwtUtilImpl implements JwrUtil {
    @Value("${jwt.expiry.time.sec}")
    private long EXPIRY_TIME;
    @Value("${jwt.secret}")
    private String MY_KEY;

    private   String userEmail = null;
    private  boolean isValid = false;
    private Claims claims;

    @Override
    public String generateJwt(String userEmail) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(userEmail)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .signWith(SignatureAlgorithm.HS512, MY_KEY).compact();
    }

    @Override
    public String generateJwt(String userEmail, int expire) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(userEmail)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .signWith(SignatureAlgorithm.HS512, MY_KEY).compact();
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String userName() {
        return userEmail;
    }

    @Override
    public void loadJwt(String token) {
        claims = Jwts.parser().setSigningKey(MY_KEY).parseClaimsJws(token).getBody();
        userEmail = claims.getSubject();
        isValid = claims.getExpiration().after(new Date());
    }
}