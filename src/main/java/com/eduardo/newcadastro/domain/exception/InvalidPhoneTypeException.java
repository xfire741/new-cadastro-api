package com.eduardo.newcadastro.domain.exception;

public class InvalidPhoneTypeException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	public InvalidPhoneTypeException(String message) {
		super(message);
	}
	
	public InvalidPhoneTypeException() {
		this("Os tipos de telefones válidos somente são: CELULAR, RESIDENCIAL ou COMERCIAL");
	}



}
