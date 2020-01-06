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
@Order(5)
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int labelId;
    @Column(nullable = false)
    private String labelText;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "labels")
    private List<NoteInfo> noteInfos;
}
