package com.eduardo.newcadastro.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.newcadastro.api.model.AddressModel;
import com.eduardo.newcadastro.domain.model.Address;

@Component
public class AddressModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public AddressModel toModel(Address address) {
		
		AddressModel addressModel = modelMapper.map(address, AddressModel.class);
		
		addressModel.getPerson().setCpf(PersonModelAssembler.cpfMasked(address.getPerson().getCpf()));
		
		addressModel.setCep(formatCEP(address.getCep()));
		
		return addressModel;
	}
	
	public List<AddressModel> toCollectionModel(List<Address> adresses) {
		return adresses.stream()
				.map(address -> toModel(address))
				.collect(Collectors.toList());
	}
	
	
    public static String formatCEP(String cep) {
        if (cep.length() != 8) {
            throw new IllegalArgumentException("O CEP deve conter 8 dígitos.");
        }
        return cep.substring(0, 5) + "-" + cep.substring(5);
    }
	
}
