package com.example.tasksystem.dto.label;

import jakarta.validation.constraints.NotNull;

public record LabelRequestDto(
        @NotNull String name,
        @NotNull String color
) {
}
