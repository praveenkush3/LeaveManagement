/**
 * 
 */
package com.genpact.leavemanagementdemo.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author debabratakundu
 *
 */
@Component
@Entity
@Table(name = "employee_leaves")
public class EmployeeLeaves {
	
	@Id
	private int empId;

    private Integer personalLeave;

    private Integer casualLeave;

    private Integer sickLeave;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getPersonalLeave() {
		return personalLeave;
	}

	public void setPersonalLeave(Integer personalLeave) {
		this.personalLeave = personalLeave;
	}

	public Integer getCasualLeave() {
		return casualLeave;
	}

	public void setCasualLeave(Integer casualLeave) {
		this.casualLeave = casualLeave;
	}

	public Integer getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(Integer sickLeave) {
		this.sickLeave = sickLeave;
	}
    
}