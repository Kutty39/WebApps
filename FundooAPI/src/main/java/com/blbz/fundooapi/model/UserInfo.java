package com.blbz.fundooapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
@Order(2)
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(name = "user_fname",nullable = false)
    private String fname;
    @Column(name = "user_lname",nullable = false)
    private String lname;
    @Column(name = "user_email",unique = true,nullable = false)
    private String eid;
    @Column(name = "user_phn",nullable = false)
    private String phn;
    @Column(name = "user_address",nullable = false)
    private String adrs;
    @Column(name = "user_pass",nullable = false)
    private String pas;
    private LocalDate user_createdOn;
    private LocalDate user_lastModifiedOn;
    @OneToOne
    @JoinColumn(name = "user_status",referencedColumnName = "status_id",columnDefinition = "int(11) DEFAULT 1")
    private User_Status user_status;


}

