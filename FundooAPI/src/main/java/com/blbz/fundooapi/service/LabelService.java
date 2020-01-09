package com.blbz.fundooapi.service;

import com.blbz.fundooapi.dto.LabelDto;
import com.blbz.fundooapi.entiry.Label;
import org.springframework.stereotype.Component;

@Component
public interface LabelService {
    Label createLabel(LabelDto labelDto);
}
