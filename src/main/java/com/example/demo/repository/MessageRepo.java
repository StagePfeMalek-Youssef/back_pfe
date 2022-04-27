package com.example.demo.repository;

import com.example.demo.model.Messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Messages, Long>{
    
}
