package com.eduardo.newcadastro.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailInputModel {

	@Email
	private String email;
	
	@NotNull
	private PersonInputId person;
	
}
