package com.eduardo.newcadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.eduardo.newcadastro.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	UserDetails findByLogin(String login);
	
}
