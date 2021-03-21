package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

	private String reportingStructureUrl;
	private Employee dummyEmployee;

	@Autowired
	private EmployeeRepository employeeRepository;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		reportingStructureUrl = "http://localhost:" + port + "/reportstructure/{id}";

		dummyEmployee = employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
	}

	/*
	 * Testing Read functionality of ReportStructureServiceImpl.
	 * 
	 */
	@Test
	public void testRead() {

		ReportingStructure resportStructure = new ReportingStructure(dummyEmployee, 2);

		ResponseEntity<ReportingStructure> reportingStructureResponse = restTemplate.getForEntity(reportingStructureUrl,
				ReportingStructure.class, dummyEmployee.getEmployeeId());

		ReportingStructure readReportingStructure = (ReportingStructure) reportingStructureResponse.getBody();
		assertReportingStructureEquivalence(readReportingStructure, resportStructure);

	}

	@After
	public void teardown() {
		dummyEmployee = null;
		reportingStructureUrl = null;

	}
	
	private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
        
    }

}
