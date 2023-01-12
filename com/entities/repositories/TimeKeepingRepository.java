package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.models.TimeKeeping;

public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Integer>{

}
