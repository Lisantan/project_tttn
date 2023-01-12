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
import com.entities.models.Employee;
import com.entities.repositories.EmployeeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findByStatus("Chính thức");
	}
	
	@GetMapping("/recruits")
	public List<Employee> getAllRecruit(){
		return employeeRepository.findByStatus("Đang tuyển");
	}
	
	@GetMapping("/employees/count")
	public int getEmployeeCount(){
		return employeeRepository.employeeCount();
	}
	
	@GetMapping("/employees/salaryCount")
	public int getSalaryCount(){
		return employeeRepository.salaryCount();
	}
	
	@GetMapping("/recruits/count")
	public int getRecruitCount(){
		return employeeRepository.recruitCount();
	}
	
	@PostMapping("/employees")
	public Employee create(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Integer id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id" + id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> update(@PathVariable Integer id,
			@RequestBody Employee employeeEdit){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id" + id));
		
		employee.setIdEmployee(employeeEdit.getIdEmployee());
		
		
		Employee employeeEdited = employeeRepository.save(employee);
		return ResponseEntity.ok(employeeEdited);	
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id" + id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
