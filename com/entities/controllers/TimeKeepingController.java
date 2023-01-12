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
import com.entities.models.TimeKeeping;
import com.entities.repositories.TimeKeepingRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TimeKeepingController {
	@Autowired
	private TimeKeepingRepository repository;
	
	@GetMapping("/timekeepings")
	public List<TimeKeeping> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/timekeepings")
	public TimeKeeping create(@RequestBody TimeKeeping e) {
		return repository.save(e);
	}
	
	@GetMapping("/timekeepings/{id}")
	public ResponseEntity<TimeKeeping> getById(@PathVariable Integer id){
		TimeKeeping e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TimeKeeping not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/timekeepings/{id}")
	public ResponseEntity<TimeKeeping> update(@PathVariable Integer id,
			@RequestBody TimeKeeping eEdit){
		TimeKeeping e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TimeKeeping not exist with id" + id));
		
		e.setEmployee(eEdit.getEmployee());
		e.setTimeKeepingMonth(eEdit.getTimeKeepingMonth());
		e.setTimeCount(eEdit.getTimeCount());
	
		TimeKeeping eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/timekeepings/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		TimeKeeping e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TimeKeeping not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
