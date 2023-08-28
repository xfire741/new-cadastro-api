package com.eduardo.newcadastro.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.newcadastro.domain.model.User;
import com.eduardo.newcadastro.domain.repository.UserRepository;

@Service
public class UserRegisterService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User save(User user) {
		
		return userRepository.save(user);
	}
	
}
