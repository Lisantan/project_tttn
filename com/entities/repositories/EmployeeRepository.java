package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entities.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByStatus(String status);
	
	@Query(value="Select count(*) from employee where status = \"Chính thức\"  ", nativeQuery = true)
	int employeeCount();
	
	@Query(value="Select count(*) from employee where status = \"Đang tuyển\"  ", nativeQuery = true)
	int recruitCount();
	
	@Query(value="Select sum(salary) from employee", nativeQuery = true)
	int salaryCount();
	
}
