package com.eduardo.newcadastro.domain.exception;

public class UserWithSameLoginException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public UserWithSameLoginException(String message) {
		super(message);
	}
	
	public UserWithSameLoginException() {
		this("Não é possível cadastrar usuário com login já existente");
	}

}
