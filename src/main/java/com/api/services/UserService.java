package com.api.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.dtos.UserDTO;

@Service
public interface UserService {

	List<UserDTO>findAll() throws SQLException;
	
	UserDTO findById(Integer idPessoa) throws SQLException;
	
	UserDTO save(UserDTO UserDTO) throws SQLException;
	
	void delete(Integer idUser) throws SQLException;
}
