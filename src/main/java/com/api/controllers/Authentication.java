package com.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.configuration.JwtTokenUtil;
import com.api.dtos.ErrorResponseDTO;
import com.api.dtos.JwtRequestDTO;
import com.api.dtos.JwtResponseDTO;
import com.api.dtos.UserDTO;
import com.api.enums.ErrorEnum;
import com.api.services.UserService;
import com.api.services.impl.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
public class Authentication {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	UserService service;
	
	@RequestMapping(value = "/api/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequestDTO authenticationRequest) throws Exception {
		
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new JwtResponseDTO("Bearer "+token));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(ErrorEnum.UNAUTHORIZED));
		}
	
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@ApiOperation(value = "Search for a user by token")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User found successfully."),
		    @ApiResponse(code = 403, message = "You do not have permission to access this resource."),
		    @ApiResponse(code = 500, message = "Error."),
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "api/me")
	public @ResponseBody ResponseEntity<?> findMe(HttpServletRequest request) {
		
		List<String>erros = new ArrayList<>();
		UserDTO userDTO = new UserDTO();
		
		try{
			final String requestTokenHeader = request.getHeader("Authorization");
			String jwtToken = requestTokenHeader.substring(7);
			
			try {
				String email = jwtTokenUtil.getUsernameFromToken(jwtToken);
				Integer id = this.service.findByEmail(email).getIdUser();
				userDTO = this.service.findById(id);
				
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			} 
			
			return ResponseEntity.ok(userDTO);
		}catch (Exception e) {
			erros.add(e.getMessage());
			return ResponseEntity.badRequest().body(erros);
		}
		
	}
	

}
