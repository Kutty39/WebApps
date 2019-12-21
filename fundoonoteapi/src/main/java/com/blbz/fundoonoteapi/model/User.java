package com.blbz.fundoonoteapi.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
