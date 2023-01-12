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
import com.entities.models.Bonus;
import com.entities.repositories.BonusRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BonusController {
	@Autowired
	private BonusRepository repository;
	
	@GetMapping("/bonuses")
	public List<Bonus> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/bonuses")
	public Bonus create(@RequestBody Bonus e) {
		return repository.save(e);
	}
	
	@GetMapping("/bonuses/{id}")
	public ResponseEntity<Bonus> getById(@PathVariable Integer id){
		Bonus e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bonus not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/bonuses/{id}")
	public ResponseEntity<Bonus> update(@PathVariable Integer id,
			@RequestBody Bonus eEdit){
		Bonus e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bonus not exist with id" + id));
		
		e.setEmployee(eEdit.getEmployee());
		e.setReason(eEdit.getReason());
		e.setMoney(eEdit.getMoney());
	
		Bonus eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/bonuses/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Bonus e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bonus not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
