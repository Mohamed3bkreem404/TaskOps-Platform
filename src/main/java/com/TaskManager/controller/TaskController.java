package com.TaskManager.controller;

import com.TaskManager.dto.CreateTaskRequest;
import com.TaskManager.dto.TaskResponse;
import com.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;

    @PostMapping
    public TaskResponse create(@RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getAll() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}