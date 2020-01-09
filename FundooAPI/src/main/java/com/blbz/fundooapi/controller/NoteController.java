package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.*;
import com.blbz.fundooapi.responce.GeneralResponse;
import com.blbz.fundooapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class NoteController {
    private final NoteService noteService;
    private GeneralResponse generalResponse;

    @Autowired
    public NoteController(GeneralResponse generalResponse, NoteService noteService) {
        this.generalResponse = generalResponse;
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public ResponseEntity<?> getNote(HttpServletRequest httpServletRequest) {
        noteService.getAllNotes(httpServletRequest);
        generalResponse.setResponse("");
        return  ResponseEntity.ok(generalResponse);
    }

    @PostMapping("/notes")
    public ResponseEntity<?> createNote(@RequestBody NoteDto noteDto, HttpServletRequest httpServletRequest) {
        if (noteDto.getNoteText() != null || noteDto.getNoteTitle() != null ||
                noteDto.getNoteRemainder() != null || noteDto.getCollaborator() != null) {
            int noteId = noteService.createNote(noteDto, httpServletRequest);
            generalResponse.setResponse(noteId);
            if (noteId > 0) {
                return ResponseEntity.ok(generalResponse);
            } else {
                return ResponseEntity.badRequest().body(generalResponse);
            }
        } else {
            generalResponse.setResponse("Any one of the fields is mandatory." +
                    "Title,Note Text, Remainder,Collaborator or Label");
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    @PutMapping("/notes")
    public ResponseEntity<?> editNotes(@RequestBody NoteDto noteDto, HttpServletRequest httpServletRequest) {
        int noteId = noteService.editNote(noteDto, httpServletRequest);
        if (noteId > 0) {
            generalResponse.setResponse(noteId);
            return ResponseEntity.ok().body(generalResponse);
        } else {
            generalResponse.setResponse("Something went wrong");
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    @PutMapping("/note/status")
    public ResponseEntity<?> updatedStatus(@RequestBody NoteStatusDto noteStatusDto, HttpServletRequest httpServletRequest) {
        int noteID = noteService.updateStatus(noteStatusDto, httpServletRequest);
        generalResponse.setResponse(noteID);
        if (noteID > 0) {
            return ResponseEntity.ok(generalResponse);
        } else {
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    @PutMapping("/notes/status")
    public ResponseEntity<?> updatedStatus(@RequestBody NotesStatusDto notesStatusDto, HttpServletRequest httpServletRequest) {
        int noteId = noteService.updateStatus(notesStatusDto, httpServletRequest);
        generalResponse.setResponse(noteId);
        if (noteId > 0) {
            return ResponseEntity.ok(generalResponse);
        } else {
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    @DeleteMapping("/note")
    public ResponseEntity<?> deleteNote(@RequestBody NoteDeleteDto noteDeleteDto) {
        int noteId = noteService.deleteNote(noteDeleteDto.getNoteId());
        generalResponse.setResponse(noteId);
        if (noteId > 0) {
            return ResponseEntity.ok(generalResponse);
        } else {
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    @DeleteMapping("/notes")
    public ResponseEntity<?> deleteNote(@RequestBody NotesDeleteDto notesDeleteDto) {
        int noteID = noteService.deleteNotes(notesDeleteDto.getNoteId());
        generalResponse.setResponse(noteID);
        if (noteID > 0) {
            return ResponseEntity.ok(generalResponse);
        } else {
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }
}
