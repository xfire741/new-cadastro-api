package com.eduardo.newcadastro.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.input.PhoneInputModel;
import com.eduardo.newcadastro.domain.model.Phone;

@Component
public class PhoneModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Phone toDomainObject(PhoneInputModel phoneInput) {
		return modelMapper.map(phoneInput, Phone.class);
	}
	
	public void copyToDomainObject(PhoneInputModel phoneInput, Phone phone) {
		modelMapper.map(phoneInput, phone);
	}
	
}
