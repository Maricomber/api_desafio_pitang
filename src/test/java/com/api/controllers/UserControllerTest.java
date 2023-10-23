package com.api.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.tools.configuration.base.MethodRef;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.dtos.UserDTO;
import com.api.repositories.CarRepository;
import com.api.repositories.UserRepository;
import com.api.services.CarService;
import com.api.services.UserService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@Generated(value = "org.junit-tools-1.1.0")
public class UserControllerTest {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	CarRepository carRepository;
	
	@Mock
	CarService carService;
	
	@Mock
	UserService userService;
	
	@Mock
	UserDTO userDTO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	private UserController createTestSubject() {
		UserController controller = new UserController();
		controller.service = userService;
		return controller;
	}

	@MethodRef(name = "findAll", signature = "(QHttpServletRequest;)QResponseEntity<*>;")
	@Test
	public void testFindAll() throws Exception {
		UserController testSubject;
		HttpServletRequest request = null;
		ResponseEntity<?> result;
		List<UserDTO>users = new ArrayList<>();
		users.add(getUserOk());
		
		when(this.userService.findAll()).thenReturn(users);
		testSubject = createTestSubject();
		result = testSubject.findAll(request);
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@MethodRef(name = "findById", signature = "(QInteger;)QResponseEntity<*>;")
	@Test
	public void testFindById() throws Exception {
		UserController testSubject;
		Integer id = 1;
		ResponseEntity<?> result;

		UserDTO user = getUserOk();
		when(this.userService.findById(1)).thenReturn(user);
		testSubject = createTestSubject();
		result = testSubject.findById(id);
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(user, result.getBody());
	}

	@MethodRef(name = "save", signature = "(QUserDTO;)QResponseEntity<*>;")
	@Test
	public void testSave() throws Exception {
		UserController testSubject;
		ResponseEntity<?> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.save(getUserOk());
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@MethodRef(name = "update", signature = "(QUserDTO;)QResponseEntity<*>;")
	@Test
	public void testUpdate() throws Exception {
		UserController testSubject;
		ResponseEntity<?> result;
		
		// default test
		testSubject = createTestSubject();
		result = testSubject.save(getUserOk());
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@MethodRef(name = "delete", signature = "(QInteger;)QResponseEntity<*>;")
	@Test
	public void testDelete() throws Exception {
		UserController testSubject;
		Integer id = 0;
		ResponseEntity<?> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.delete(id);
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	private UserDTO getUserOk() {
		 return new UserDTO(null, "teste", "teste", "teste@teste.com", new Date(0), "login", "password", "1234-5678", null);
	}
}