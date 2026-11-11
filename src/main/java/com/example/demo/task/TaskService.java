package com.example.demo.task;

import com.example.demo.project.Project;
import com.example.demo.project.ProjectNotFoundException;
import com.example.demo.project.ProjectRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserNotFoundException;
import com.example.demo.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public TaskResponse create(Long projectId, CreateTaskRequest request){
        Project project = projectRepository.findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException(projectId));
        User createdBy = userRepository.findById(request.createdById()).
                orElseThrow(() -> new UserNotFoundException(request.createdById()));
        Task task = new Task(project, createdBy, request.title(), request.description(), request.dueDate());

        if(request.priority() != null){
            task.setPriority(request.priority());
        }
        if(request.assigneeId() != null){
            User assignee = userRepository.findById((request.assigneeId())).
                    orElseThrow(() -> new UserNotFoundException(request.assigneeId()));
            task.setAssignee(assignee);
        }

        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> findByProject (Long projectId){
        if(!projectRepository.existsById(projectId)){
            throw new ProjectNotFoundException(projectId);
        }
        return taskRepository.findByProjectId(projectId).stream().map(this::toResponse).toList();
    }

    private TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getProject().getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getAssignee() == null ? null : task.getAssignee().getId(),
                task.getCreatedBy().getId(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
