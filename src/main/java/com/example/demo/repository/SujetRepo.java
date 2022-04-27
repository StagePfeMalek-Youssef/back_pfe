package com.example.demo.repository;

import com.example.demo.model.Sujet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SujetRepo extends JpaRepository<Sujet, Long>{
    
}
