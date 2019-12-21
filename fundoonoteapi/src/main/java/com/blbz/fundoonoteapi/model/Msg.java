package com.blbz.fundoonoteapi.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Msg implements Test {
    @Override
    public String hi() {
        return "Hi";
    }
}
