package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.models.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer>{
	
}
