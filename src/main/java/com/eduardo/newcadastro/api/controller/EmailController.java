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

import com.eduardo.newcadastro.api.assembler.EmailModelAssembler;
import com.eduardo.newcadastro.api.assembler.EmailModelDisassembler;
import com.eduardo.newcadastro.api.model.EmailModel;
import com.eduardo.newcadastro.api.model.input.EmailInputModel;
import com.eduardo.newcadastro.domain.exception.ChangePersonEmailOwnerException;
import com.eduardo.newcadastro.domain.model.Email;
import com.eduardo.newcadastro.domain.repository.EmailRepository;
import com.eduardo.newcadastro.domain.service.EmailRegisterService;

@RestController
@RequestMapping("/emails")
public class EmailController {

	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private EmailRegisterService emailRegisterService;
	
	@Autowired
	private EmailModelAssembler emailModelAssembler;
	
	@Autowired
	private EmailModelDisassembler emailModelDisassembler;
	
	@GetMapping
	public List<EmailModel> list() {
		return emailModelAssembler.toCollectionModel(emailRepository.findAll());
	}
	
	@GetMapping("/{emailId}")
	public EmailModel find(@PathVariable Long emailId) {
		return emailModelAssembler.toModel(emailRegisterService.findOrFail(emailId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmailModel save(@RequestBody @Valid EmailInputModel emailInputModel) {
		Email email = emailModelDisassembler.toDomainObject(emailInputModel);
		
		return emailModelAssembler.toModel(emailRegisterService.save(email));
	}
	
	@DeleteMapping("/{emailId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long emailId) {
		emailRegisterService.delete(emailId);
	}
	
	@PutMapping("/{emailId}")
	public EmailModel atualizar(@PathVariable Long emailId, @RequestBody @Valid EmailInputModel emailInputModel) {
		
		
		
		Email nowEmail = emailRegisterService.findOrFail(emailId);
		if(!emailInputModel.getPerson().getId().equals(nowEmail.getPerson().getId())) {
			throw new ChangePersonEmailOwnerException();
		}
		
		emailModelDisassembler.copyToDomainObject(emailInputModel, nowEmail);
		
		return emailModelAssembler.toModel(emailRegisterService.save(nowEmail));
	}
	
}
