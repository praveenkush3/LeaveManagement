package com.genpact.leavemanagementdemo.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.leavemanagementdemo.entity.LeaveRequest;
import com.genpact.leavemanagementdemo.repository.LeaveRequestRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaveRequestService {
	
	private static final Logger log = LoggerFactory.getLogger(LeaveRequestService.class);
	
	@Autowired
	LeaveRequestRepository leaveRequestRepository;
	
	@Autowired
	EmployeeLeavesService employeeLeavesService;

	public LeaveRequest applyLeaves(LeaveRequest leaveRequest) {
		return leaveRequestRepository.save(leaveRequest);
	}

	public Optional<LeaveRequest> getAppliedLeavesByEmpId(int empId) {
		return leaveRequestRepository.findByEmpId(empId);
	}
	
	public List<LeaveRequest> getLeaveRequestsWithApplied(String status){
		return leaveRequestRepository.findByStatus("applied");
	}
	@Transactional
	public void updateLeaves(List<LeaveRequest> leaveReqList) {
		
		for (LeaveRequest leaveRequest : leaveReqList) {
			
			leaveRequest.setStatus("approved");
			
			leaveRequestRepository.save(leaveRequest);
			
			int empId = leaveRequest.getEmpId();
			String leaveType = leaveRequest.getLeaveType();
			int leaveDuration = (int) (ChronoUnit.DAYS.between(leaveRequest.getFromDate(), leaveRequest.getToDate())+1);
			
			employeeLeavesService.updateEmployeeLeaves(empId,leaveType,leaveDuration);
			
			
			
		}
		
	}

}
