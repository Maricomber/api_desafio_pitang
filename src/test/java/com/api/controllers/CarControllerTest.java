package com.api.controllers;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;
import org.springframework.http.ResponseEntity;

import com.api.dtos.CarDTO;

@Generated(value = "org.junit-tools-1.1.0")
public class CarControllerTest {

	private CarController createTestSubject() {
		return new CarController();
	}

	@MethodRef(name = "findAll", signature = "(QHttpServletRequest;)QResponseEntity<*>;")
	@Test
	public void testFindAll() throws Exception {
		CarController testSubject;
		HttpServletRequest request = null;
		ResponseEntity<?> result;

		// default test 1
		testSubject = createTestSubject();
		result = testSubject.findAll(request);
	}

	@MethodRef(name = "findAll", signature = "(QHttpServletRequest;)QResponseEntity<*>;")
	@Test
	public void testFindAll_1() throws Exception {
		CarController testSubject;
		HttpServletRequest request = null;
		ResponseEntity<?> result;

		// default test 1
		testSubject = createTestSubject();
		result = testSubject.findAll(request);
	}

	@MethodRef(name = "findById", signature = "(QInteger;)QResponseEntity<*>;")
	@Test
	public void testFindById() throws Exception {
		CarController testSubject;
		Integer id = 0;
		ResponseEntity<?> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.findById(id);
	}

	@MethodRef(name = "save", signature = "(QCarDTO;)QResponseEntity<*>;")
	@Test
	public void testSave() throws Exception {
		CarController testSubject;
		CarDTO carDTO = null;
		ResponseEntity<?> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.save(carDTO);
	}

	@MethodRef(name = "update", signature = "(QCarDTO;)QResponseEntity<*>;")
	@Test
	public void testUpdate() throws Exception {
		CarController testSubject;
		CarDTO carDTO = null;
		ResponseEntity<?> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.update(carDTO);
	}

	@MethodRef(name = "delete", signature = "(QInteger;)QResponseEntity<*>;")
	@Test
	public void testDelete() throws Exception {
		CarController testSubject;
		Integer id = 0;
		ResponseEntity<?> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.delete(id);
	}
}