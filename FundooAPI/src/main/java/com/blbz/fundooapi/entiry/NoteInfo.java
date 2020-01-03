package com.blbz.fundooapi.entiry;

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
@Order(1)
public class NoteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;
    private String noteTitle;
    private String noteText;
    private LocalDate noteRemainder;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colour")
    private Colours colour;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noteStatus")
    private NoteStatus noteStatus;
    private boolean showTick = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private UserInfo createdBy;
    private LocalDate noteCreatedOn;
    private LocalDate noteLastEditedOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editedBy")
    private UserInfo editedBy;
    private boolean isPinned = false;

}
