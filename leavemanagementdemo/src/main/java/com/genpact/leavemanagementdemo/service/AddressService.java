package com.genpact.leavemanagementdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.leavemanagementdemo.entity.Address;
import com.genpact.leavemanagementdemo.repository.AddressRepository;

@Service
public class AddressService {
	
	
	@Autowired
	AddressRepository addressRepository;
	

	public Address addAddress(Address address) {
		return addressRepository.save(address);
	}

	public List<Address> getAddressByEmpId(int empId) {
		return addressRepository.findByEmpId(empId);
	}

}
