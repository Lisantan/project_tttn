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
import com.entities.models.ActivityHistory;
import com.entities.repositories.ActivityHistoryRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ActivityHistoryController {
	@Autowired
	private ActivityHistoryRepository repository;
	
	@GetMapping("/activities")
	public List<ActivityHistory> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/activities")
	public ActivityHistory create(@RequestBody ActivityHistory e) {
		return repository.save(e);
	}
	
	@GetMapping("/activities/{id}")
	public ResponseEntity<ActivityHistory> getById(@PathVariable Integer id){
		ActivityHistory e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ActivityHistory not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/activities/{id}")
	public ResponseEntity<ActivityHistory> update(@PathVariable Integer id,
			@RequestBody ActivityHistory eEdit){
		ActivityHistory e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ActivityHistory not exist with id" + id));
		
		e.setEmployee(eEdit.getEmployee());
		e.setHistory(eEdit.getHistory());
		e.setDateStart(eEdit.getDateStart());
		e.setDateStart(eEdit.getDateEnd());
		
	
		ActivityHistory eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/activities/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		ActivityHistory e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ActivityHistory not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
