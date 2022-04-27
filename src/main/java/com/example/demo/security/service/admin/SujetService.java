package com.example.demo.security.service.admin;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Sujet;
import com.example.demo.repository.SujetRepo;

import org.springframework.beans.factory.annotation.Autowired;

public class SujetService {
    @Autowired
    SujetRepo sujetR;

    public Sujet saveSujet(Sujet sujet) {
        return sujetR.save(sujet);
    }

    public List<Sujet> getSujetInfos() {
        return sujetR.findAll();
    }

    public Optional<Sujet> getSujetById(long idSu) {
        return sujetR.findById(idSu);
    }

    public boolean checkExistedSujet(long idSu) {
        if (sujetR.existsById((long) idSu)) {
            return true;
        }
        return false;
    }

    public Sujet updateSujet(Sujet sujet) {
        return sujetR.save(sujet);
    }

    public void deleteSujetById(long idSu) {
        sujetR.deleteById(idSu);
    }
}
