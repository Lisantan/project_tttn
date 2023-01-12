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
import com.entities.models.TimeKeepingMonth;
import com.entities.repositories.TimeKeepingMonthRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TimeKeepingMonthController {
	@Autowired
	private TimeKeepingMonthRepository repository;
	
	@GetMapping("/timekeepingmonths")
	public List<TimeKeepingMonth> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/timekeepingmonths")
	public TimeKeepingMonth create(@RequestBody TimeKeepingMonth e) {
		return repository.save(e);
	}
	
	@GetMapping("/timekeepingmonths/{id}")
	public ResponseEntity<TimeKeepingMonth> getById(@PathVariable Integer id){
		TimeKeepingMonth e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TimeKeepingMonth not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/timekeepingmonths/{id}")
	public ResponseEntity<TimeKeepingMonth> update(@PathVariable Integer id,
			@RequestBody TimeKeepingMonth eEdit){
		TimeKeepingMonth e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TimeKeepingMonth not exist with id" + id));
		
		e.setMonth(eEdit.getMonth());
	
		TimeKeepingMonth eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/timekeepingmonths/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		TimeKeepingMonth e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TimeKeepingMonth not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
