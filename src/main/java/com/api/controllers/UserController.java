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

import com.api.dtos.ResponseDTO;
import com.api.dtos.UserDTO;
import com.api.services.UserService;

import io.swagger.annotations.*;


@RestController
@RequestMapping(path = {"/api/users"})
public class UserController {

	@Autowired
	UserService service;
	
	@ApiOperation(value = "Search a list of all users")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "List found successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDTO<List<UserDTO>>> findAll(HttpServletRequest request) {
		
		ResponseDTO<List<UserDTO>> response = new ResponseDTO<>();
		List<String>erros = new ArrayList<>();
		
		try{
			List<UserDTO>UserDTO = this.service.findAll();
			
			if(UserDTO.isEmpty()) {
				throw new SQLDataException("User not found.");
			}
			response.setData(UserDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Search for a user by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User found successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@GetMapping(path = {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDTO<UserDTO>> findById(@PathVariable Integer id){
		
		List<String>erros = new ArrayList<>();
		ResponseDTO<UserDTO>response = new ResponseDTO<>();
		UserDTO UserDTO;
		
		try {
			
			if(id == null) {
				throw new InvalidAttributesException("Campos em branco");
			}
			
			UserDTO= this.service.findById(id);
			response.setData(UserDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Create a new user.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User created successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
	    @ApiResponse(code = 500, message = "Error"),
	})
	@PostMapping
	public @ResponseBody ResponseEntity<ResponseDTO<UserDTO>> save(@RequestBody UserDTO UserDTO) {
		
		ResponseDTO<UserDTO> response = new ResponseDTO<>();
		List<String>erros = new ArrayList<>();
		
		try {

			if(UserDTO == null) {
				throw new InvalidAttributesException("Campos vazios. ");
			}
			UserDTO = this.service.save(UserDTO);
			response.setData(UserDTO);
			return ResponseEntity.ok(response);
			
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Update a user record")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User updated successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDTO<UserDTO>> update(@RequestBody UserDTO UserDTO){
		
		List<String>erros = new ArrayList<>();
		ResponseDTO<UserDTO>response = new ResponseDTO<>();
		
		try {
			UserDTO = this.service.save(UserDTO);
			if(UserDTO == (null)) {
				return ResponseEntity.badRequest().body(response);
			}
		response.setData(UserDTO);
		return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation(value = "Deletes a user record by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User deleted successfully."),
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
			response.setData("User deleted successfully!");
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}