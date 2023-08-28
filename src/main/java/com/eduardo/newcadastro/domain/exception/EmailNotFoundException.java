package com.eduardo.newcadastro.domain.exception;

public class EmailNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String message) {
		super(message);
	}
	
	public EmailNotFoundException(Long enderecoId) {
		this(String.format("email com o código %d não encontrado", enderecoId));
	}

}
