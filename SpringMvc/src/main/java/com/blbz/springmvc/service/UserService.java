package com.blbz.springmvc.service;

import com.blbz.springmvc.entity.UserInfo;
import com.blbz.springmvc.model.LoginDetail;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    boolean validater(String email);
    boolean register(UserInfo userInfo);
    String encript(String pas);
    UserInfo login(LoginDetail loginDetail);
    boolean update(UserInfo user);
}
