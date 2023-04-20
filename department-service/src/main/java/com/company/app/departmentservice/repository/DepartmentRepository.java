package com.company.app.departmentservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.company.app.departmentservice.entity.Department;

import reactor.core.publisher.Mono;
@Repository
public interface DepartmentRepository extends R2dbcRepository<Department, Integer>{

	Mono<Department> findByDepartmentCode(String departmentCode);

}
