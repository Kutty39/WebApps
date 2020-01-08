package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.dto.NoteDto;
import com.blbz.fundooapi.entiry.*;
import com.blbz.fundooapi.repository.*;
import com.blbz.fundooapi.service.CustomMapper;
import com.blbz.fundooapi.service.JwtUtil;
import com.blbz.fundooapi.service.NoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final CustomMapper customMapper;
    private final NoteRepo noteRepo;
    private NoteInfo noteInfo;
    private final LabelRepo labelRepo;
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private final NoteStatusRepo noteStatusRepo;
    private final ColorRepo colorRepo;


    @Autowired
    public NoteServiceImpl(CustomMapper customMapper, NoteRepo noteRepo, NoteInfo noteInfo
            , LabelRepo labelRepo, UserRepo userRepo, JwtUtil jwtUtil, NoteStatusRepo noteStatusRepo, ColorRepo colorRepo) {
        this.customMapper = customMapper;
        this.noteRepo = noteRepo;
        this.noteInfo = noteInfo;
        this.labelRepo = labelRepo;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.noteStatusRepo = noteStatusRepo;
        this.colorRepo = colorRepo;
    }

    @Override
    public int createNote(NoteDto noteDto, HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getHeader("Authorization") != null) {
            String jwt = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
            BeanUtils.copyProperties(noteDto, noteInfo);
            List<Label> labelList = noteDto.getLabels() != null ? customMapper.getListVal(noteDto.getLabels(), Label.class, labelRepo) : null;
            List<UserInfo> userInfos = noteDto.getCollaborator() != null ? customMapper.getListVal(noteDto.getCollaborator(), UserInfo.class, userRepo) : null;
            UserInfo createdBy = userRepo.findByEid(jwtUtil.loadJwt(jwt).userName());
            NoteStatus status=noteStatusRepo.findByStatusText("Active");
            Colors colors=colorRepo.findByColorName("White");
            noteInfo.setLabels(labelList);
            noteInfo.setCollaborator(userInfos);
            noteInfo.setNoteCreatedOn(Date.from(Instant.now()));
            noteInfo.setCreatedBy(createdBy);
            noteInfo.setNoteStatus(status);
            noteInfo.setColors(colors);
            noteInfo=noteRepo.save(noteInfo);
        }
        return noteInfo.getNoteId();
    }
}
