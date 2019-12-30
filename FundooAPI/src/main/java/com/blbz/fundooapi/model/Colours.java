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
@Order(6)
public class Colours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colour_id;
    @Column(unique = true,nullable = false)
    private String colour_name;
}
