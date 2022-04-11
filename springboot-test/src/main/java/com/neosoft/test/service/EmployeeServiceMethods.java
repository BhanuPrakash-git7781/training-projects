package com.neosoft.test.service;

import java.util.List;

import com.neosoft.test.bean.Employee;

public interface EmployeeServiceMethods {

	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	Employee updateEmployee(Employee employee, long id);
	void deleteEmployee(long id);	
}
