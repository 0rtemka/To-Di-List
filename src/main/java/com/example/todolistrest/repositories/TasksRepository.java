package com.example.todolistrest.repositories;

import com.example.todolistrest.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Integer> {
}
