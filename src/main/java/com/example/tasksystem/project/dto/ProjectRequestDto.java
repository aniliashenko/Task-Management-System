package com.example.tasksystem.project.dto;

import com.example.tasksystem.project.model.Project;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjectRequestDto(

        @NotBlank(message = "Project name can not be empty")
        @Size(max = 100, message = "Project size can not be longer than 100 symbols")
        String name,

        @Size(max = 1000, message = "Project size can not be longer than 100 symbols")
        String description,

        @NotNull
        Project.Status status,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @FutureOrPresent(message = "End date can not be in the past")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate endDate,

        String search
) {
}
