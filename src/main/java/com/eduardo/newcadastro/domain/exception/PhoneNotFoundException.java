package com.eduardo.newcadastro.domain.exception;

public class PhoneNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PhoneNotFoundException(String message) {
		super(message);
	}
	
	public PhoneNotFoundException(Long phoneId) {
		this(String.format("Telefone com o código %d não encontrado", phoneId));
	}

}
