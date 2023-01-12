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
import com.entities.models.Salary;
import com.entities.repositories.SalaryRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SalaryController {
	@Autowired
	private SalaryRepository repository;
	
	@GetMapping("/salaries")
	public List<Salary> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/salaries")
	public Salary create(@RequestBody Salary e) {
		return repository.save(e);
	}
	
	@GetMapping("/salaries/{id}")
	public ResponseEntity<Salary> getById(@PathVariable Integer id){
		Salary e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/salaries/{id}")
	public ResponseEntity<Salary> update(@PathVariable Integer id,
			@RequestBody Salary eEdit){
		Salary e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not exist with id" + id));
		
		e.setEmployee(eEdit.getEmployee());
		e.setTaxId(eEdit.getTaxId());
		e.setTax(eEdit.getTax());
		e.setTaxLevel(eEdit.getTaxLevel());
		e.setAssuranceMedical(eEdit.getAssuranceMedical());
		e.setAssuranceSocial(eEdit.getAssuranceSocial());
		e.setAssuranceUnemployed(eEdit.getAssuranceUnemployed());
		e.setAssurance(eEdit.getAssuranceMedical() + eEdit.getAssuranceSocial() + eEdit.getAssuranceUnemployed());
		e.setAssurance(eEdit.getAssuranceMedical() + eEdit.getAssuranceSocial() + eEdit.getAssuranceUnemployed());
		
		Salary eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/salaries/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Salary e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
