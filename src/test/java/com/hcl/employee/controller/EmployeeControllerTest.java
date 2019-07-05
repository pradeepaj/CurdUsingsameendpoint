package com.hcl.employee.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.employee.entity.Employee;
import com.hcl.employee.service.EmployeeServiceImpl;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestContext.class, EmployeeController.class})
@WebAppConfiguration
public class EmployeeControllerTest {
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeServiceImpl employeeService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void addEmployeetest() throws JsonProcessingException, Exception {
		Employee emp=new Employee(1, "pradeep", "aj@gmail.com");
		ResponseEntity<Employee> expResult=new ResponseEntity<>(emp, HttpStatus.CREATED);
		when(employeeService.addEmployee(emp)).thenReturn(emp);
		mockMvc.perform(post("/bank/profiles").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp))).andReturn();
		ResponseEntity<Employee> result=employeeController.addEmployee(emp);
		assertEquals(expResult, result);
	}
	
	@Test
	public void getAllEmployeesTest() throws JsonProcessingException, Exception {
		Employee emp=new Employee(1, "pradeep", "aj@gmail.com");
		Employee emp1=new Employee(2, "aj", "pradeep@gmail.com");
		List<Employee> emplist=new ArrayList<>();
		emplist.add(emp);
		emplist.add(emp1);
		ResponseEntity<List<Employee>> expResult=new ResponseEntity<>(emplist,HttpStatus.OK);
		when(employeeService.getAllEmployees()).thenReturn(emplist);
		mockMvc.perform(get("/bank/profiles").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emplist))).andReturn();
		ResponseEntity<List<Employee>> actResult=employeeController.getAllEmployees();
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void updateEmployeeTest() throws JsonProcessingException, Exception {
		//Employee emp=new Employee(1, "pradeep", "aj@gmail.com");
		Employee emp1=new Employee(1, "aj", "pradeep@gmail.com");
		ResponseEntity<Employee> expResult=new ResponseEntity<>(emp1, HttpStatus.OK);
		when(employeeService.updateEmployee(emp1)).thenReturn(emp1);
		mockMvc.perform(put("/bank/profiles").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp1))).andReturn();
		ResponseEntity<Employee> actResult=employeeController.updateEmployee(emp1);
		assertEquals(expResult, actResult);
	}
	@Test
	public void deleteAllEmployeesTest() throws Exception {
		ResponseEntity exres=new ResponseEntity(HttpStatus.OK);
		doNothing().when(employeeService).deleteAllEmployees();
		mockMvc.perform(delete("/bank/profiles").contentType(MediaType.APPLICATION_JSON));
		ResponseEntity res=employeeController.deleteAllEmployees();
		assertEquals(exres, res);
	}
	
	@Test
	public void getEmployeeByIdTest() throws JsonProcessingException, Exception {
		Employee emp=new Employee(1, "pradeep", "aj@gmail.com");
		ResponseEntity<Employee> expResult=new ResponseEntity<>(emp, HttpStatus.OK);
		when(employeeService.getEmployeeById(Mockito.anyInt())).thenReturn(emp);
		mockMvc.perform(get("bank/profiles/{id}",1).contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp))).andReturn();
		ResponseEntity<Employee> actResult=employeeController.getEmployeeById(1);
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void updateEmployeeByIdTest() throws JsonProcessingException, Exception {
		Employee emp=new Employee(1, "pradeep", "aj@gmail.com");
		ResponseEntity<Employee> expResult=new ResponseEntity<>(emp, HttpStatus.OK);
		when(employeeService.updateEmployeeById(Mockito.anyInt())).thenReturn(emp);
		mockMvc.perform(put("bank/profiles/{id}",1).contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp))).andReturn();
		ResponseEntity<Employee> actResult=employeeController.updateEmployeeById(1);
		assertEquals(expResult, actResult);
	}
	@Test
	public void deleteEmployeeByIdTest() throws Exception {
		ResponseEntity expResult=new ResponseEntity(HttpStatus.OK);
		doNothing().when(employeeService).deleteEmployeeById(Mockito.anyInt());
		mockMvc.perform(delete("bank/profiles/{id}",1).contentType(MediaType.APPLICATION_JSON)).andReturn();
		ResponseEntity res=employeeController.deleteEmployeeById(1);
		assertEquals(expResult, res);
	}
	
	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}

}
