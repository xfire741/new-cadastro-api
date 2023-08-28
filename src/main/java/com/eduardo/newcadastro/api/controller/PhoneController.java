package com.eduardo.newcadastro.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.newcadastro.api.assembler.PhoneModelAssembler;
import com.eduardo.newcadastro.api.assembler.PhoneModelDisassembler;
import com.eduardo.newcadastro.api.model.PhoneModel;
import com.eduardo.newcadastro.api.model.input.PhoneInputModel;
import com.eduardo.newcadastro.domain.model.Phone;
import com.eduardo.newcadastro.domain.repository.PhoneRepository;
import com.eduardo.newcadastro.domain.service.PhoneRegisterService;

@RestController
@RequestMapping("/phones")
public class PhoneController {

	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private PhoneModelAssembler phoneModelAssembler;
	
	@Autowired
	private PhoneRegisterService phoneRegisterService;
	
	@Autowired
	private PhoneModelDisassembler phoneModelDisassembler;
	
	@GetMapping
	public List<PhoneModel> list() {
		return phoneModelAssembler.toCollectionModel(phoneRepository.findAll());
	}
	
	@GetMapping("/{phoneId}")
	public PhoneModel find(@PathVariable Long phoneId) {
		return phoneModelAssembler.toModel(phoneRegisterService.findOrFail(phoneId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PhoneModel save(@RequestBody @Valid PhoneInputModel phoneInputModel) {
		Phone phone = phoneModelDisassembler.toDomainObject(phoneInputModel);
		
		return phoneModelAssembler.toModel(phoneRegisterService.save(phone));	
	}
	
	@DeleteMapping("/{phoneId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long phoneId) {
		phoneRegisterService.delete(phoneId);
	}
	
}
