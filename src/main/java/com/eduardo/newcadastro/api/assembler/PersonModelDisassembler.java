package com.eduardo.newcadastro.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.input.PersonInputModel;
import com.eduardo.newcadastro.domain.model.Person;

@Component
public class PersonModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Person toDomainObject(PersonInputModel personInput) {
		return modelMapper.map(personInput, Person.class);
	}
	
	public void copyToDomainObject(PersonInputModel personInput, Person person) {
		modelMapper.map(personInput, person);
	}
	
}
