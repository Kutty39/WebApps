package com.blbz.fundooapi.entiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Order(6)
public class Colours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colourId;
    @Column(unique = true,nullable = false)
    private String colourName;
}
