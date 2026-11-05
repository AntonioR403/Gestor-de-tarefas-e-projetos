package com.example.demo.project;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public ProjectResponse create(CreateProjectRequest request){
        Project project = new Project(request.name(), request.description());
        Project saved = projectRepository.save(project);
        return toResponse(saved);
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProjectResponse findById(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
        return toResponse(project);
    }
    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(project.getId(), project.getName(), project.getDescription(), project.getCreatedAt());
    }

    public void delete(Long id){
        if(!projectRepository.existsById(id)){
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
    }
}
