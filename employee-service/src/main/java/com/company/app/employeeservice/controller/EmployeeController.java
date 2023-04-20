package com.company.app.employeeservice.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.company.app.employeeservice.dto.DepartmentDTO;
import com.company.app.employeeservice.dto.EmployeeDTO;
import com.company.app.employeeservice.entity.Employee;
import com.company.app.employeeservice.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	private final Logger logger=Logger.getLogger("EmployeeController");
	
	private final EmployeeService employeeService;
	
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employee")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Flux<Employee>> getAllEmployeeDetails()
	{
		Flux<Employee> e = employeeService.getAllEmployeeDetails();
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}
	

	@GetMapping("/employee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Employee>> getEmployeeDetailsById(@PathVariable("id") int id)
	{

		Mono<Employee> e = employeeService.getEmployeeDetailsById(id);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);
	}
	
	@PostMapping("/employee")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Employee>> createEmployee(@RequestBody Employee employee)
	{

		Mono<Employee> e = employeeService.createEmployee(employee);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);
	}
	
	@DeleteMapping("/employee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Employee>> deleteEmployeeById(@PathVariable("id") int id)
	{

		Mono<Employee> e = employeeService.deleteEmployeeById(id);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);
	}
	//calling department-service using webclient from employee-service
	
	@GetMapping("/employee/department")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Flux<DepartmentDTO>> getAllDepartmentDetails()
	{
		Flux<DepartmentDTO> e = employeeService.getAllDepartmentDetails();
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}
	@GetMapping("/employees/{deptCode}")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<List<EmployeeDTO>> getAllEmployeewithDeptDetailsByCode(@PathVariable String deptCode) throws InterruptedException
	{
		logger.info("getAllEmployeewithDeptDetailsByCode:"+deptCode);
		List<EmployeeDTO> e = employeeService.getAllEmployeewithDeptDetailsByCode(deptCode);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}
	
	@GetMapping("/employee/department/{code}")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Mono<DepartmentDTO>> getDepartmentByCode(@PathVariable String code)
	{
		Mono<DepartmentDTO> e = employeeService.getDepartmentByCode(code);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}
	@PostMapping("/employee/department")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Mono<DepartmentDTO>> createDepartment(@RequestBody DepartmentDTO department)
	{
		logger.info("createDepartment:"+department);
		Mono<DepartmentDTO> e = employeeService.createDepartment(department);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}
	@PutMapping("/employee/department")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Mono<DepartmentDTO>> updateDepartment(@RequestBody DepartmentDTO department)
	{
		logger.info("createDepartment:"+department);
		Mono<DepartmentDTO> e = employeeService.updateDepartment(department);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}
	
	@DeleteMapping("/employee/department/{deptCode}")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Mono<DepartmentDTO>> deleteDepartmentByDeptCode(@PathVariable String deptCode)
	{
		logger.info("deleteDepartmentByDeptCode:"+deptCode);
		Mono<DepartmentDTO> e = employeeService.deleteDepartmentByDeptCode(deptCode);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}
}
