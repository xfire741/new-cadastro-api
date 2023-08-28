package com.eduardo.newcadastro.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Person {

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
 	private Long id;
 	
 	private String name;
 	
 	private String cpf;
 	
 	@JsonIgnore
 	@OneToOne(mappedBy = "person")
 	private Address address;
 	
 	@OneToMany(mappedBy = "person")
 	private List<Phone> phones = new ArrayList<>();

 	@OneToMany(mappedBy = "person")
 	private List<Email> emails = new ArrayList<>();
	
 	public boolean alreadyHaveAddress() {
 		return this.address == null;
 	}
	
}
