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
@Order(7)
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colId;
    @ManyToOne
    @JoinColumn(name = "noteInfo")
    private NoteInfo noteInfo;
    @ManyToOne
    @JoinColumn(name = "colInfo")
    private UserInfo userInfo;
}
