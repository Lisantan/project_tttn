package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.models.Law;

@Repository
public interface LawRepository extends JpaRepository<Law, Integer>{

}
