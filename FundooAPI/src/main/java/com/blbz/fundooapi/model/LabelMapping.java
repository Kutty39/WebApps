package com.blbz.fundooapi.model;

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
@Order(8)
public class LabelMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lbmId;
    @ManyToOne
    @JoinColumn(name="labelInfo")
    private Label label;
    @ManyToOne
    @JoinColumn(name = "noteInfo")
    private NoteInfo noteInfo;
}
