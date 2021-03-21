package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
	
	//private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImplTest.class);

	private String readCompensationUrl;
	private String createCompensationUrl;
	private Employee dummyEmployee;

	@Autowired
	private EmployeeRepository employeeRepository;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		dummyEmployee = employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
		readCompensationUrl = "http://localhost:" + port + "/compensation/{id}";
		createCompensationUrl = "http://localhost:" + port + "/compensation";
	}

	@Test
	public void testReadCreate() {

		Compensation dummyCompensation = new Compensation();
		dummyCompensation.setEmployee(dummyEmployee);
		dummyCompensation.setSalary("100000");
		dummyCompensation.setEffectiveDate(Instant.parse("2021-03-19T11:52:00Z"));

		// Testing Create functionality of CompensationServiceImpl.
		ResponseEntity<Compensation> createdCompensationResponse = restTemplate.postForEntity(createCompensationUrl,
				dummyCompensation, Compensation.class);
		Compensation createdCompensation = (Compensation) createdCompensationResponse.getBody();
		
//		LOG.debug("dummyCompensation", dummyCompensation.toString());
//		LOG.debug("createdCompensation", createdCompensation.toString());
		
		assertEquals(HttpStatus.OK, createdCompensationResponse.getStatusCode());
		//assertCompensationEquivalence(dummyCompensation, createdCompensation);

		// Testing Read functionality of CompensationServiceImpl.
		ResponseEntity<Compensation> readCompensationResponse = restTemplate.getForEntity(readCompensationUrl,
				Compensation.class, "03aa1462-ffa9-4978-901b-7c001562cf6f");
		Compensation readCompensation = (Compensation) readCompensationResponse.getBody();
		assertEquals(HttpStatus.OK, readCompensationResponse.getStatusCode());
		//assertCompensationEquivalence(readCompensation, createdCompensation);

	}

	private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
		assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
		assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
		assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
		assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
		assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
		assertEquals(expected.getSalary(), actual.getSalary());
		assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
	}

}
