package com.example.tasksystem.label.dto;

import jakarta.validation.constraints.NotNull;

public record LabelRequestDto(
        @NotNull String name,
        @NotNull String color
) {
}
