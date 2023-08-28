package com.eduardo.newcadastro.domain.exception;

public class InvalidCepException  extends BusinessException {

	private static final long serialVersionUID = 1L;

	public InvalidCepException(String message) {
		super(message);
	}
	
	public InvalidCepException() {
		this("Cep inválido");
	}

}
