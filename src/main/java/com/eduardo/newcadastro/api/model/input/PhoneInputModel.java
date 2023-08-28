package com.eduardo.newcadastro.api.model.input;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneInputModel {

	@NotBlank
	@Size(min = 8, max = 11)
	@Digits(fraction = 8, integer = 11)
	private String number;
	
	@NotBlank
	private String phoneType;
	
	@NotNull
	private PersonInputId person;
	
}
