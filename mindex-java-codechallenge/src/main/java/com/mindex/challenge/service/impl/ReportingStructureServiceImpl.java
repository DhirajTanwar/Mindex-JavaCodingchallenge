package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;
	
	
	/*
	 * Fetch the ReportingStructure Object for given Employee ID
	 * Param: Employee ID
	 * Returns: ReportingStructure object
	 */
	@Override
	public ReportingStructure read(String id) {
		LOG.debug("Getting employee with id [{}]", id);
		
		Employee employee = employeeService.read(id);
		int reportsCount = employeeService.getCountOfReports(id);
		ReportingStructure reportingStructure = new ReportingStructure(employee, reportsCount);
		return reportingStructure;
	}

}
