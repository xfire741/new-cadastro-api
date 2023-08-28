package com.eduardo.newcadastro.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

	private Long id;
	
	private String cep;

	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;
	
	private String complemento;

	private PersonResumModel person;
	
}
