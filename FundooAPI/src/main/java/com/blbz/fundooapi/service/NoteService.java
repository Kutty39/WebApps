package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.NoteDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface NoteService {
    int createNote(NoteDto noteDto, HttpServletRequest httpServletRequest);
}
