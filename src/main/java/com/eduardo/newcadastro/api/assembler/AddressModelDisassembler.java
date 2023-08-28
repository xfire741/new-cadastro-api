package com.eduardo.newcadastro.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.input.AddressInputModel;
import com.eduardo.newcadastro.domain.model.Address;

@Component
public class AddressModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Address toDomainObject(AddressInputModel addressInputModel) {
		return modelMapper.map(addressInputModel, Address.class);
	}
	
	public void copyToDomainObject(AddressInputModel addressInput, Address address) {
		modelMapper.map(addressInput, address);
	}
	
}
