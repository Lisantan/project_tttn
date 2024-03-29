package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
