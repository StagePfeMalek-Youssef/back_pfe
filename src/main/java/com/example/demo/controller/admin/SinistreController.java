package com.example.demo.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.demo.model.Contrat;
import com.example.demo.model.Sinistre;
import com.example.demo.repository.ContratRepo;
import com.example.demo.repository.SinistreRepo;

@RestController
@RequestMapping("/api/v1/sinistres")
@CrossOrigin("*")
public class SinistreController {

    @Autowired
    private SinistreRepo sinistreR;
    @Autowired
    private ContratRepo contratRepo;
    
    
	@GetMapping
    public List<Sinistre> getAllSinistres(){
        return sinistreR.findAll();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Sinistre> getSinistreById(@PathVariable  long id){
        Sinistre sinistre = sinistreR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sinistre not exist with id:" + id,"Sinistre not exist with id:" + id));
        return ResponseEntity.ok(sinistre);
    }
    
    @PostMapping("{type}")
    public Sinistre createSinistre(@PathVariable  String type,@RequestBody Sinistre sinistre) {
    	Contrat contrat = contratRepo.findByType(type);
    	sinistre.setContrat(contrat);
    	sinistre.setDateDeclaration(new Date());
    	sinistre.setUpdatedAt(null);
        return sinistreR.save(sinistre);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Sinistre> updateSinistre(@PathVariable long id,@RequestBody Sinistre sinistreDetails) {
        Sinistre updateSinistre = sinistreR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sinistre not exist with id: " + id, "Sinistre not exist with id: " + id));

        updateSinistre.setNumSinistre(sinistreDetails.getNumSinistre());
        updateSinistre.setDateSurvenance(sinistreDetails.getDateDeclaration());
        updateSinistre.setEtat(sinistreDetails.getEtat());;
		updateSinistre.setLieu(sinistreDetails.getLieu());;
		updateSinistre.setDateDeclaration(sinistreDetails.getDateDeclaration());;
		updateSinistre.setType(sinistreDetails.getType());
		updateSinistre.setUpdatedAt(new Date());
        sinistreR.save(updateSinistre);

        return ResponseEntity.ok(updateSinistre);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteSinistre(@PathVariable long id){

        Sinistre sinistre = sinistreR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sinistre not exist with id: " + id, null));

        sinistreR.delete(sinistre);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    
}