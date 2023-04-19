package com.company.app.employeeservice.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@EntityScan
@Table(name="EMPLOYEES")
public class Employee {
	
	@Id
	private int id;

	@Column(value="First_Name")
	private String firstName;
	
	@Column(value="Last_Name")
	private String lastName;
	
	@Column(value="Role")
	private String role;
	
	@Column(value="Email")
	private String email;
	
	@Column(value="Location")
	private String location;
	
	@Column(value="Deptartment_Code")
	private String departmentCode;
	
	@Column(value="Status")
	private String employeeStatus;
	
	@Column(value="Salary")
	private Double employeeSalary;
	
	public Employee() {}
	public Employee(String firstName, String lastName, String role, String email, String location,
			String departmentCode, String employeeStatus,Double employeeSalary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.location = location;
		this.departmentCode = departmentCode;
		this.employeeStatus = employeeStatus;
		this.employeeSalary= employeeSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	public Double getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(Double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

}
