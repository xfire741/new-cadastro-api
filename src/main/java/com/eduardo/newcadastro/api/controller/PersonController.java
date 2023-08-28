package com.eduardo.newcadastro.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.newcadastro.api.assembler.PersonModelAssembler;
import com.eduardo.newcadastro.api.assembler.PersonModelDisassembler;
import com.eduardo.newcadastro.api.assembler.PhoneModelAssembler;
import com.eduardo.newcadastro.api.model.PersonModel;
import com.eduardo.newcadastro.api.model.PersonResumModel;
import com.eduardo.newcadastro.api.model.input.PersonInputModel;
import com.eduardo.newcadastro.domain.model.Person;
import com.eduardo.newcadastro.domain.repository.PersonRepository;
import com.eduardo.newcadastro.domain.service.PersonRegisterService;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonModelAssembler personModelAssembler;
	
	@Autowired
	private PersonRegisterService personRegisterService;
	
	@Autowired
	private PersonModelDisassembler personModelDisassembler;
	
	@Autowired
	private PhoneModelAssembler phoneModelAssembler;
	
	@GetMapping
	public List<PersonResumModel> list() {
		return personModelAssembler.toCollectionModel(personRepository.findAll());
	}
	
	@GetMapping("/{personId}")
	public PersonModel find(@PathVariable Long personId) {
		
		Person person = personRegisterService.findOrFail(personId);
		
		PersonModel personModel = personModelAssembler.toModel(person);
		
		personModel.setPhones(phoneModelAssembler.toCollectionResumModel(person.getPhones()));
		
		return personModel;
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PersonModel save(@RequestBody @Valid PersonInputModel personInputModel) {
		Person person = personModelDisassembler.toDomainObject(personInputModel);
		
		return personModelAssembler.toModel(personRegisterService.save(person));
	}
	
	@DeleteMapping("{personId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long personId) {
		personRegisterService.delete(personId);
	}
	
	@PutMapping("/{personId}")
	public PersonModel atualizar(@PathVariable Long personId, @RequestBody @Valid PersonInputModel personInputModel) {
		
		Person nowPerson = personRegisterService.findOrFail(personId);
		personModelDisassembler.copyToDomainObject(personInputModel, nowPerson);
		
		return personModelAssembler.toModel(personRegisterService.save(nowPerson));
	}
	
}
