package com.genpact.leavemanagementdemo.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.genpact.leavemanagementdemo.entity.EmployeeLeaves;
import com.genpact.leavemanagementdemo.repository.EmployeeLeavesRepository;

@Service
public class EmployeeLeavesService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeLeavesService.class);
	
	@Autowired
	private EmployeeLeavesRepository employeeLeavesRepository;
	
	public EmployeeLeaves saveEmployeeLeaves(EmployeeLeaves employeeLeaves) {
        return employeeLeavesRepository.save(employeeLeaves);
    }
	@Cacheable("dataCache")
	public Optional<EmployeeLeaves> findByEmpId(int empId) {
		return employeeLeavesRepository.findById(empId);
	}

	public List<EmployeeLeaves> getAllEmployeesLeaves() {
		return employeeLeavesRepository.findAll();
	}

	public void updateEmployeeLeaves(int empId, String leaveType, int leaveDuration) {
		
		Method getMethod = null;
		Method setMethod = null;
		Integer totalLeaves = 0;
		Integer remainingLeaves =0;
		
		String leaveTypeGetMethod = createGetMethodName(leaveType);
		String leaveTypeSetMethod = createSetMethodName(leaveType);
		
		Optional<EmployeeLeaves> leaveEmployeeOptional = findByEmpId(empId);
		
		if (leaveEmployeeOptional.isPresent()) {
			EmployeeLeaves employeeLeaves = leaveEmployeeOptional.get();
			
			try {
				getMethod = EmployeeLeaves.class.getMethod(leaveTypeGetMethod);
				totalLeaves = (Integer) getMethod.invoke(employeeLeaves);
				
				remainingLeaves = totalLeaves - leaveDuration;
				
				
				setMethod = EmployeeLeaves.class.getMethod(leaveTypeSetMethod, Integer.class);
                setMethod.invoke(employeeLeaves, remainingLeaves);
				
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				log.error("Update EmployeeLeaves object Not success");
				e.printStackTrace();
			}
			employeeLeavesRepository.save(employeeLeaves);
			log.debug("success update EmployeeLeaves object");
        } else {
        	log.info("EmployeeLeaves object is returned null from db");
        }		
		
	}

	private String createGetMethodName(String leaveType) {
		
		String firstChar = leaveType.substring(0, 1).toUpperCase();
        String restOfTheString = leaveType.substring(1);

        String result = "get" + firstChar + restOfTheString;
		return result;
	}
	
	private String createSetMethodName(String leaveType) {
		String firstChar = leaveType.substring(0, 1).toUpperCase();
        String restOfTheString = leaveType.substring(1);

        String result = "set" + firstChar + restOfTheString;
		return result;
	}

}
