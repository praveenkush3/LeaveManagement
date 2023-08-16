package com.genpact.leavemanagementdemo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.leavemanagementdemo.entity.Address;
import com.genpact.leavemanagementdemo.entity.Employee;
import com.genpact.leavemanagementdemo.entity.EmployeeLeaves;
import com.genpact.leavemanagementdemo.service.AddressService;
import com.genpact.leavemanagementdemo.service.EmployeeLeavesService;
import com.genpact.leavemanagementdemo.service.EmployeeService;



@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeLeavesService employeeLeavesService;
	
	@Autowired
	AddressService addressService;

	@Autowired
	Employee employee;

	@PostMapping("/addEmployee")
	@Transactional
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

		Employee savedEmployee = employeeService.saveEmployee(employee);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);

	}

	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/getEmployeeById/{empId}")
	public ResponseEntity<Optional<Employee>> findEmployeeByEmpId(@PathVariable int empId) {
		
		List<Address> addresses = addressService.getAddressByEmpId(empId);
		
		Optional<Employee> employees = employeeService.findByEmpId(empId);
		
		if(employees.isPresent()) {
			employees.get().setAddresses(addresses);
		}
		return ResponseEntity.ok(employees);
	}
	
	@PostMapping("/addLeaves")
	@Transactional
	public ResponseEntity<EmployeeLeaves> addLeaves(@RequestBody EmployeeLeaves employeeLeaves) {

		EmployeeLeaves savedEmployeeLeaves = employeeLeavesService.saveEmployeeLeaves(employeeLeaves);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeLeaves);

	}
	
	@GetMapping("/getAllEmployeeLeaves")
	public ResponseEntity<List<EmployeeLeaves>> getAllEmployeesLeaves() {
		List<EmployeeLeaves> employeesLeaves = employeeLeavesService.getAllEmployeesLeaves();
		return ResponseEntity.ok(employeesLeaves);
	}
	
	@GetMapping("/getLeaveOfEmployee/{empId}")
	public ResponseEntity<Optional<EmployeeLeaves>> findLeavesByEmpId(@PathVariable int empId) {
		Optional<EmployeeLeaves> employeeLeaves = employeeLeavesService.findByEmpId(empId);
		return ResponseEntity.ok(employeeLeaves);
	}
	
	@PostMapping("/addAddress")
	@Transactional
	public ResponseEntity<Address> addAddress(@RequestBody Address address) {

		Address savedAddress = addressService.addAddress(address);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);

	}

}
