package com.example.demo.project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService _projectservice){
        this.projectService = _projectservice;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody CreateProjectRequest request){
        return projectService.create(request);
    }

    @GetMapping
    public List<ProjectResponse> findAll(){
        return projectService.findAll();
    }
}
