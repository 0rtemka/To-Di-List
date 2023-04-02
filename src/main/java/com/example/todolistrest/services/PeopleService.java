package com.example.todolistrest.services;

import com.example.todolistrest.dto.PersonDTO;
import com.example.todolistrest.models.Person;
import com.example.todolistrest.repositories.PeopleRepository;
import com.example.todolistrest.utils.CreateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final ModelMapper modelMapper;

    public List<Person> getPeople() {
        return peopleRepository.findAll();
    }

    public Person getPerson(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isEmpty()) return null; //TODO: create NotFoundException and throw it if person is empty

        return person.get();
    }

    @Transactional
    public void addPerson(PersonDTO personDTO) {
        Optional<Person> person = peopleRepository.findByUsername(personDTO.getUsername());

        if (person.isPresent()) throw new CreateException("Пользователь с таким именем уже существует");

        peopleRepository.save(convertToPerson(personDTO));
    }

    public PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person convertToPerson(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        person.setTasks(Collections.emptyList());
        return person;
    }
}
