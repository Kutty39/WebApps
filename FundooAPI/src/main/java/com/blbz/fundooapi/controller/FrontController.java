package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.LoginDto;
import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.dto.ResetPassDto;
import com.blbz.fundooapi.exception.InvalidTokenException;
import com.blbz.fundooapi.exception.TokenExpiredException;
import com.blbz.fundooapi.responce.GeneralResponse;
import com.blbz.fundooapi.service.UserService;
import com.blbz.fundooapi.utility.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@RestController
@Slf4j
public class FrontController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final GeneralResponse generalResponse;
    private final Util util;

    @Autowired
    public FrontController(UserService userService, AuthenticationManager authenticationManager, GeneralResponse generalResponse, Util util) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.generalResponse = generalResponse;
        this.util = util;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(HttpServletRequest httpServletRequest) throws Exception {
        generalResponse.setResponse(userService.getAllUser(httpServletRequest));
        return ResponseEntity.ok().body(generalResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid@RequestBody LoginDto loginDto,BindingResult bindingResult) throws Exception {
       util.validAndThrow(bindingResult);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credential(Username or Password is wrong)");
        }
        generalResponse.setResponse(userService.loginUser(loginDto.getUsername()));
        return ResponseEntity.ok(generalResponse);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto regData, BindingResult result) throws Exception {
        HashMap<String, List<String>> errorDetail = new HashMap<>();
        List<String> errors = new ArrayList<>();
        util.validAndThrow(result);
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
        //try {
        return ResponseEntity.ok().body(userService.registerUser(regData));
        /*} catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Something went wrong. please try register again");
        }*/
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<?> validEmail(@PathVariable String email) {
        generalResponse.setResponse(userService.checkEmail(email));
        return ResponseEntity.ok(generalResponse);
    }

    @GetMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestParam String tk) throws TokenExpiredException, InvalidTokenException {
        generalResponse.setResponse(userService.userActivate(tk));
        return ResponseEntity.ok().body(generalResponse);
    }

    @GetMapping("/blockjwt")
    public ResponseEntity<?> blockJwt(@RequestParam String jwt) {
        userService.blockedJwt(jwt);
        generalResponse.setResponse(true);
        return ResponseEntity.ok().body(generalResponse);
    }

    @GetMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws Exception {
        if (userService.checkEmail(email)) {
            generalResponse.setResponse(userService.forgotPasswordMail(email));
            return ResponseEntity.ok().body(generalResponse);
        } else {
            generalResponse.setResponse("email not found");
            return ResponseEntity.badRequest().body(generalResponse);
        }

    }
    @PostMapping("/resetpassword")
    public ResponseEntity<?> resetPass(@Valid @RequestBody ResetPassDto resetPassDto, BindingResult bindingResult, HttpServletRequest request) {
        util.validAndThrow(bindingResult);
        if (!resetPassDto.getPassword().equals(resetPassDto.getConpassword())) {
            return ResponseEntity.badRequest().body("Password and conform is not matched");
        }
        String jwt = request.getHeader("Authorization").replace("Bearer ", "");
        userService.updatePassword(jwt, resetPassDto.getPassword());
        return ResponseEntity.ok("Successfully resetted password. Please login again");
    }

    @GetMapping("/reset")
    public ResponseEntity<?> reset(@RequestParam String tk) {
        generalResponse.setResponse("please send your password and conform password to http://localhost:8080/api/resetpassword\nusing post with JWT:" + tk +
                " this token in header (parameters \n{\"password\":\"your password\",\n\"conpassword\":\"your conform password\"\n})");
        return ResponseEntity.ok(generalResponse);
    }
}
