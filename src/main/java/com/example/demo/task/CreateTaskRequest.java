package com.example.demo.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateTaskRequest(
        @NotBlank @Size(max = 255) String title,
        String description,
        TaskPriority priority,
        LocalDate dueDate,
        Long assigneeId,
        @NotNull Long createdById
        ) {
}
