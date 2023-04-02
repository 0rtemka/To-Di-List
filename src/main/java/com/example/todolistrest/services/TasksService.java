package com.example.todolistrest.services;

import com.example.todolistrest.dto.TaskDTO;
import com.example.todolistrest.models.Person;
import com.example.todolistrest.models.Task;
import com.example.todolistrest.repositories.TasksRepository;
import com.example.todolistrest.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    public List<Task> getTasks(int person_id) {
        Person person = peopleService.getPerson(person_id);
        return person.getTasks();
    }

    public void addTask(TaskDTO taskDTO, int personId) {
        Person person = peopleService.getPerson(personId);
        tasksRepository.save(convertToTask(taskDTO, person));
    }

    public TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task convertToTask(TaskDTO taskDTO, Person person) {
        Task task = modelMapper.map(taskDTO, Task.class);

        task.setCreatedAt(LocalDateTime.now());
        task.setPerson(person);

        return task;
    }
}
