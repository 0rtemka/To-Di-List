package com.example.todolistrest.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    private int username;

    @OneToMany(mappedBy = "person")
    private List<Task> tasks;
}
