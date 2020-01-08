package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.NoteDto;
import com.blbz.fundooapi.dto.ResetPassDto;
import com.blbz.fundooapi.responce.GeneralResponse;
import com.blbz.fundooapi.service.NoteService;
import com.blbz.fundooapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {
    private final UserService userService;
    private final GeneralResponse generalResponse;
    private final NoteService noteService;

    @Autowired
    public ApiController(UserService userService, GeneralResponse generalResponse,
                         NoteService noteService) {
        this.userService = userService;
        this.generalResponse = generalResponse;
        this.noteService=noteService;
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<?> resetPass(@Valid @RequestBody ResetPassDto resetPassDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            List<String> error = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(error);
        }
        if (!resetPassDto.getPassword().equals(resetPassDto.getConpassword())) {
            return ResponseEntity.badRequest().body("Password and conform is not matched");
        }
        String jwt = request.getHeader("Authorization").replace("Bearer ", "");
        userService.updatePassword(jwt, resetPassDto.getPassword());
        return ResponseEntity.ok("Successfully resetted password. Please login again");
    }
    @PostMapping("/notes")
    public ResponseEntity<?> createNote(@RequestBody NoteDto noteDto, HttpServletRequest httpServletRequest) {
        if (noteDto.getNoteText() != null || noteDto.getNoteTitle() != null ||
                noteDto.getNoteRemainder() != null || noteDto.getCollaborator() != null) {
            int noteId=noteService.createNote(noteDto,httpServletRequest);
            generalResponse.setResponse(noteId);
            if(noteId>0) {
                return ResponseEntity.ok(generalResponse);
            }else{
                return ResponseEntity.badRequest().body(generalResponse);
            }
        } else {
            generalResponse.setResponse("Any one of the fields is mandatory." +
                    "Title,Note Text, Remainder,Collaborator or Label");
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }
}
