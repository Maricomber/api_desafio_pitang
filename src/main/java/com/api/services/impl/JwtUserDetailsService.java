package com.api.services.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.dtos.UserDTO;
import com.api.services.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
    private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDTO user = userService.findByEmail(userName);
		
		if (user.getEmail().equals(userName)) {
			return new User(userName, user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
	}
}
