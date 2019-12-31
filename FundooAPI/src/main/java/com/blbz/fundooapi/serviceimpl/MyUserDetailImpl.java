package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.model.UserInfo;
import com.blbz.fundooapi.service.MyUserDetail;
import com.blbz.fundooapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailImpl implements MyUserDetail {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo u =userService.getUser(s);
        if(u==null)
            throw  new UsernameNotFoundException("Wrong user name and Password");
        return new SecUserDetail(u);
    }
}
