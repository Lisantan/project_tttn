package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.models.ActivityHistory;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Integer>{

}
