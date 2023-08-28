package com.eduardo.newcadastro.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eduardo.newcadastro.domain.exception.PersonInUseException;
import com.eduardo.newcadastro.domain.exception.PersonNotFoundException;
import com.eduardo.newcadastro.domain.model.Person;
import com.eduardo.newcadastro.domain.repository.PersonRepository;

@Service
public class PersonRegisterService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person findOrFail(Long personId) {
		return personRepository.findById(personId)
				.orElseThrow(()-> new PersonNotFoundException(personId));
	}
	
	@Transactional
	public Person save(Person person) {
		return personRepository.save(person);
	}
	
	@Transactional
	public void delete(Long personId) {
		try {
			personRepository.deleteById(personId);
			personRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PersonNotFoundException(personId);
			
		} catch (DataIntegrityViolationException e) {
			throw new PersonInUseException(personId);

		}
	}
	
}
