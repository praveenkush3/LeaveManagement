/**
 * 
 */
package com.genpact.leavemanagementdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.leavemanagementdemo.entity.LeaveRequest;

/**
 * @author debabratakundu
 *
 */
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer>{

	//Default findById only for primary key column where Id should be primary column.
	//Added new findByEmpId method explicitly
	Optional<LeaveRequest> findByEmpId(int empId);

	List<LeaveRequest> findByStatus(String status);

}
