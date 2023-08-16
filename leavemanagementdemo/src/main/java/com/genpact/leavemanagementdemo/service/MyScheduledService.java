package com.genpact.leavemanagementdemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.genpact.leavemanagementdemo.entity.LeaveRequest;

@Service
public class MyScheduledService {
	
	private static final Logger log = LoggerFactory.getLogger(MyScheduledService.class);
	
	@Autowired
	LeaveRequestService leaveRequestService;

    @Scheduled(fixedRate = 60000) // Runs every 10 seconds
    public void myScheduledTask() {
    	log.info("Scheduled task is running...");
    	List<LeaveRequest> leaveReqList = leaveRequestService.getLeaveRequestsWithApplied("applied");
    	leaveRequestService.updateLeaves(leaveReqList);
    }
}
