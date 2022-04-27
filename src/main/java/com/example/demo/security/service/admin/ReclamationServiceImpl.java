package com.example.demo.security.service.admin;

import com.example.demo.model.Reclamation;
import com.example.demo.repository.ReclamationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Autowired
    private ReclamationRepo reclamationR;

    @Override
    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationR.save(reclamation);
    }

}
