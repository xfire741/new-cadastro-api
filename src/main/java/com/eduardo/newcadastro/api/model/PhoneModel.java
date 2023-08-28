package com.eduardo.newcadastro.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneModel {

	private String number;
	private String phoneType;
	private PersonResumModel person;
	
	
}
