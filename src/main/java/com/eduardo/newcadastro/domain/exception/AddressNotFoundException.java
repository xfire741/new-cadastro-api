package com.eduardo.newcadastro.domain.exception;

public class AddressNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AddressNotFoundException(String message) {
		super(message);
	}
	
	public AddressNotFoundException(Long enderecoId) {
		this(String.format("endereço com o código %d não encontrado", enderecoId));
	}

}
