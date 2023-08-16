package com.genpact.leavemanagementdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.leavemanagementdemo.entity.Address;
import com.genpact.leavemanagementdemo.entity.Employee;
import com.genpact.leavemanagementdemo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    
    @Autowired
    Address address;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public Employee saveEmployee(Employee employee) {
    	
    	List<Address> addresses = new ArrayList<>();
    	addresses = employee.getAddresses();
    	
    	
    	
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

	public Optional<Employee> findByEmpId(int empId) {
		return employeeRepository.findById(empId);
	}

    // Add other service methods as needed
}
