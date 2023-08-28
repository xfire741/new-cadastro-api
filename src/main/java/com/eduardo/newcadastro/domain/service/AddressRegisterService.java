package com.eduardo.newcadastro.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.newcadastro.domain.exception.AddressNotFoundException;
import com.eduardo.newcadastro.domain.exception.InvalidCepException;
import com.eduardo.newcadastro.domain.exception.PersonAlreadyHaveAddressException;
import com.eduardo.newcadastro.domain.model.Address;
import com.eduardo.newcadastro.domain.model.Person;
import com.eduardo.newcadastro.domain.repository.AddressRepository;

@Service
public class AddressRegisterService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRegisterService personRegisterService;

	public Address findOrFail(Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(()-> new AddressNotFoundException(addressId));
	}
	
	@Transactional
	public Address save(Address address) {
		
		if(!validateCEP(address.getCep())) {
			throw new InvalidCepException();
		}
			
		Person person = personRegisterService.findOrFail(address.getPerson().getId());
		
		if(!person.alreadyHaveAddress() && address.getId() != person.getAddress().getId()) {
			throw new PersonAlreadyHaveAddressException();
		}
		
		address.setPerson(person);
		
		return addressRepository.save(address);
	}
	
	public boolean validateCEP(String cep) {
        String cleanedCEP = cep.replaceAll("[^0-9]", "");
        
        return cleanedCEP.length() == 8;
    }
	
}
