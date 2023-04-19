package com.company.app.employeeservice.dto;

public class DepartmentDTO {

	private String departmentName;
	
	private String departmentCode;
	
	private String headOfDepartment;

	@Override
	public String toString() {
		return "DepartmentDTO [departmentName=" + departmentName + ", departmentCode=" + departmentCode
				+ ", headOfDepartment=" + headOfDepartment + "]";
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
