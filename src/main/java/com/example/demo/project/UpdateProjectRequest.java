package com.example.demo.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProjectRequest(@NotBlank @Size(max = 250) String name, String description) {

}
