package com.example.springmarket.service;

import com.example.springmarket.model.Queue;
import com.example.springmarket.model.Queue;
import com.example.springmarket.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;



    public List<Queue> getAll(){return queueRepository.findAll();}
    public Queue getById(int id){
        return queueRepository.getById(id);
    }
    public Queue save(Queue queue){
        return queueRepository.save(queue);
    }
    public void deleteById(int id){
        queueRepository.deleteById(id);
    }
}
