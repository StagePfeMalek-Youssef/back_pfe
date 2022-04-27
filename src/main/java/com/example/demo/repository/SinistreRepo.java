package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Contrat;
import com.example.demo.model.Sinistre;

public interface SinistreRepo extends JpaRepository<Sinistre, Long>{

}
