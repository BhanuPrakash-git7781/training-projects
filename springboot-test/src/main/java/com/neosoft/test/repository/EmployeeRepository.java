package com.neosoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.test.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
