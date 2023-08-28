package com.eduardo.newcadastro.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.input.LoginInputModel;
import com.eduardo.newcadastro.api.model.input.UserInputModel;
import com.eduardo.newcadastro.domain.model.User;

@Component
public class UserModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public User toDomainObjectLogin(LoginInputModel loginInputModel) {
		return modelMapper.map(loginInputModel, User.class);
	}
	
	public User toDomainObjectRegister(UserInputModel userInputModel) {
		return modelMapper.map(userInputModel, User.class);
	}
	
}
