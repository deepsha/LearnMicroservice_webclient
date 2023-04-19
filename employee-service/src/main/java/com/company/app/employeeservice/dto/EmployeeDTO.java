package com.company.app.employeeservice.dto;



import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
public class EmployeeDTO {

	private String firstName;

	private String lastName;

	private String role;	

	private String email;	

	private String location;	

	private String employeeStatus;	

	private Double employeeSalary;

	private List<DepartmentDTO> department;
	@Builder
	public EmployeeDTO(String firstName, String lastName, String role, String email, String location,
			String employeeStatus, Double employeeSalary,List<DepartmentDTO> department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.location = location;
		this.employeeStatus = employeeStatus;
		this.employeeSalary = employeeSalary;
		this.department = department;
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

	public List<DepartmentDTO> getDepartment() {
		return department;
	}

	public void setDepartment(List<DepartmentDTO> department) {
		this.department = department;
	}

	

}
