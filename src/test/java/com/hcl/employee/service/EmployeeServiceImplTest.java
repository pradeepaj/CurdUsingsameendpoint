package com.hcl.employee.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.employee.entity.Employee;
import com.hcl.employee.exceptionHandling.DataNotFoundException;
import com.hcl.employee.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void getEmployeeByIdTest() {
		Employee emp = new Employee(1, "pradeep", "aj@gmail.com");

		when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(emp));
		Employee actResult = employeeService.getEmployeeById(emp.getId());
		assertEquals(emp, actResult);

	}

	@Test(expected = DataNotFoundException.class)
	public void getEmployeeByIdExceptionTest() {

		when(employeeRepository.findById(Mockito.anyInt())).thenThrow(new DataNotFoundException("Data not found"));
		employeeService.getEmployeeById(8);

	}

}
