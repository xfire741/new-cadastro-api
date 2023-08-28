package com.eduardo.newcadastro.domain.exception;

public class ChangePersonEmailOwnerException extends BusinessException{
	
	private static final long serialVersionUID = 1L;

	public ChangePersonEmailOwnerException(String message) {
		super(message);
	}
	
	public ChangePersonEmailOwnerException() {
		this("Não é possível mudar o dono do Email.");
	}

	

}
