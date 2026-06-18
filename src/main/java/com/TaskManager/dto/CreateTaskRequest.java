package com.TaskManager.dto;

public record CreateTaskRequest(
        String title,
        String description
) {
}
