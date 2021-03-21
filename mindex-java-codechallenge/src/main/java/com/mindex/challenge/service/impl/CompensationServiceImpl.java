package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

@Service
public class CompensationServiceImpl implements CompensationService{

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompensationRepository compensationRepository;
    
    
    /*
     * Fetch the Compensation Object for the given Employee ID
     * PAram: Employee ID
     * Returns: Compensation Object
     */
	@Override
	public Compensation read(String id) {
		LOG.debug("In CompensationServiceImpl-Getting Compensation for id [{}]", id);
		
		Employee employee = employeeService.read(id);
        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) {
            throw new RuntimeException("No compensation found for employeeID " + id);
        }

        return compensation;

	}

	/*
     * Creates the Compensation Object in the DB
     * Param: Compensation Object
     * Returns:  created Compensation Object 
     */
	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("In CompensationServiceImpl-Creating Compensation for ", compensation);
		Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
    	compensation.setEmployee(employee);
        compensationRepository.insert(compensation);
        
        return compensation;
	}
	
}
