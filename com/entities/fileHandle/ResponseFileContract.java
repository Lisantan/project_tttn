package com.entities.fileHandle;

import com.entities.models.Employee;

public class ResponseFileContract {
	private Integer id;
	private Employee employee;
	private String fileName;
	private String name;
	private String url;
	private String type;
	private long size;

	public ResponseFileContract() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseFileContract(Integer id, Employee employee, String fileName, String name, String url, String type, long size) {
		super();
		this.id = id;
		this.employee = employee;
		this.fileName = fileName;
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
