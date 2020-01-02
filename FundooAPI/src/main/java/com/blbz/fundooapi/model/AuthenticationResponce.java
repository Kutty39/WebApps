package com.blbz.fundooapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponce {
    private final String jwt;
}
