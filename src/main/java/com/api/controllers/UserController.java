package com.api.controllers;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public @ResponseBody ResponseEntity<?> findAll(HttpServletRequest request) {
		
		List<String>erros = new ArrayList<>();
		
		try{
			List<UserDTO>UserDTO = this.service.findAll();
			
			if(UserDTO.isEmpty()) {
				throw new SQLDataException("No records.");
			}
			return ResponseEntity.ok(UserDTO);
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
		
	}
	
	@ApiOperation(value = "Search for a user by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User found successfully."),
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
	
	@ApiOperation(value = "Create a new user.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User created successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
	    @ApiResponse(code = 500, message = "Error"),
	})
	@PostMapping
	public @ResponseBody ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
		
		List<String>erros = new ArrayList<>();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		try {
			if(userDTO == null) {
				throw new InvalidAttributesException("Missing fields.");
			}
			userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
			return ResponseEntity.ok(this.service.save(userDTO));
			
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
		
	}
	
	@ApiOperation(value = "Update a user record")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User updated successfully."),
	    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
	    @ApiResponse(code = 500, message = "Error."),
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> update(@RequestBody UserDTO UserDTO){
		
		List<String>erros = new ArrayList<>();
		
		try {
			if(UserDTO.getIdUser() == (null)) {
				throw new InvalidAttributesException("Missing fields.");
			}
			return ResponseEntity.ok(this.service.save(UserDTO));
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
	}

	@ApiOperation(value = "Deletes a user record by id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User deleted successfully."),
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