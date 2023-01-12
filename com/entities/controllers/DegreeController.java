package com.entities.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entities.exceptions.ResourceNotFoundException;
import com.entities.models.Degree;
import com.entities.repositories.DegreeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DegreeController {
	@Autowired
	private DegreeRepository repository;
	
	@GetMapping("/degrees")
	public List<Degree> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/degrees")
	public Degree create(@RequestBody Degree e) {
		return repository.save(e);
	}
	
	@GetMapping("/degrees/{id}")
	public ResponseEntity<Degree> getById(@PathVariable Integer id){
		Degree e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Degree not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@DeleteMapping("/degrees/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Degree e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Degree not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
