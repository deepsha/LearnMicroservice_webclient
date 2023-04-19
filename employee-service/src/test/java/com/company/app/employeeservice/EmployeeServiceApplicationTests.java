package com.company.app.employeeservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.company.app.employeeservice.controller.EmployeeController;
import com.company.app.employeeservice.entity.Employee;
import com.company.app.employeeservice.repository.EmployeeRepository;
import com.company.app.employeeservice.service.EmployeeService;

import reactor.core.publisher.Mono;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class EmployeeServiceApplicationTests {

	private final String URL="http://localhost:8082/company/employee";
	
	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	EmployeeRepository repository;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(repository);
		Assertions.assertNotNull(webTestClient);
	}

	@Test
	@Order(1)
	public void testCreateEmployee() {
		
		Employee employee=new Employee("Philip", "Latra", "Senior", "tarzan@gmail.com", "NewYork",
				"B14", "Active",30000.00);
		
		webTestClient.post().uri(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(employee), Employee.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.firstName").isNotEmpty()
				.jsonPath("$.firstName").isEqualTo("Philip")
				.jsonPath("$.lastName").isEqualTo("Latra")
				.jsonPath("$.role").isEqualTo("Senior")
				.jsonPath("$.email").isEqualTo("tarzan@gmail.com")
				.jsonPath("$.location").isEqualTo("NewYork")
				.jsonPath("$.departmentCode").isEqualTo("B14")
				.jsonPath("$.employeeStatus").isEqualTo("Active");
	}

	@Test
	@Order(2)
	public void testGetAllEmployees() {
		webTestClient.get().uri(URL)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Employee.class);
	}
	@Test
	@Order(2)
	public void testGetEmployeeDetailsById() {
		
		Employee employee=new Employee("Philip", "Latra", "Senior", "tarzan@gmail.com", "NewYork",
				"B14", "Active",30000.00);
		String uri=URL+"/{id}";				
		webTestClient.get().uri(uri,5)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
		.jsonPath("$.firstName").isNotEmpty()
		.jsonPath("$.id").isEqualTo(5)
		.jsonPath("$.firstName").isEqualTo("Philip")
		.jsonPath("$.lastName").isEqualTo("Latra")
		.jsonPath("$.role").isEqualTo("Senior")
		.jsonPath("$.email").isEqualTo("tarzan@gmail.com")
		.jsonPath("$.location").isEqualTo("NewYork")
		.jsonPath("$.departmentCode").isEqualTo("B14")
		.jsonPath("$.employeeStatus").isEqualTo("Active");
	}


}
