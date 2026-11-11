package com.example.demo.auth;

import java.time.OffsetDateTime;

public record UserResponse(
        Long id,
        String email,
        String name,
        OffsetDateTime createdAt
) { }
