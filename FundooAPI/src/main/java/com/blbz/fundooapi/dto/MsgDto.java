package com.blbz.fundooapi.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class MsgDto {
    private String name;
    private String subject;
    private String email;
    private String msg;
    private String jwt;
}
