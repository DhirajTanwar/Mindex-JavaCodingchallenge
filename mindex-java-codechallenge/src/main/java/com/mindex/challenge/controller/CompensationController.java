package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

public class CompensationController {
	
	 private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);
	 
	 @Autowired
	 CompensationService compensationService;
	 
	 
	 	/*
	 	 * Controller that handle the GET request 
	 	 * PAth: "/compensation/{id}"
	 	 * Param: Employee ID
	 	 * Return: Compensation object
	 	 */
		@GetMapping("/compensation/{id}")
	    public Compensation read(@PathVariable String id) {
	        LOG.debug("Received request to read Compensation for ID [{}]", id);

	        return compensationService.read(id);
	    }
	  
		/*
	 	 * Controller that handle the POST request 
	 	 * PAth: "/compensation/"
	 	 * Param: Compensation object to create Compensation object in DB
	 	 * Return: Compensation object that is created
	 	 */
		@PostMapping("/compensation")
	    public Compensation create(@RequestBody Compensation compensation) {
	        LOG.debug("Received request to create Compensation for [{}]", compensation);

	        return compensationService.create(compensation);
	    }

}
