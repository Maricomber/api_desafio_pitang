package com.api.controllers;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.dtos.CarDTO;
import com.api.services.CarService;

import io.swagger.annotations.*;


@RestController
@RequestMapping(path = {"/api/car"})
public class CarController {

	@Autowired
	CarService service;
	
	@ApiOperation(value = "Search a list of all cars")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "List found successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> findAll(HttpServletRequest request) {
		
		List<String>erros = new ArrayList<>();
		
		try{
			List<CarDTO>carDTO = this.service.findAll();
			
			if(carDTO.isEmpty()) {
				throw new SQLDataException("No records.");
			}
			return ResponseEntity.ok(carDTO);
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
		
	}
	
	@ApiOperation(value = "Search for a car by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car found successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@GetMapping(path = {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> findById(@PathVariable Integer id){
		
		List<String>erros = new ArrayList<>();
		
		try {
			if(id == null) {
				throw new InvalidAttributesException("Missing fields.");
			}
			return ResponseEntity.ok(this.service.findById(id));
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
		
	}
	
	@ApiOperation(value = "Create a new car.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car created successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
	    @ApiResponse(code = 500, message = "Error"),
	})
	@PostMapping
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody CarDTO carDTO) {
		
		List<String>erros = new ArrayList<>();
		try {
			if(carDTO == null) {
				throw new InvalidAttributesException("Missing fields.");
			}
			
			return ResponseEntity.ok(this.service.save(carDTO));
			
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
		
	}
	
	@ApiOperation(value = "Update a car record")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car updated successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody CarDTO carDTO){
		
		List<String>erros = new ArrayList<>();
		
		try {
			if(carDTO.getIdCar() == (null)) {
				throw new InvalidAttributesException("Missing fields.");
			}
			return ResponseEntity.ok(this.service.save(carDTO));
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
	}

	@ApiOperation(value = "Deletes a car record by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car deleted successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable Integer id) {
		
		List<String>erros = new ArrayList<>();
		
		try {
			if(id == null) {
				throw new InvalidAttributesException("Missing fields.");
			}
			this.service.delete(id);
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
		return ResponseEntity.ok("");
	}
}
