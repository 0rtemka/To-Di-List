package com.example.todolistrest.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PersonErrorResponse {
    private String message;
    private LocalDateTime timestamp;
}
