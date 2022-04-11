package com.neosoft.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.test.bean.Employee;
import com.neosoft.test.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employees")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable long id){
		return employeeService.getEmployeeById(id);
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable long id) {
		return employeeService.updateEmployee(employee, id);
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.deleteEmployee(id);
	}
	
	
	
	
}
