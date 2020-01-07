package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.NoteDto;
import org.springframework.stereotype.Component;

@Component
public interface NoteService {
    int createNote(NoteDto noteDto);
}
