package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entities.models.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	@Query(value="Select count(*) from report", nativeQuery = true)
	int reportCount();
}
