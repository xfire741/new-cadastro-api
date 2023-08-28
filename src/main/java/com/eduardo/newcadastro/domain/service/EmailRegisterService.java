package com.eduardo.newcadastro.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.newcadastro.domain.exception.EmailNotFoundException;
import com.eduardo.newcadastro.domain.model.Email;
import com.eduardo.newcadastro.domain.model.Person;
import com.eduardo.newcadastro.domain.repository.EmailRepository;

@Service
public class EmailRegisterService {

	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private PersonRegisterService personRegisterService;

	public Email findOrFail(Long emailId) {
		return emailRepository.findById(emailId)
				.orElseThrow(()-> new EmailNotFoundException(emailId));
	}
	
	@Transactional
	public Email save(Email email) {
			Person person = personRegisterService.findOrFail(email.getPerson().getId());
			email.setPerson(person);

			emailRepository.flush();
			
		return emailRepository.save(email);

	}
	
	@Transactional
	public void delete(Long emailId) {
		findOrFail(emailId);
		
		emailRepository.deleteById(emailId);
	}
	
}
