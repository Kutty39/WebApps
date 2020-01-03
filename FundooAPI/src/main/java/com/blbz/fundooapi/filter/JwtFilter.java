package com.blbz.fundooapi.filter;

import com.blbz.fundooapi.config.MyUserDetailService;
import com.blbz.fundooapi.service.JwrUtil;
import com.blbz.fundooapi.utility.JwtUtilImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private MyUserDetailService myUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        log.info(header);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            JwrUtil jwt = new JwtUtilImpl(token);
            if (jwt.isValid()&& SecurityContextHolder.getContext().getAuthentication()==null) {
                log.info("Authenticated");
                UserDetails userDetails = myUserDetailService.loadUserByUsername(jwt.userName());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                log.error("Token expired. please login again");
                throw new RuntimeException("Token expired. please login again");
            }

        }
        filterChain.doFilter(request, response);
    }
}