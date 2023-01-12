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
import com.entities.models.Department;
import com.entities.repositories.DepartmentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/departments")
	public List<Department> getAll(){
		return departmentRepository.findAll();
	}
	
	@PostMapping("/departments")
	public Department create(@RequestBody Department department) {
		return departmentRepository.save(department);
	}
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<Department> getById(@PathVariable Integer id){
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id" + id));
		return ResponseEntity.ok(department);
	}
	
	@PutMapping("/departments/{id}")
	public ResponseEntity<Department> update(@PathVariable Integer id,
			@RequestBody Department departmentEdit){
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id" + id));
		
		department.setIdDept(departmentEdit.getIdDept());
		department.setName(departmentEdit.getName());
		department.setManager(departmentEdit.getManager());
	
		Department departmentEdited = departmentRepository.save(department);
		return ResponseEntity.ok(departmentEdited);	
	}
	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id" + id));
		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}