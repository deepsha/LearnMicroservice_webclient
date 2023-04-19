package com.company.app.departmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.departmentservice.entity.Department;
import com.company.app.departmentservice.repository.DepartmentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;
	@Autowired	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public Flux<Department> getAllDepartmentDetails() {
		return departmentRepository.findAll();
	}

	public Mono<Department> getDepartmentDetailsByCode(String code) {
		return departmentRepository.findByDepartmentCode(code);
	}

	public Mono<Department> createDepartment(Department department) {		
		return departmentRepository.save(department);
	}

	public Mono<Department> updateDepartment(Department department) {
		
		return departmentRepository.save(department);
	}
	

	
}
