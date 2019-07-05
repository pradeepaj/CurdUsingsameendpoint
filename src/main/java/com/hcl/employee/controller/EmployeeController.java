package com.hcl.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.employee.entity.Employee;
import com.hcl.employee.service.EmployeeService;

@RestController
@RequestMapping("/bank")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/profiles")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.addEmployee(employee);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

	@GetMapping("/profiles")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> emp = employeeService.getAllEmployees();
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@PutMapping("/profiles")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.updateEmployee(employee);

		return new ResponseEntity<>(emp, HttpStatus.OK);

	}

	@DeleteMapping("/profiles")
	public ResponseEntity deleteAllEmployees() {
		employeeService.deleteAllEmployees();
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/profiles/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		Employee emp = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@PutMapping("/profiles/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") int id) {
		Employee emp = employeeService.updateEmployeeById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	@DeleteMapping("/profiles/{id}")
	public ResponseEntity deleteEmployeeById(@PathVariable("id") int id){
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity (HttpStatus.OK);
	}
	
	
	

}
