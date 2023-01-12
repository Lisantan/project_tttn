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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entities.exceptions.ResourceNotFoundException;
import com.entities.models.Position;
import com.entities.repositories.PositionRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PositionController {
	@Autowired
	private PositionRepository repository;
	
	@GetMapping("/positions")
	public List<Position> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/positions")
	public Position create(@RequestBody Position e) {
		return repository.save(e);
	}
	
	@GetMapping("/positions/{id}")
	public ResponseEntity<Position> getById(@PathVariable Integer id){
		Position e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Position not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/positions/{id}")
	public ResponseEntity<Position> update(@PathVariable Integer id,
			@RequestBody Position eEdit){
		Position e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Position not exist with id" + id));
		
		e.setIdPos(eEdit.getIdPos());
		e.setName(eEdit.getName());
	
		Position eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/positions/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Position e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Position not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
