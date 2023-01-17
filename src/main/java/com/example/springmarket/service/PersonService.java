package com.example.springmarket.service;

import com.example.springmarket.model.Person;
import com.example.springmarket.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;



    public List<Person> getAll(){return personRepository.findAll();}
    public Person getById(int id){
        return personRepository.getById(id);
    }
    public Person save(Person category){
        return personRepository.save(category);
    }
    public void deleteById(int id){
        personRepository.deleteById(id);
    }


}
