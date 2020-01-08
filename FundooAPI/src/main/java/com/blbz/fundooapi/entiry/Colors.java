package com.blbz.fundooapi.entiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Order(6)
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorId;
    @Column(unique = true,nullable = false)
    private String colorName;
    @OneToMany
    private List<NoteInfo> noteInfos;
}
