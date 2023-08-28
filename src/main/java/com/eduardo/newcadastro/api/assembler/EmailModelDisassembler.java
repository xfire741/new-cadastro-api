package com.eduardo.newcadastro.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.input.EmailInputModel;
import com.eduardo.newcadastro.domain.model.Email;

@Component
public class EmailModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Email toDomainObject(EmailInputModel emailInputModel) {
		return modelMapper.map(emailInputModel, Email.class);
	}
	
	public void copyToDomainObject(EmailInputModel emailInput, Email email) {
		modelMapper.map(emailInput, email);
	}
	
}
