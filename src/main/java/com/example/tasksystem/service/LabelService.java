package com.example.tasksystem.service;

import com.example.tasksystem.dto.label.LabelRequestDto;
import com.example.tasksystem.dto.label.LabelResponseDto;

import java.util.List;

public interface LabelService {

    LabelResponseDto create(LabelRequestDto requestDto);

    List<LabelResponseDto> getAll();

    LabelResponseDto update(Long id, LabelRequestDto requestDto);

    void delete(Long id);
}
