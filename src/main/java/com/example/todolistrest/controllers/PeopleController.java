package com.example.todolistrest.controllers;

import com.example.todolistrest.models.Person;
import com.example.todolistrest.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PeopleController {
    private final PeopleService peopleService;

    @GetMapping
    public List<Person> getPeople() {
        return peopleService.getPeople();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return peopleService.getPerson(id);
    }
}
