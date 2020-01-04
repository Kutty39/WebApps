package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.LoginDto;
import com.blbz.fundooapi.dto.MsgDto;
import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.responce.GeneralResponse;
import com.blbz.fundooapi.service.JwrUtil;
import com.blbz.fundooapi.service.Publisher;
import com.blbz.fundooapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RestController
@Slf4j
public class FrontController {
    @Value("${jwt.expiry.time.sec.day}")
    private int expireForDay;

    @Autowired
    private Publisher publisher;
    @Autowired
    private MsgDto msgDto;
    @Autowired
    private JwrUtil jwrUtil;
    @Autowired
    private UserService userService;
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
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        log.info("/Login");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
        }catch (BadCredentialsException e){
            log.error("Bad credential(Username or Password is wrong)");
            throw new Exception("Bad credential(Username or Password is wrong)");
        }
        return ResponseEntity.ok(jwrUtil.generateJwt(loginDto.getUsername()));
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

        msgDto.setEmail(regData.getEid());
        msgDto.setJwt(jwrUtil.generateJwt(regData.getEid(),expireForDay));
        msgDto.setName(regData.getFname()+" "+regData.getLname());
        msgDto.setSubject("Account Activation");
        try {
            userService.sendActivationMail(msgDto);
        }catch (MessagingException e){
            return ResponseEntity.badRequest().body("Something went wrong");
        }
        return ResponseEntity.ok().body("Successfully Registered. Please check your mail to activate your account");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> validEmail(@PathVariable String email) {
        return ResponseEntity.ok(new GeneralResponse(userService.checkEmail(email)));
    }

}
