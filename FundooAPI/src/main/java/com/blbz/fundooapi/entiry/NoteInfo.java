package com.blbz.fundooapi.entiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private Date noteRemainder;
    private String noteRemainderLocation;
    private boolean showTick = false;
    private Date noteCreatedOn;
    private Date noteLastEditedOn;
    private boolean isPinned = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colour")
    private Colours colour;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noteStatus")
    private NoteStatus noteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private UserInfo createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editedBy")
    private UserInfo editedBy;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserInfo> collaborator;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Label> labels;

}
