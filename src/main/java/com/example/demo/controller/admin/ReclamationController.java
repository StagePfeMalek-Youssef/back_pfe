package com.example.demo.controller.admin;

import java.util.Date;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.model.ObjetName;
import com.example.demo.model.Produit;
import com.example.demo.model.Reclamation;
import com.example.demo.model.User;
import com.example.demo.repository.ReclamationRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.service.admin.ReclamationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/reclamations")
public class ReclamationController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReclamationRepo reclamationRepo;
	@Autowired
	private ReclamationService reclamationService;

	@GetMapping
	public List<Reclamation> index() {
		return reclamationRepo.findAllByOrderByUpdatedAtDesc();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Reclamation> show(@PathVariable(value = "id") Long id) {
		Optional<Reclamation> reclamation = reclamationRepo.findById(id);
		return reclamation
				.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping("/{username}")
	public Reclamation createReclamation(@PathVariable String username,
			@RequestBody Reclamation reclamation) {

		User user = userRepository.findByUsername(username);
		reclamation.setUsers(user);
		reclamation.setCreatedAt(new Date());
		reclamation.setUpdatedAt(null);

		return reclamationRepo.save(reclamation);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateReclamation(@RequestBody Reclamation reclamationRequert,
			@PathVariable(value = "id") Long id) {
		Optional<Reclamation> reclamation = reclamationRepo.findById(id);
		if (reclamation.isPresent()) {
			reclamation.get().setNumDec(reclamationRequert.getNumDec());
			reclamation.get().setMessage(reclamationRequert.getMessage());
			reclamation.get().setObjet(reclamationRequert.getObjet());
			reclamation.get().setUpdatedAt(new Date());
			reclamationRepo.save(reclamation.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteReclamation(@PathVariable(value = "id") Long id) {
		Optional<Reclamation> reclamation = reclamationRepo.findById(id);
		if (reclamation.isPresent()) {
			reclamationRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
