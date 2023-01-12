package com.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.models.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{

}
