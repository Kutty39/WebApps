package com.blbz.fundooapi.entiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Order(2)
@Component
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
    private Date userCreatedOn;
    private Date userLastModifiedOn;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userStatus")
    private UserStatus userStatus;
    @ManyToMany(fetch =FetchType.LAZY,mappedBy = "collaborator")
    private List<NoteInfo> noteInfo;


}

