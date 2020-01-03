package com.blbz.fundooapi.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponce {
    private final String name;
    private final int status;
    private final String jwt;
}
