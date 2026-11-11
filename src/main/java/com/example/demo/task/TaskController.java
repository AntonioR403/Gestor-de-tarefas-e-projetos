package com.example.demo.task;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@PathVariable Long projectId,
                               @Valid @RequestBody CreateTaskRequest request){
        return taskService.create(projectId, request);
    }

    @GetMapping
    public List<TaskResponse> findByProject(@PathVariable Long projectId){
        return taskService.findByProject(projectId);
    }

    @GetMapping("/{taskId}")
    public TaskResponse findById(@PathVariable Long projectId, @PathVariable Long taskId){
        return taskService.findById(projectId, taskId);
    }

    @PutMapping("/{taskId}")
    public TaskResponse update(@PathVariable Long projectId, @PathVariable Long taskId, @Valid @RequestBody UpdateTaskResquest resquest){
        return taskService.update(projectId, taskId, resquest);
    }

    @DeleteMapping("/{taskId}")
    public void delete(@PathVariable Long projectId, @PathVariable Long taskId){
        taskService.delete(projectId, taskId);
    }
}
