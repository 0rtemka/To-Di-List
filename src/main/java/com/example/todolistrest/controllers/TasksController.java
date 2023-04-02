package com.example.todolistrest.controllers;

import com.example.todolistrest.dto.TaskDTO;
import com.example.todolistrest.services.TasksService;
import com.example.todolistrest.utils.NotFoundException;
import com.example.todolistrest.utils.PersonErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/tasks")
@RequiredArgsConstructor
public class TasksController {
    private final TasksService tasksService;
    @GetMapping("/{person_id}")
    public List<TaskDTO> getTasks(@PathVariable int person_id) {
        return tasksService
                .getTasks(person_id)
                .stream()
                .map(tasksService::convertToTaskDTO)
                .toList();
    }

    @PostMapping("/{person_id}")
    public ResponseEntity<HttpStatus> addTask(@PathVariable int person_id, @RequestBody TaskDTO taskDTO) {
        tasksService.addTask(taskDTO, person_id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(NotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
