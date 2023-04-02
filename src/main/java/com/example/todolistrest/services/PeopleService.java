package com.example.todolistrest.services;

import com.example.todolistrest.dto.PersonDTO;
import com.example.todolistrest.models.Person;
import com.example.todolistrest.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
