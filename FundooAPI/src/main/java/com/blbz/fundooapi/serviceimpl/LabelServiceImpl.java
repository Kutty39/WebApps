package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.dto.LabelDto;
import com.blbz.fundooapi.entiry.Label;
import com.blbz.fundooapi.repository.LabelRepo;
import com.blbz.fundooapi.service.LabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {
    private final LabelRepo labelRepo;
    private final Label label;

    public LabelServiceImpl(LabelRepo labelRepo, Label label) {
        this.labelRepo = labelRepo;
        this.label = label;
    }

    @Override
    public Label createLabel(LabelDto labelDto) {
        BeanUtils.copyProperties(labelDto,label);
        return labelRepo.save(label);
    }
}
