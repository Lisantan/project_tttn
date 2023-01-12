package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.models.Bonus;

public interface BonusRepository extends JpaRepository<Bonus, Integer>{

}
