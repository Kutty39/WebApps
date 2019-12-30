package com.blbz.fundooapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Order(1)
public class Note_Info {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int note_id;
    private String note_title;
    private String note_text;
    private LocalDate note_remainder;
    @OneToOne
    @JoinColumn(name="colour",referencedColumnName = "colour_id",columnDefinition = "int(11) DEFAULT 1")
    private Colours colour;
    @OneToOne
    @JoinColumn(name="noteStatus",referencedColumnName = "status_id",columnDefinition = "int(11) DEFAULT 1")
    private Note_Status noteStatus;
    private boolean show_tick=false;
    @ManyToOne
    @JoinColumn(name="createdBy",referencedColumnName = "user_id")
    private UserInfo createdBy;
    private LocalDate note_createdOn;
    private LocalDate note_lastEditedOn;
    @ManyToOne
    @JoinColumn(name="editedBy",referencedColumnName = "user_id")
    private UserInfo editedBy;
    private boolean note_pinned=false;
    @ManyToOne
    @JoinColumn(name="label",referencedColumnName = "label_id")
    private Label label;

}
