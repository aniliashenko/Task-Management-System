package com.example.tasksystem.service.impl;

import com.example.tasksystem.dto.label.LabelRequestDto;
import com.example.tasksystem.dto.label.LabelResponseDto;
import com.example.tasksystem.mapper.LabelMapper;
import com.example.tasksystem.model.Label;
import com.example.tasksystem.repository.LabelRepository;
import com.example.tasksystem.service.LabelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    @Override
    public LabelResponseDto create(LabelRequestDto requestDto) {
        Label entity = labelMapper.toEntity(requestDto);
        Label saved = labelRepository.save(entity);
        return labelMapper.toDto(saved);
    }

    @Override
    public List<LabelResponseDto> getAll() {
        return labelRepository.findAll()
                .stream()
                .map(labelMapper::toDto)
                .toList();
    }

    @Override
    public LabelResponseDto update(Long id, LabelRequestDto requestDto) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + id));

        labelMapper.updateLableFromDto(requestDto, label);

        Label saved = labelRepository.save(label);
        return labelMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + id));
        labelRepository.delete(label);
    }
}
