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
@Order(5)
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int label_id;
    @Column(nullable = false)
    private String label_text;

}
