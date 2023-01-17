package com.example.springmarket.repository;

import com.example.springmarket.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Integer> {
    public Queue findByName(String name);
}
