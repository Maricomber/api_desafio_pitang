package com.api.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.dtos.UserDTO;
import com.api.entities.Users;
import com.api.repositories.UserRepository;
import com.api.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository repository;

	private String msgErro;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public List<UserDTO> findAll() throws SQLException {
		log.info("Searching all users...");
		List<Users> users = new ArrayList<>();
		List<UserDTO> userRetorno = new ArrayList<>();
		
		try {
			users = this.repository.findAll();
			users.stream().forEach(user->userRetorno.add(mapper.map(user, UserDTO.class)));
			log.info("Search completed successfully.");
			return userRetorno;
		}catch (Exception e) {
			msgErro = "Error searching all users. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public UserDTO findById(Integer idUser) throws SQLException {
		log.info("Searching user...");
		Users user = new Users();
		try {
			user = this.repository.findByIdUser(idUser);
			if(user == null) {
				throw new NoResultException("No records.");
			}
			log.info("User found.");
			return mapper.map(user, UserDTO.class);
		}catch (Exception e) {
			msgErro = "Error searching user. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public UserDTO save(UserDTO userDTO) throws SQLException {
		if(userDTO == null){
			throw new NoResultException("No records.");
		}
		log.info("Saving user...");
		try {
			Users user = mapper.map(userDTO, Users.class);
			user.getCars().stream().forEach(car->car.setUsers(user));
			return mapper.map(this.repository.save(user), UserDTO.class);
		}catch (Exception e) {
			msgErro = "Error saving user. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public void delete(Integer idUser) throws SQLException {
		Users user = new Users();
		log.info("Deleting user...");
		
		try{
			user = this.repository.findByIdUser(idUser);
			this.repository.delete(user);
		}catch (Exception e) {
			msgErro = "Error user cannot be deleted. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public UserDTO findByEmail(String email) throws UsernameNotFoundException {
		log.info("Searching user...");
		Users user = new Users();
		UserDTO userDTO = new UserDTO();
				
		try {
			user = this.repository.findByEmail(email);
			if(user == null) {
				throw new NoResultException("No records.");
			}
			log.info("User found.");
			
			userDTO.setEmail(user.getEmail());
			userDTO.setPassword(user.getPassword());
			return userDTO;
		}catch (Exception e) {
			msgErro = "Error searching user. "+e.getMessage();
			log.info(msgErro);
			throw new UsernameNotFoundException(msgErro);
		}
	}

}
