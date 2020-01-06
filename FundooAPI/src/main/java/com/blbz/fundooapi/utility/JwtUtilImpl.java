package com.blbz.fundooapi.utility;

import com.blbz.fundooapi.service.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@PropertySource(value = "jwt.properties", ignoreResourceNotFound = true)
@Getter
@NoArgsConstructor
public class JwtUtilImpl implements JwtUtil {
    @Value("${jwt.expiry.time.sec}")
    private long EXPIRY_TIME;
    @Value("${jwt.secret}")
    private String MY_KEY;

    private   String userEmail = null;
    private  boolean isValid = false;
    private Claims claims;
    private HashMap<String,Object> claimsBody=new HashMap<>();

    @Override
    public String generateJwt(String userEmail,String url) {
        claimsBody.put("url",url);
        return Jwts.builder()
                .addClaims(claimsBody)
                .setSubject(userEmail)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .signWith(SignatureAlgorithm.HS512, MY_KEY).compact();
    }

    @Override
    public String generateJwt(String userEmail, int expire,String url) {
        claimsBody.put("url",url);
        return Jwts.builder()
                .addClaims(claimsBody)
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
    @Override
    public Claims getClaims(){
        return claims;
    }
}