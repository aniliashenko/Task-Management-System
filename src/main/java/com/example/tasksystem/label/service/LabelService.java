package com.example.tasksystem.label.service;

import com.example.tasksystem.label.dto.LabelRequestDto;
import com.example.tasksystem.label.dto.LabelResponseDto;

import java.util.List;

public interface LabelService {

    LabelResponseDto create(LabelRequestDto requestDto);

    List<LabelResponseDto> getAll();

    LabelResponseDto update(Long id, LabelRequestDto requestDto);

    void delete(Long id);
}
