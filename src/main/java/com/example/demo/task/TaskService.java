package com.example.demo.task;

import com.example.demo.project.Project;
import com.example.demo.project.ProjectNotFoundException;
import com.example.demo.project.ProjectRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserNotFoundException;
import com.example.demo.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @Transactional(readOnly = true)
    public TaskResponse findById(Long projectId, Long taskId){
        Task task = taskRepository.findByIdAndProjectId(taskId, projectId).orElseThrow(() -> new TaskNotFoundException(taskId));
        return toResponse(task);
    }

    @Transactional
    public TaskResponse update(Long projectId, Long taskId, UpdateTaskResquest request){
        Task task = taskRepository.findByIdAndProjectId(taskId, projectId).orElseThrow(() -> new TaskNotFoundException(taskId));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setDueDate(request.dueDate());

        if(request.assigneeId() == null){
            task.setAssignee(null);
        }else{
            User assignee = userRepository.findById((request.assigneeId())).
                    orElseThrow(() -> new UserNotFoundException(request.assigneeId()));
            task.setAssignee(assignee);
        }

        return toResponse(task);
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long projectId, Long taskId){
        Task task = taskRepository.findByIdAndProjectId(taskId, projectId).orElseThrow(() -> new TaskNotFoundException(taskId));
        taskRepository.delete(task);
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
