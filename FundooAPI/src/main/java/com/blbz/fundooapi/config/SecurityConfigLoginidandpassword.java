/*
package com.blbz.fundooapi.config;

import com.blbz.fundooapi.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigLoginidandpassword extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetail myUserDetail;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetail);
        authProvider.setPasswordEncoder(gePasswordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder gePasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
*/
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetail).passwordEncoder(getPasswordEncoder());
    }
*//*


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login1").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl("/logout").permitAll()
                .and()
                .httpBasic();
    }

}
*/
