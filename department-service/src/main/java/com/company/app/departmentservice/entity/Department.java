package com.company.app.departmentservice.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EntityScan
@Table(name="DEPARTMENTS")
public class Department {
	
	@Id
	private int id;
	@Column(value="Department_Name")
	private String departmentName;
	@Column(value="Department_Code")
	private String departmentCode;
	@Column(value="Head_Of_Department")
	private String headOfDepartment;
	
	
	public Department() {}
	public Department( String departmentName, String departmentCode, String headOfDepartment) {
		
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
		this.headOfDepartment = headOfDepartment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getHeadOfDepartment() {
		return headOfDepartment;
	}
	public void setHeadOfDepartment(String headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}
	
	

}
