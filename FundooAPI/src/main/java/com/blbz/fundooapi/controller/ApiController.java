package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.NoteDto;
import com.blbz.fundooapi.dto.ResetPassDto;
import com.blbz.fundooapi.entiry.Label;
import com.blbz.fundooapi.repository.LabelRepo;
import com.blbz.fundooapi.responce.GeneralResponse;
import com.blbz.fundooapi.service.CustomMapper;
import com.blbz.fundooapi.service.UserService;
import com.blbz.fundooapi.serviceimpl.CustomMapperImpl;
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

    @Autowired
    public ApiController(UserService userService, GeneralResponse generalResponse) {
        this.userService = userService;
        this.generalResponse = generalResponse;
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
@Autowired
    public LabelRepo labelRepo;
    @PostMapping("/notes")
    public ResponseEntity<?> createNote(@RequestBody NoteDto noteDto) {
        if (noteDto.getNoteText() != null || noteDto.getNoteTitle() != null ||
                noteDto.getNoteRemainder() != null || noteDto.getCollaborator() != null ||
                noteDto.getLabels() != null) {
            CustomMapper mapper = new CustomMapperImpl();
            Label label=new Label();
            List<Label> list=mapper.mapper(noteDto.getLabels(), label,labelRepo);
            list.forEach(System.out::println);
            return ResponseEntity.ok(noteDto);
        } else {
            generalResponse.setResponse("Any one of the fields is mandatory." +
                    "Title,Note Text, Remainder,Collaborator or Label");
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }
}
