package com.api.controllers;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;
import javax.servlet.http.HttpServletRequest;

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
import com.api.dtos.ResponseDTO;
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
	public @ResponseBody ResponseEntity<ResponseDTO<List<CarDTO>>> findAll(HttpServletRequest request) {
		
		ResponseDTO<List<CarDTO>> response = new ResponseDTO<>();
		List<String>erros = new ArrayList<>();
		
		try{
			List<CarDTO>CarDTO = this.service.findAll();
			
			if(CarDTO.isEmpty()) {
				throw new SQLDataException("Car not found.");
			}
			response.setData(CarDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Search for a car by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car found successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@GetMapping(path = {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDTO<CarDTO>> findById(@PathVariable Integer id){
		
		List<String>erros = new ArrayList<>();
		ResponseDTO<CarDTO>response = new ResponseDTO<>();
		CarDTO CarDTO;
		
		try {
			
			if(id == null) {
				throw new InvalidAttributesException("Campos em branco");
			}
			
			CarDTO= this.service.findById(id);
			response.setData(CarDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Create a new car.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car created successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
	    @ApiResponse(code = 500, message = "Error"),
	})
	@PostMapping
	public @ResponseBody ResponseEntity<ResponseDTO<CarDTO>> save(@RequestBody CarDTO CarDTO) {
		
		ResponseDTO<CarDTO> response = new ResponseDTO<>();
		List<String>erros = new ArrayList<>();
		
		try {

			if(CarDTO == null) {
				throw new InvalidAttributesException("Campos vazios. ");
			}
			CarDTO = this.service.save(CarDTO);
			response.setData(CarDTO);
			return ResponseEntity.ok(response);
			
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Update a car record")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car updated successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDTO<CarDTO>> update(@RequestBody CarDTO CarDTO){
		
		List<String>erros = new ArrayList<>();
		ResponseDTO<CarDTO>response = new ResponseDTO<>();
		
		try {
			CarDTO = this.service.save(CarDTO);
			if(CarDTO == (null)) {
				return ResponseEntity.badRequest().body(response);
			}
		response.setData(CarDTO);
		return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation(value = "Deletes a car record by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Car deleted successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<ResponseDTO<String>> delete(@PathVariable Integer id) {
		
		ResponseDTO<String> response = new ResponseDTO<>();
		List<String>erros = new ArrayList<>();
		
		try {
			if(id == null) {
				throw new InvalidAttributesException("Campos em branco. ");
			}
			this.service.delete(id);
			response.setData("Car deleted successfully!");
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
