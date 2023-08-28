package com.eduardo.newcadastro.domain.exception;

public class PersonInUseException extends EntityInUseException{

	public PersonInUseException(String mensagem) {
		super(mensagem);
	}
	
	public PersonInUseException(Long personId) {
		this(String.format("Pessoa com id %d não pode ser excluída por estar em uso", personId));
	}

	private static final long serialVersionUID = 1L;

}
