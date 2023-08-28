package com.eduardo.newcadastro.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.newcadastro.api.assembler.AddressModelAssembler;
import com.eduardo.newcadastro.api.assembler.AddressModelDisassembler;
import com.eduardo.newcadastro.api.model.AddressModel;
import com.eduardo.newcadastro.api.model.input.AddressInputModel;
import com.eduardo.newcadastro.domain.model.Address;
import com.eduardo.newcadastro.domain.repository.AddressRepository;
import com.eduardo.newcadastro.domain.service.AddressRegisterService;

@RestController
@RequestMapping("/adresses")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressRegisterService addressRegisterService;
	
	@Autowired
	private AddressModelAssembler addressModelAssembler;
	
	@Autowired
	private AddressModelDisassembler addressModelDisassembler;
	
	@GetMapping
	public List<AddressModel> list() {	
		return addressModelAssembler.toCollectionModel(addressRepository.findAll());
	}
	
	@GetMapping("/{addressId}")
	public AddressModel find(@PathVariable Long addressId) {
		return addressModelAssembler.toModel(addressRegisterService.findOrFail(addressId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AddressModel save(@RequestBody @Valid AddressInputModel addressInputModel) {
		
		Address address = addressModelDisassembler.toDomainObject(addressInputModel);
		
		return addressModelAssembler.toModel(addressRegisterService.save(address));
	}
	
	@PutMapping("/{addressId}")
	public AddressModel atualizar(@PathVariable Long addressId, @RequestBody @Valid AddressInputModel addressInputModel) {
		
		Address nowPerson = addressRegisterService.findOrFail(addressId);
		addressModelDisassembler.copyToDomainObject(addressInputModel, nowPerson);
		
		return addressModelAssembler.toModel(addressRegisterService.save(nowPerson));
	}
	
}
