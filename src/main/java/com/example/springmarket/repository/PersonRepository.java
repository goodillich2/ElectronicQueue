package com.example.springmarket.repository;

import com.example.springmarket.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
    List<Person> findPersonByNameAndSurname(String name, String surname);
}
