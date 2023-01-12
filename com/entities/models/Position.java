package com.entities.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private Integer idPos;
	
	private String name;

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Position(Integer id, Integer idPos, String name) {
		super();
		this.id = id;
		this.idPos = idPos;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPos() {
		return idPos;
	}

	public void setIdPos(Integer idPos) {
		this.idPos = idPos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
