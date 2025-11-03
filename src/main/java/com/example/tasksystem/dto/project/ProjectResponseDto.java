package com.example.tasksystem.dto.project;

import java.time.LocalDate;

public record ProjectResponseDto(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {}
