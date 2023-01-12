package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.models.TimeKeepingMonth;

public interface TimeKeepingMonthRepository extends JpaRepository<TimeKeepingMonth, Integer>{

}
