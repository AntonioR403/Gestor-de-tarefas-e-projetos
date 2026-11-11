package com.example.demo.task;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record TaskResponse(
        Long id,
        Long projectId,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate,
        Long assigneeId,
        Long createdById,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
