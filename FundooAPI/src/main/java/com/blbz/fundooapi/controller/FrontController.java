package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.RegisterDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RestController
public class FrontController {

    @PostMapping("/login")
    public String valid() {
        return "test";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto regData, BindingResult result) {
        HashMap<String, List<String>> errorDetail = new HashMap<>();
        List<String> errors =new ArrayList<>();
        if (result.hasErrors()) {
            errors = (result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));

        }
        if(!regData.getPas().equals(regData.getConpas())){
            errors.add("Password and Confirm password should be equal");
        }
        if(errors.size()>0){
            errorDetail.put("Errors",errors);
            return ResponseEntity.badRequest().body(errorDetail);
        }
        return ResponseEntity.ok(regData);
    }

}
