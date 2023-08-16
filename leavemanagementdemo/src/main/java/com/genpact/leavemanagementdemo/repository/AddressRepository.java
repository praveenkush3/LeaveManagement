package com.genpact.leavemanagementdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.leavemanagementdemo.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByEmpId(int i);

}
