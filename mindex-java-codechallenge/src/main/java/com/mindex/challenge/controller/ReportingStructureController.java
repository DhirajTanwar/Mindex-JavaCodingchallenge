package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RestController
public class ReportingStructureController {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

	@Autowired
	private ReportingStructureService reportingStructureService;

	/*
	 * Controller that handle the GET request 
	 * PAth: "/reportstructure/{id}"
	 * Param: Employee ID required to fetch the employee Object and later count the no of reports
	 * Return: ReportingStructure object
	 */
	@GetMapping("/reportstructure/{id}")
	public ReportingStructure read(@PathVariable String id) {
		LOG.debug("Received employee, fetching data for employee ID [{}]", id);
		return reportingStructureService.read(id);
	}

}
