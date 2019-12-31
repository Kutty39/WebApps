package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.model.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class SecUserDetail implements UserDetails {
    private UserInfo userInfo;

    public SecUserDetail() {
    }

    public SecUserDetail(UserInfo userInfo) {
        this.userInfo = userInfo;
        System.out.println(userInfo.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return userInfo.getPas();
    }

    @Override
    public String getUsername() {
        return userInfo.getEid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
