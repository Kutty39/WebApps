/*
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
@Order(7)
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noteInfo")
    private NoteInfo noteInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colInfo")
    private UserInfo userInfo;
}
*/
