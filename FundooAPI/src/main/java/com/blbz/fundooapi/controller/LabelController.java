package com.blbz.fundooapi.controller;

import com.blbz.fundooapi.dto.LabelDto;
import com.blbz.fundooapi.exception.InvalidUserException;
import com.blbz.fundooapi.exception.LabelNotFoundException;
import com.blbz.fundooapi.exception.ParameterEmptyException;
import com.blbz.fundooapi.responce.GeneralResponse;
import com.blbz.fundooapi.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/labels")
public class LabelController {
    private final LabelService labelService;
    private final GeneralResponse generalResponse;

    @Autowired
    public LabelController(LabelService labelService, GeneralResponse generalResponse) {
        this.labelService = labelService;
        this.generalResponse = generalResponse;
    }

    @GetMapping
    public ResponseEntity<?> getAllLabels(@RequestHeader("Authorization") String jwtToken) throws  InvalidUserException {
        generalResponse.setResponse(labelService.getAllLabels(jwtToken));
        return ResponseEntity.ok(generalResponse);
    }

    @GetMapping("/{label}")
    public ResponseEntity<?> getLabel(@PathVariable String label, @RequestHeader("Authorization") String jwtToken) throws LabelNotFoundException,  InvalidUserException, ParameterEmptyException {
        if(label==null || label.isEmpty()){
            throw new ParameterEmptyException("label text not passed");
        }
        generalResponse.setResponse(labelService.getLabel(label,jwtToken));
        return ResponseEntity.ok(generalResponse);
    }

    @PostMapping
    public ResponseEntity<?> createLabel(LabelDto labelDto){
        generalResponse.setResponse(labelService.createLabel(labelDto));
        return ResponseEntity.ok(generalResponse);
    }
    @PutMapping
    public ResponseEntity<?> editLabel(@RequestBody LabelDto labelDto) throws  LabelNotFoundException {
        generalResponse.setResponse(labelService.editLabel(labelDto));
        return ResponseEntity.ok(generalResponse);
    }
}
