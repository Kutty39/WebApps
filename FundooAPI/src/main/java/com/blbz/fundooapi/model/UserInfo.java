package com.blbz.fundooapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Order(2)
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "userFname", nullable = false)
    private String fname;
    @Column(name = "userLname", nullable = false)
    private String lname;
    @Column(name = "userEmail", unique = true, nullable = false)
    private String eid;
    @Column(name = "userPhn", nullable = false)
    private String phn;
    @Column(name = "userAddress", nullable = false)
    private String adrs;
    @Column(name = "userPass", nullable = false)
    private String pas;
    private LocalDate userCreatedOn;
    private LocalDate userLastModifiedOn;
    @OneToOne
    @JoinColumn(name = "userStatus")
    private UserStatus userStatus;


}

