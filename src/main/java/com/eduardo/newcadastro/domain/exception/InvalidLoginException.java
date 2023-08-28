package com.eduardo.newcadastro.domain.exception;

public class InvalidLoginException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public InvalidLoginException(String message) {
		super(message);
	}
	
	public InvalidLoginException() {
		this("Login inválido, por favor verifique as credenciais");
	}

}
