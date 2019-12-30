package com.blbz.fundooapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Order(4)
public class Note_Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int status_id;
    @Column(nullable = false,unique = true)
    private String status_text;

}
