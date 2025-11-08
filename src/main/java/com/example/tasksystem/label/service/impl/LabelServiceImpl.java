package com.example.tasksystem.label.service.impl;

import com.example.tasksystem.label.dto.LabelRequestDto;
import com.example.tasksystem.label.dto.LabelResponseDto;
import com.example.tasksystem.label.mapper.LabelMapper;
import com.example.tasksystem.label.model.Label;
import com.example.tasksystem.label.repository.LabelRepository;
import com.example.tasksystem.label.service.LabelService;
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
        final Label entity = labelMapper.toEntity(requestDto);
        final Label saved = labelRepository.save(entity);
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
        final Label label = labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + id));

        labelMapper.updateLableFromDto(requestDto, label);

        final Label saved = labelRepository.save(label);
        return labelMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        final Label label = labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + id));
        labelRepository.delete(label);
    }
}
