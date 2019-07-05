package com.hcl.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.employee.entity.Employee;
import com.hcl.employee.exceptionHandling.DataNotFoundException;
import com.hcl.employee.exceptionHandling.ResourceNotFoundException;
import com.hcl.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);

	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepository.getOne(employee.getId());
		if (emp != null) {
			return employeeRepository.save(employee);
		} else {
			throw new ResourceNotFoundException("Employee does not exist with id " + employee.getId());
		}

	}

	@Override
	public void deleteAllEmployees() {

		List<Employee> emplist = employeeRepository.findAll();
		if (emplist.size() != 0) {
			employeeRepository.deleteAll();
		} else {
			throw new DataNotFoundException("Data not found");
		}
	}

	@Override
	public Employee getEmployeeById(int id) {

		Optional<Employee> emp = employeeRepository.findById(id);

		if (emp.isPresent()) {
			Employee emp1 = emp.get();
			return emp1;
		} else {
			throw new DataNotFoundException(" Data not found ttttttttttttttt" + id);
		}
	}

	@Override
	public Employee updateEmployeeById(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			Employee emp1 = emp.get();
			emp1.setEmail("aj@gmasil.com");
			emp1.setName("aj");
			return employeeRepository.save(emp1);

		} else {
			throw new DataNotFoundException("User Not Found" + id);
		}	
	}

	@Override
	public void deleteEmployeeById(int id) {
		
		Optional<Employee> emp = employeeRepository.findById(id);

		if (emp.isPresent()) {
			employeeRepository.deleteById(id);
			
		} else {
			throw new DataNotFoundException(" Data not found " + id);
		}
	}

}
