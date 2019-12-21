package com.blbz.springmvc.model;

import com.blbz.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDetail {
    String email;
    String pas;
    @Autowired
    private UserService userService;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = userService.encript(pas);
    }

    @Override
    public String toString() {
        return "LoginDetail{" +
                "email='" + email + '\'' +
                ", pas='" + pas +
                '}';
    }
}
