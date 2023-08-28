package com.eduardo.newcadastro.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.PersonModel;
import com.eduardo.newcadastro.api.model.PersonResumModel;
import com.eduardo.newcadastro.domain.model.Person;

@Component
public class PersonModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PersonModel toModel(Person person) {
		
		PersonModel personModel = new PersonModel();
		
		modelMapper.map(person, personModel);
		
		personModel.setCpf(cpfMasked(person.getCpf()));
		
		if(person.getAddress() != null) {
		personModel.getAddress().setCep(AddressModelAssembler.formatCEP(personModel.getAddress().getCep()));
		}
		
		return personModel;
		
	}
	
public PersonResumModel toResumModel(Person person) {
		
		PersonResumModel personResumModel = new PersonResumModel();
		
		modelMapper.map(person, personResumModel);
		
		personResumModel.setCpf(cpfMasked(person.getCpf()));
		
		return personResumModel;
		
	}
	
	public List<PersonResumModel> toCollectionModel(List<Person> persons) {
		
		return persons.stream()
			.map(person -> toResumModel(person))
			.collect(Collectors.toList());
		
	}
	
	public static String cpfMasked(String cpf) {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
 
 	}
	
}
