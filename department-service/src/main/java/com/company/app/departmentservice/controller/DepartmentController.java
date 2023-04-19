package com.company.app.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.departmentservice.entity.Department;
import com.company.app.departmentservice.service.DepartmentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/company")
public class DepartmentController {
	
	private final DepartmentService  departmentService;
	
	@Autowired	
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/department")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Flux<Department>> getAllDepartmentDetails()
	{
		Flux<Department> e = departmentService.getAllDepartmentDetails();
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}

	@GetMapping("/department/{code}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Department>> getDepartmentDetailsByCode(@PathVariable("code") String code)
	{

		Mono<Department> e = departmentService.getDepartmentDetailsByCode(code);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);
	}
	
	@PostMapping("/department/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Department> createDepartment(@RequestBody Department department) {
		 return departmentService.createDepartment(new Department(department.getDepartmentName(), department.getDepartmentCode(), department.getHeadOfDepartment()));
	}
	
	@PutMapping("/department")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Department> updateDepartment(@RequestBody Department department) {
		 return departmentService.updateDepartment(new Department(department.getDepartmentName(), department.getDepartmentCode(), department.getHeadOfDepartment()));
	}

}
