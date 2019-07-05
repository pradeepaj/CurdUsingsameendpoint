package com.hcl.employee.service;

import java.util.List;

import com.hcl.employee.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee updateEmployee(Employee employee);

	void deleteAllEmployees();

	Employee getEmployeeById(int id);

	Employee updateEmployeeById(int id);

	void deleteEmployeeById(int id);

}
