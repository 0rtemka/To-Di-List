package com.example.todolistrest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private String title;

    private String description;

    private boolean isDone;

    private LocalDateTime createdAt;

    private PersonDTO personDTO;
}
