package com.neosoft.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.test.bean.Employee;
import com.neosoft.test.exception.ResourceNotFoundException;
import com.neosoft.test.repository.EmployeeRepository;

@Service
public class EmployeeService implements EmployeeServiceMethods {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();		
	}

	@Override
	public Employee getEmployeeById(long id) {		
		return employeeRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("Employee","Id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {		
		
		if(employeeRepository.findById(id).isPresent()) {
			employee.setId(id);
			employeeRepository.save(employee);
		}			
		else 
			throw new ResourceNotFoundException("Employee","Id",id);
		
		return employee;
	}

	@Override
	public void deleteEmployee(long id) {		
		employeeRepository.findById(id)
		.orElseThrow( () -> new ResourceNotFoundException("Employee","Id",id));
		employeeRepository.deleteById(id);
	}
}
