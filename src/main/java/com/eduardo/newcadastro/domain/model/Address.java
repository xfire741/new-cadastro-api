package com.eduardo.newcadastro.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Address {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String cep;

	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;
	
	private String complemento;

	@JoinColumn(name = "person_id")
	@OneToOne
	private Person person;
	
}
