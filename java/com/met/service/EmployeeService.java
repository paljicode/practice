package com.met.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.dao.EmployeeDao;
import com.met.model.Employee;

@Service
public class EmployeeService {

	@Autowiredi
	private EmployeeDao employeeDao;

	public void registerEmployee(Employee employee) {
		// additional validation logic before saving the employee
		if (employee.getPassword().isEmpty() || employee.getName().isEmpty() || employee.getGender().isEmpty()) {
			throw new IllegalArgumentException("Employee details are incomplete");
		}
		employeeDao.saveEmployee(employee);

	}

	public boolean loginUser(String id, String password) {
		Employee employee = employeeDao.findByUsernameAndPassword(id, password);
		return employee != null;
	}

	public String getEmployeeName(String id) {
	    if (id == null) {
	        return ""; // or handle the null case appropriately
	    }
	    // Retrieve the employee's name from the DAO
	    Employee employee = employeeDao.findById(id);
	    if (employee != null) {
	        return employee.getName();
	    }
	    return null; // Employee with the given ID not found
	}
	

	public List<Employee> getAllEmployees() {

		List<Employee> employees = employeeDao.getAllEmployees();

		return employees;
	}

}
