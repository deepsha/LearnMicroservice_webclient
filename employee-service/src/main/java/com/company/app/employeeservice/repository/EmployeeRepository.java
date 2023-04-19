package com.company.app.employeeservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.company.app.employeeservice.entity.Employee;

import reactor.core.publisher.Flux;
@Repository
public interface EmployeeRepository extends  R2dbcRepository<Employee,Integer> {
	
	Flux<Employee> findEmployeesBydepartmentCode(String departmentCode);

}
