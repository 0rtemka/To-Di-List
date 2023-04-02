package com.example.todolistrest.repositories;

import com.example.todolistrest.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
