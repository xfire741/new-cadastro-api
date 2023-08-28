package com.eduardo.newcadastro.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.PhoneModel;
import com.eduardo.newcadastro.api.model.PhoneResumModel;
import com.eduardo.newcadastro.domain.exception.InvalidPhoneTypeException;
import com.eduardo.newcadastro.domain.model.Phone;

@Component
public class PhoneModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PhoneModel toModel(Phone phone) {
		
		PhoneModel phoneModel = modelMapper.map(phone, PhoneModel.class);
		
		phoneModel.setNumber(formatPhoneNumber(phone));
		
		phoneModel.getPerson().setCpf(PersonModelAssembler.cpfMasked(phone.getPerson().getCpf()));
		
		return phoneModel;
	}
	
public PhoneResumModel toResumModel(Phone phone) {
		
		PhoneResumModel phoneModel = modelMapper.map(phone, PhoneResumModel.class);
		
		phoneModel.setNumber(formatPhoneNumber(phone));
		
		return phoneModel;
	}
	
	public List<PhoneModel> toCollectionModel(List<Phone> phones) {
		return phones.stream()
				.map(phone -> toModel(phone))
				.collect(Collectors.toList());
	}
	
	public List<PhoneResumModel> toCollectionResumModel(List<Phone> phones) {
		return phones.stream()
				.map(phone -> toResumModel(phone))
				.collect(Collectors.toList());
	}
	
	public String formatPhoneNumber(Phone phone) {
        StringBuilder formattedNumber = new StringBuilder();
        
        if (phone.getPhoneType().equals("CELULAR")) {
            formattedNumber.append("(")
                           .append(phone.getNumber().substring(0, 2))
                           .append(") ")
                           .append(phone.getNumber().substring(2, 3))
                           .append(phone.getNumber().substring(3, 7))
                           .append("-")
                           .append(phone.getNumber().substring(7));
        } else if (phone.getPhoneType().equals("RESIDENCIAL")) {
            formattedNumber.append("(")
                           .append(phone.getNumber().substring(0, 2))
                           .append(") ")
                           .append(phone.getNumber().substring(2, 6))
                           .append("-")
                           .append(phone.getNumber().substring(6));
        } else if (phone.getPhoneType().equals("COMERCIAL")) {
            formattedNumber.append(phone.getNumber().substring(0, 4))
                           .append("-")
                           .append(phone.getNumber().substring(4));
        } else {
            throw new InvalidPhoneTypeException();
        }
        
        return formattedNumber.toString();
    }
	
}
