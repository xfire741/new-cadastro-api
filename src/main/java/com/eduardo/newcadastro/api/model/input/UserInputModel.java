package com.eduardo.newcadastro.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputModel {

	@NotBlank
	private String login;
	
	@NotNull
	private String password;
	
	@NotBlank
	private String role;
	
}
