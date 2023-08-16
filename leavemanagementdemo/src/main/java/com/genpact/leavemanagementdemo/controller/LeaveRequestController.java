/**
 * 
 */
package com.genpact.leavemanagementdemo.controller;

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

import com.genpact.leavemanagementdemo.entity.LeaveRequest;
import com.genpact.leavemanagementdemo.service.LeaveRequestService;

/**
 * @author debabratakundu
 *
 */
@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {
	
	private static final Logger log = LoggerFactory.getLogger(LeaveRequestController.class);
	
	@Autowired
	LeaveRequestService leaveRequestService;
	
	@PostMapping("/applyLeaves")
	@Transactional
	public ResponseEntity<LeaveRequest> applyLeaves(@RequestBody LeaveRequest leaveRequest) {

		LeaveRequest applyLeaves = leaveRequestService.applyLeaves(leaveRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(applyLeaves);

	}
	
	@GetMapping("/getLeavesOfEmployeeById/{empId}")
	@Transactional
	public ResponseEntity<Optional<LeaveRequest>> getAppliedLeavesByEmpId(@PathVariable int empId) {

		Optional<LeaveRequest> applyLeaves = leaveRequestService.getAppliedLeavesByEmpId(empId);

		return ResponseEntity.status(HttpStatus.CREATED).body(applyLeaves);

	}

}
