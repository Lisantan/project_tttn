package com.entities.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private Integer idEmployee;
	
	private String image;
	
	private String name;
	
	private String gender;
	
	private String dob;
	
	private String email;
	
	private String address;
	
	private String addressPermanent;
	
	private String ethnic;
	
	private String socialCredit;
	
	private String professionalKnowledge;
	
	@ManyToOne
	private Department department;
	
	@ManyToOne
	private Position position;
	
	private String status;

	private Double salary;
	
	private String socialCreditDate;
	
	private String socialCreditPlace;
	
	private String phoneNumber;
	
	private String homeTown;
	
	private String birthPlace;
	
	private String religion;
	
	private String joinDate; 
	

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Employee(Integer id, Integer idEmployee, String image, String name, String gender, String dob, String email,
			String address, String addressPermanent, String ethnic, String socialCredit, String professionalKnowledge,
			Department department, Position position, String status, Double salary, String socialCreditDate,
			String socialCreditPlace, String phoneNumber, String homeTown, String birthPlace, String religion,
			String joinDate) {
		super();
		this.id = id;
		this.idEmployee = idEmployee;
		this.image = image;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.address = address;
		this.addressPermanent = addressPermanent;
		this.ethnic = ethnic;
		this.socialCredit = socialCredit;
		this.professionalKnowledge = professionalKnowledge;
		this.department = department;
		this.position = position;
		this.status = status;
		this.salary = salary;
		this.socialCreditDate = socialCreditDate;
		this.socialCreditPlace = socialCreditPlace;
		this.phoneNumber = phoneNumber;
		this.homeTown = homeTown;
		this.birthPlace = birthPlace;
		this.religion = religion;
		this.joinDate = joinDate;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressPermanent() {
		return addressPermanent;
	}

	public void setAddressPermanent(String addressPermanent) {
		this.addressPermanent = addressPermanent;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getSocialCredit() {
		return socialCredit;
	}

	public void setSocialCredit(String socialCredit) {
		this.socialCredit = socialCredit;
	}

	public String getProfessionalKnowledge() {
		return professionalKnowledge;
	}

	public void setProfessionalKnowledge(String professionalKnowledge) {
		this.professionalKnowledge = professionalKnowledge;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getSocialCreditDate() {
		return socialCreditDate;
	}

	public void setSocialCreditDate(String socialCreditDate) {
		this.socialCreditDate = socialCreditDate;
	}

	public String getSocialCreditPlace() {
		return socialCreditPlace;
	}

	public void setSocialCreditPlace(String socialCreditPlace) {
		this.socialCreditPlace = socialCreditPlace;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
}
