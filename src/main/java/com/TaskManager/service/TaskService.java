package com.TaskManager.service;

import com.TaskManager.dto.CreateTaskRequest;
import com.TaskManager.dto.TaskResponse;
import com.TaskManager.model.Task;
import com.TaskManager.model.TaskStatus;
import com.TaskManager.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepository;

    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(TaskStatus.TODO)
                .createdAt(LocalDateTime.now())
                .build();

        task = taskRepository.save(task);

        return toResponse(task);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse getTask(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return toResponse(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }
}