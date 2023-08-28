package com.eduardo.newcadastro.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInputModel {

	@NotBlank
	@Size(min = 3, max = 100)
	private String name;
	
	@NotBlank
	@CPF
	private String cpf;
	
}
