package com.api.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dtos.UserDTO;
import com.api.repositories.UserRepository;
import com.api.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository repository;
	
	@Override
	public List<UserDTO> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findById(Integer idPessoa) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO save(UserDTO UserDTO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer idUser) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
