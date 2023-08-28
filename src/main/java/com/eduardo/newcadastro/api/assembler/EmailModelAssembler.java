package com.eduardo.newcadastro.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.EmailModel;
import com.eduardo.newcadastro.domain.model.Email;

@Component
public class EmailModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EmailModel toModel(Email email) {
		
		EmailModel emailModel = modelMapper.map(email, EmailModel.class);
		
		emailModel.getPerson().setCpf(PersonModelAssembler.cpfMasked((email.getPerson().getCpf())));
		
		return emailModel;
	}
	
	public List<EmailModel> toCollectionModel(List<Email> emails) {
		return emails.stream()
				.map(email -> toModel(email))
				.collect(Collectors.toList());
	}
	
}
