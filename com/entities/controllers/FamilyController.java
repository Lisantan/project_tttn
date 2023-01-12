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
import com.entities.models.Family;
import com.entities.repositories.FamilyRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FamilyController {

	@Autowired
	private FamilyRepository repository;
	
	@GetMapping("/families")
	public List<Family> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/families")
	public Family create(@RequestBody Family e) {
		return repository.save(e);
	}
	
	@GetMapping("/families/{id}")
	public ResponseEntity<Family> getById(@PathVariable Integer id){
		Family e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Family not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/families/{id}")
	public ResponseEntity<Family> update(@PathVariable Integer id,
			@RequestBody Family eEdit){
		Family e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Family not exist with id" + id));
		
		e.setEmployee(eEdit.getEmployee());
		e.setName(eEdit.getName());
		e.setRelationship(eEdit.getRelationship());
		e.setBirthYear(eEdit.getBirthYear());
		e.setJob(eEdit.getJob());
		e.setAddress(eEdit.getAddress());
		
		Family eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/families/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Family e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Family not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
