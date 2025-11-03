package com.example.tasksystem.controller;

import com.example.tasksystem.dto.label.LabelRequestDto;
import com.example.tasksystem.dto.label.LabelResponseDto;
import com.example.tasksystem.service.LabelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelResponseDto create(@RequestBody @Valid LabelRequestDto requestDto) {
        return labelService.create(requestDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<LabelResponseDto> getAll() {
        return labelService.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public LabelResponseDto update(@PathVariable Long id,
                                   @RequestBody @Valid LabelRequestDto requestDto) {
        return labelService.update(id, requestDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        labelService.delete(id);
    }
}
