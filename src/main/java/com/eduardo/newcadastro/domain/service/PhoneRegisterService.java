package com.eduardo.newcadastro.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eduardo.newcadastro.domain.exception.InvalidPhoneTypeException;
import com.eduardo.newcadastro.domain.exception.PhoneNotFoundException;
import com.eduardo.newcadastro.domain.model.Person;
import com.eduardo.newcadastro.domain.model.Phone;
import com.eduardo.newcadastro.domain.repository.PhoneRepository;

@Service
public class PhoneRegisterService {

	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private PersonRegisterService personRegisterService;
	
	public Phone findOrFail(Long phoneId) {
		return phoneRepository.findById(phoneId)
				.orElseThrow(()-> new PhoneNotFoundException(phoneId));
	}
	
	@Transactional
	public Phone save(Phone phone) {
		
		if(phone.getPhoneType() == "CELULAR" || phone.getPhoneType() == "RESIDENCIAL" || phone.getPhoneType() == "COMERCIAL") {
			throw new InvalidPhoneTypeException();
		}
		
		Person person = personRegisterService.findOrFail(phone.getPerson().getId());
		
		phone.setPerson(person);
		phone = phoneRepository.save(phone);
		
		phoneRepository.flush();
		
		
			
		return phone;
	}
	
	@Transactional
	public void delete(Long phoneId) {
		try {
			phoneRepository.deleteById(phoneId);
			phoneRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PhoneNotFoundException(phoneId);
			
		}
	}
	
}
