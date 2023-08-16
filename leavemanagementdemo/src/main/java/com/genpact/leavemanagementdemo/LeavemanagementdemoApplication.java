package com.genpact.leavemanagementdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Enable scheduling support
@EnableCaching
public class LeavemanagementdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeavemanagementdemoApplication.class, args);
	}

}
