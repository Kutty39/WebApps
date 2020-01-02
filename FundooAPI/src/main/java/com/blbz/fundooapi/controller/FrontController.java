package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.model.AuthenticationRequest;
import com.blbz.fundooapi.service.JwrUtil;
import com.blbz.fundooapi.service.MyUserDetail;
import com.blbz.fundooapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RestController
@Slf4j
@RequestMapping("/api")
public class FrontController {
    @Autowired
    private JwrUtil jwrUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private MyUserDetail myUserDetail;
    @Autowired
    private AuthenticationManager authenticationManager;


    public FrontController() {

    }

    @PostMapping("/logout")
    public String logout() {
        log.info("/logout");
        return "logout";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        log.info("/Login");
        System.out.println("fsdfsda");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password");
        }
        final UserDetails userDetails = myUserDetail.loadUserByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(jwrUtil.generateJwt(userDetails));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto regData, BindingResult result) {
        log.info("/Register");
        HashMap<String, List<String>> errorDetail = new HashMap<>();
        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            errors = (result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));

        }
        if (regData.getPas() != null) {
            if (!regData.getPas().equals(regData.getConpas())) {
                errors.add("Password and Confirm password should be equal");
            }
        }
        if (userService.checkEmail(regData.getEid())) {
            errors.add("Email already exists.");
        }
        if (errors.size() > 0) {
            errorDetail.put("Errors", errors);
            log.info(String.valueOf(errors));
            return ResponseEntity.badRequest().body(errorDetail);
        }
        userService.registerUser(regData);
        return ResponseEntity.ok("Successfully Registered");
    }

    @GetMapping("/email/{email}")
    public boolean validEmail(@PathVariable String email) {
        return userService.checkEmail(email);
    }

}
