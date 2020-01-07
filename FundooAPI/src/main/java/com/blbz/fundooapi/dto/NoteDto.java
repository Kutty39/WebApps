package com.blbz.fundooapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoteDto {
    private String noteTitle;
    private String noteText;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss a")
    private Date noteRemainder;
    private String noteRemainderLocation;
    private boolean showTick = false;
    private boolean isPinned = false;
    private String colour;
    private String noteStatus;
    private List<String> collaborator;
    private List<String> labels;
}
