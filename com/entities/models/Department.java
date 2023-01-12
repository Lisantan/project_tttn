package com.entities.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private Integer idDept;
	
	private String name;
	
	private String manager;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(Integer id, Integer idDept, String name, String manager) {
		super();
		this.id = id;
		this.idDept = idDept;
		this.name = name;
		this.manager = manager;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDept() {
		return idDept;
	}

	public void setIdDept(Integer idDept) {
		this.idDept = idDept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
}
