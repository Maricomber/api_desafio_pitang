package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.entities.Users;

@Transactional(readOnly = true)
@Repository
public interface  UserRepository extends JpaRepository<Users, Integer>{
	Users findByIdUser(Integer idUser);
	Users findByEmail(String email);
}
