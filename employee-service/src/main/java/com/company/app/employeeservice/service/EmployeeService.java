package com.company.app.employeeservice.service;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.company.app.employeeservice.dto.DepartmentDTO;
import com.company.app.employeeservice.dto.EmployeeDTO;
import com.company.app.employeeservice.entity.Employee;
import com.company.app.employeeservice.repository.EmployeeRepository;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

	private final EmployeeRepository  employeeRepository;
	private final Logger logger=Logger.getLogger("EmployeeService");

	private final WebClient webClient;

	@Autowired
	public EmployeeService(WebClient.Builder webClientBuilder,EmployeeRepository  employeeRepository) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:8081/company").build();
		this.employeeRepository=employeeRepository;
	}



	public Flux<Employee> getAllEmployeeDetails() {

		return employeeRepository.findAll();
	}
	public Mono<Employee> getEmployeeDetailsById(int id) {
			return employeeRepository.findById(id);
	}

	public Mono<Employee> createEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}
	//calling department-service using webclient from employee-service
	public Flux<DepartmentDTO> getAllDepartmentDetails() {

		return this.webClient.get().uri("/department")
				.retrieve().bodyToFlux(DepartmentDTO.class);
	}
	
	
	public Mono<DepartmentDTO> getDepartmentByCode(String deptcode) {

		return this.webClient.get().uri("/department/{deptcode}",deptcode)
				.retrieve().bodyToMono(DepartmentDTO.class);
	}


	public Mono<DepartmentDTO> createDepartment(DepartmentDTO department) {		
		return this.webClient.post().uri("/department/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(department),DepartmentDTO.class)
				.retrieve()
				.bodyToMono(DepartmentDTO.class);
	}
	public Mono<DepartmentDTO> updateDepartment(DepartmentDTO department) {		
		return this.webClient.put().uri("/department")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(department),DepartmentDTO.class)
				.retrieve()
				.bodyToMono(DepartmentDTO.class);
				
	}



	
	public List<EmployeeDTO> getAllEmployeewithDeptDetailsByCode(String deptCode) throws InterruptedException {
		List<EmployeeDTO> employeelist=null;
		Flux<Employee> employeeFlux=employeeRepository.findEmployeesBydepartmentCode(deptCode);
		Mono<DepartmentDTO> departmentMono=getDepartmentByCode(deptCode);
		Thread.sleep(50);
		departmentMono.subscribe(data -> System.out.println("departmentMono data: " + data));
		logger.info("getAllEmployeewithDeptDetailsByCode");
		List<DepartmentDTO> deptList=  new ArrayList<>();
	//	dept = departmentMono.subscribe();
		 Flux<DepartmentDTO> fluxFromMono = Flux.from(departmentMono);
		 fluxFromMono.collectList().subscribe(deptList::addAll);
				//departmentMono.blockOptional().get();blockOptional() is blocking, which is not supported in thread reactor-http-nio-4
		List<Employee> empList=new ArrayList<>();
		
		// empList=(List<Employee>) employeeFlux.collectList().block();//blocking is not working so we use subscribe
		employeeFlux.collectList().subscribe(empList::addAll);
		logger.info("empList:"+empList.size());
		logger.info("deptList:"+deptList.size());
		employeelist = empList.stream()
		        .map(emp -> new EmployeeDTO(emp.getFirstName(), emp.getLastName(),emp.getRole(),emp.getEmail(),emp.getLocation(),emp.getEmployeeStatus(),emp.getEmployeeSalary(), deptList))
		        .collect(Collectors.toList());
	
		 
		return employeelist;
	}



}
