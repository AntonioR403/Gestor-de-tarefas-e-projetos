package com.example.demo.project;

import java.time.OffsetDateTime;

public record ProjectResponse(Long id, String name, String description, OffsetDateTime createdAt) {
}
