package com.blbz.fundooapi.entiry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Order(3)
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(nullable = false,unique = true)
    private String statusText;
    @JsonIgnore
    @OneToMany(mappedBy = "userStatus")
    private List<UserInfo> userInfos;
}
