package com.blbz.fundooapi.config;

import com.blbz.fundooapi.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;
    @Autowired
    private MyUserDetail myUserDetail;

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//this will disable the default form login
                .authorizeRequests()
                .antMatchers("/api/register","/api/email/**","/api/login").permitAll()//this will allow matched request without authentication
                //.anyRequest().authenticated()// this will authenticate all the request
        .and()
        .exceptionHandling().authenticationEntryPoint(entryPoint)// this is used to navigate all the exception to entry point class
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        /* this will make session stateless
        “stateless” – is a guarantee that the application will not create any session at all.
         “never” option will ensure that Spring Security itself will not create any session; however, if the application creates one, then Spring Security will make use of it.
         By default, Spring Security will create a session when it needs one – this is “ifRequired“.
         */
       // http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
