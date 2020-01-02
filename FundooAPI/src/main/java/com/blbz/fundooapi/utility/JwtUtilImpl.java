package com.blbz.fundooapi.utility;

import com.blbz.fundooapi.model.AuthenticationResponce;
import com.blbz.fundooapi.service.JwrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource(value = "jwt.properties", ignoreResourceNotFound = true)
@Getter
public class JwtUtilImpl implements JwrUtil {
    @Value("${jwt.expiry.time.sec}")
    private long EXPIRY_TIME;
    @Value("${jwt.secret}")
    private String KEY;

    private String userName = null;
    private boolean isValid = false;


    public JwtUtilImpl() {
    }

    public JwtUtilImpl(String token) {
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        userName = claims.getSubject();
        isValid = claims.getExpiration().before(new Date());
    }

    @Override
    public AuthenticationResponce generateJwt(UserDetails userDetailser) {
        return new AuthenticationResponce(Jwts.builder()
                .setClaims(null)
                .setSubject(userDetailser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .signWith(SignatureAlgorithm.HS512, KEY).compact());
    }
}