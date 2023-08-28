package com.eduardo.newcadastro.domain.exception;

public class PersonAlreadyHaveAddressException extends BusinessException{

	public PersonAlreadyHaveAddressException(String message) {
		super(message);
	}
	
	public PersonAlreadyHaveAddressException() {
		this("Não é possível adicionar novo endereço, esta pessoa já possui um.");
	}

	private static final long serialVersionUID = 1L;

}
