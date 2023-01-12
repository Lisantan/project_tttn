package com.entities.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Employee employee;
	
	private Integer taxId;
	
	private Double tax;
	
	private String taxLevel;
	
	private Double assuranceMedical;
	
	private Double assuranceSocial;
	
	private Double assuranceUnemployed;
	
	private Double assurance;
	
	private Double unionFee;

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salary(Integer id, Employee employee, Integer taxId, Double tax, String taxLevel, Double assuranceMedical,
			Double assuranceSocial, Double assuranceUnemployed, Double assurance, Double unionFee) {
		super();
		this.id = id;
		this.employee = employee;
		this.taxId = taxId;
		this.tax = tax;
		this.taxLevel = taxLevel;
		this.assuranceMedical = assuranceMedical;
		this.assuranceSocial = assuranceSocial;
		this.assuranceUnemployed = assuranceUnemployed;
		this.assurance = assuranceMedical + assuranceSocial + assuranceUnemployed;
		this.unionFee = unionFee;
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

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getTaxLevel() {
		return taxLevel;
	}

	public void setTaxLevel(String taxLevel) {
		this.taxLevel = taxLevel;
	}

	public Double getAssuranceMedical() {
		return assuranceMedical;
	}

	public void setAssuranceMedical(Double assuranceMedical) {
		this.assuranceMedical = assuranceMedical;
	}

	public Double getAssuranceSocial() {
		return assuranceSocial;
	}

	public void setAssuranceSocial(Double assuranceSocial) {
		this.assuranceSocial = assuranceSocial;
	}

	public Double getAssuranceUnemployed() {
		return assuranceUnemployed;
	}

	public void setAssuranceUnemployed(Double assuranceUnemployed) {
		this.assuranceUnemployed = assuranceUnemployed;
	}

	public Double getAssurance() {
		return assurance;
	}

	public void setAssurance(Double assurance) {
		this.assurance = this.assuranceMedical + this.assuranceSocial + this.assuranceUnemployed;
	}

	public Double getUnionFee() {
		return unionFee;
	}

	public void setUnionFee(Double unionFee) {
		this.unionFee = unionFee;
	}

}
