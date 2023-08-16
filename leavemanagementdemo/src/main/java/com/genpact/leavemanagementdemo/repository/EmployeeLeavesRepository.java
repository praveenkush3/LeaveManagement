package com.genpact.leavemanagementdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.leavemanagementdemo.entity.EmployeeLeaves;

public interface EmployeeLeavesRepository extends JpaRepository<EmployeeLeaves, Integer> {

}