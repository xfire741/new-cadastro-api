package com.eduardo.newcadastro.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.newcadastro.api.assembler.UserModelDisassembler;
import com.eduardo.newcadastro.api.model.LoginResponseModel;
import com.eduardo.newcadastro.api.model.input.LoginInputModel;
import com.eduardo.newcadastro.api.model.input.UserInputModel;
import com.eduardo.newcadastro.domain.exception.InvalidLoginException;
import com.eduardo.newcadastro.domain.exception.UserWithSameLoginException;
import com.eduardo.newcadastro.domain.model.User;
import com.eduardo.newcadastro.domain.repository.UserRepository;
import com.eduardo.newcadastro.domain.service.UserRegisterService;
import com.eduardo.newcadastro.infraestructure.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private UserModelDisassembler userModelDisassembler;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRegisterService registerService;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginInputModel userInputModel) {
		try {
		UsernamePasswordAuthenticationToken usernamePassword = 
				new UsernamePasswordAuthenticationToken(userInputModel.getLogin(), userInputModel.getPassword());
		
		Authentication auth = this.authenticationManager.authenticate(usernamePassword);
		
		 String token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseModel(token)); 
		} catch (Exception e) {
			throw new InvalidLoginException();
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserInputModel userInputModel) {
		if(this.userRepository.findByLogin(userInputModel.getLogin()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(userInputModel.getPassword());
		
		userInputModel.setPassword(encryptedPassword);
		
		User user = userModelDisassembler.toDomainObjectRegister(userInputModel);
		
		registerService.save(user);
		
		return ResponseEntity.ok().build();
		
		} catch (Exception e) {
			throw new UserWithSameLoginException();
		}
		
	}
	
}
