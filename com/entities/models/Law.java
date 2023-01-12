package com.entities.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Law {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String lawName;

	private String name;

	private String type;

	@Lob
	private byte[] data;

	public Law() {
		
	}
	
	public Law(String lawName, String name, String type, byte[] data) {
		super();
		this.lawName = lawName;
		this.name = name;
		this.type = type;
		this.data = data;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLawName() {
		return lawName;
	}


	public void setLawName(String lawName) {
		this.lawName = lawName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}

}
