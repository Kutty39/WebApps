package com.blbz.fundoonoteapi.controller;

import com.blbz.fundoonoteapi.model.Test;
import com.blbz.fundoonoteapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private User user;

   /* @GetMapping("/get")
    public List<User> getall(){
        return List<User>;
    }*/
   @GetMapping("/{id}")
    public User add(@PathVariable("id") int id){
        user.setId(id);
        return user;
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
