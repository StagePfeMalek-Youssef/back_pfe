package com.example.demo.controller.admin;

import java.util.List;

import com.example.demo.model.Sujet;
import com.example.demo.model.User;
import com.example.demo.repository.SujetRepo;
import com.example.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sujets")
@CrossOrigin("*")
public class SujetController {

    @Autowired
    private SujetRepo sujetR;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Sujet> getAllSujets() {
        return sujetR.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Sujet> getSujetById(@PathVariable long id) {
        Sujet sujet = sujetR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sujet not exist with id:" + id,
                        "Sujet not exist with id:" + id));
        return ResponseEntity.ok(sujet);
    }

    @PostMapping("/{username}")
    public Sujet createSujet(@RequestBody Sujet sujet, @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        sujet.setUser(user);
        return sujetR.save(sujet);
    }

    @PutMapping("{id}")
    public ResponseEntity<Sujet> updateSujet(@PathVariable long id, @RequestBody Sujet sujetDetails) {
        Sujet updateSujet = sujetR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sujet not exist with id:" + id,
                        "Sujet not exist with id:" + id));

        updateSujet.setTitreSujet(sujetDetails.getTitreSujet());
        sujetR.save(updateSujet);

        return ResponseEntity.ok(updateSujet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteSujet(@PathVariable long id) {

        Sujet sujet = sujetR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sujet not exist with id:" + id,
                        "Sujet not exist with id:" + id));

        sujetR.delete(sujet);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
