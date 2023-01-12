package com.entities.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TimeKeeping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private TimeKeepingMonth timeKeepingMonth;
	
	private Integer timeCount;

	public TimeKeeping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeKeeping(Integer id, Employee employee, TimeKeepingMonth timeKeepingMonth, Integer timeCount) {
		super();
		this.id = id;
		this.employee = employee;
		this.timeKeepingMonth = timeKeepingMonth;
		this.timeCount = timeCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TimeKeepingMonth getTimeKeepingMonth() {
		return timeKeepingMonth;
	}

	public void setTimeKeepingMonth(TimeKeepingMonth timeKeepingMonth) {
		this.timeKeepingMonth = timeKeepingMonth;
	}

	public Integer getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(Integer timeCount) {
		this.timeCount = timeCount;
	}

}
