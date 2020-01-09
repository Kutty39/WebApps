package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.NoteDto;
import com.blbz.fundooapi.dto.NoteStatusDto;
import com.blbz.fundooapi.dto.NotesStatusDto;
import com.blbz.fundooapi.entiry.NoteInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public interface NoteService {
    int createNote(NoteDto noteDto, HttpServletRequest httpServletRequest);
    int noteAction(NoteDto noteDto, HttpServletRequest httpServletRequest, boolean edit);
    int editNote(NoteDto noteDto, HttpServletRequest httpServletRequest);
    int deleteNote(int noteId);
    int deleteNotes(List<Integer> noteId);
    int updateStatus(NotesStatusDto noteStatusDto, HttpServletRequest httpServletRequest);
    int updateStatus(NoteStatusDto noteStatusDto, HttpServletRequest httpServletRequest);

    List<NoteInfo> getAllNotes(HttpServletRequest httpServletRequest);
}
