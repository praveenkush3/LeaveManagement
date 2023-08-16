package com.genpact.leavemanagementdemo.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Component
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long empId;

	private String city;
	private String street;

	// Many addresses can be associated with one employee
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Address(Long id, Long empId, String city, String street, Employee employee) {
		super();
		this.id = id;
		this.empId = empId;
		this.city = city;
		this.street = street;
		this.employee = employee;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}