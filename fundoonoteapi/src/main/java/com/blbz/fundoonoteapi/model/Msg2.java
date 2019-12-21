package com.blbz.fundoonoteapi.model;

import org.springframework.stereotype.Component;

@Component
public class Msg2 implements Test {
    @Override
    public String hi() {
        return "Bye";
    }
}
