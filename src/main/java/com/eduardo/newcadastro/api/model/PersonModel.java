package com.eduardo.newcadastro.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonModel {

	private Long id;
	private String name;
	private String cpf;
	private List<EmailResumModel> emails = new ArrayList<>();
	private List<PhoneResumModel> phones = new ArrayList<>();
	private AddressResumModel address;
	
	
}
