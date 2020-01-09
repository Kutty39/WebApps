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
@Order(1)
@Component
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

    @OneToOne
    @JoinColumn(name = "colors")
    private Colors colors;

    @OneToOne
    @JoinColumn(name = "noteStatus")
    private NoteStatus noteStatus;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private UserInfo createdBy;

    @ManyToOne
    @JoinColumn(name = "editedBy")
    private UserInfo editedBy;

    @ManyToMany
    private List<UserInfo> collaborator;

    @ManyToMany
    private List<Label> labels;

}
