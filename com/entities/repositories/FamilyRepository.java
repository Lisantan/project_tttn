package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.models.Family;

public interface FamilyRepository extends JpaRepository<Family, Integer>{

}
