package com.eduardo.newcadastro.api.model.input;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInputModel {

	@NotBlank
	@Size(min = 8, max = 8)
	@Digits(fraction = 11, integer = 11)
	private String cep;

	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String uf;
	
	private String complemento;
	
	@NotNull
	private PersonInputId person;
	
}
