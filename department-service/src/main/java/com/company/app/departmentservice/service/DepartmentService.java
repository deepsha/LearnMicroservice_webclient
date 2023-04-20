package com.company.app.departmentservice.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.departmentservice.entity.Department;
import com.company.app.departmentservice.repository.DepartmentRepository;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepartmentService {
	
	private final Logger logger=Logger.getLogger("EmployeeController");
	private final DepartmentRepository departmentRepository;
	@Autowired	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public Flux<Department> getAllDepartmentDetails() {
		logger.info("inside DepartmentService getAllDepartmentDetails:");
		return departmentRepository.findAll();
	}

	public Mono<Department> getDepartmentDetailsByCode(String code) {
		logger.info("inside DepartmentService getDepartmentDetailsByCode:"+code);
		return departmentRepository.findByDepartmentCode(code);
	}

	public Mono<Department> createDepartment(Department department) {		
		return departmentRepository.save(department);
	}

	public Mono<Department> updateDepartment(Department department) {
		logger.info("inside DepartmentService updateDepartment:"+department.getDepartmentCode());
		return departmentRepository.save(department);
	}
	
	public Mono<Department> deleteDepartmentById(String deptId) {
		logger.info("inside DepartmentService deleteDepartmentById:"+deptId);
		return departmentRepository.findByDepartmentCode(deptId).flatMap(dept->departmentRepository.delete(dept)
				.then(Mono.just(dept)));
	}
	

	
}
