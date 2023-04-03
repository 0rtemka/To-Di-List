package com.example.todolistrest.services;

import com.example.todolistrest.dto.TaskDTO;
import com.example.todolistrest.models.Person;
import com.example.todolistrest.models.Task;
import com.example.todolistrest.repositories.TasksRepository;
import com.example.todolistrest.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TasksService {
    private final TasksRepository tasksRepository;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    public List<Task> getTasks(int person_id) {
        Person person = peopleService.getPerson(person_id);
        return person.getTasks();
    }

    @Transactional
    public void addTask(TaskDTO taskDTO, int personId) {
        Person person = peopleService.getPerson(personId);
        tasksRepository.save(convertToTask(taskDTO, person));
    }

    @Transactional
    public void finishTask(int personId, int taskId) {
        Person person = peopleService.getPerson(personId);
        Optional<Task> optTask = person.getTasks().stream().filter(t -> t.getTask_id() == taskId).findFirst();

        if (optTask.isEmpty()) {
            throw new NotFoundException("Задача не найдена");
        }

        Task task = optTask.get();

        task.setDone(!task.isDone());
        tasksRepository.save(task);
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
