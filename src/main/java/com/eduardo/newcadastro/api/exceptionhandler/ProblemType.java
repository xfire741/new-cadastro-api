package com.eduardo.newcadastro.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	MENSAGEM_INCOMPREENSIVEL("/mesagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido"),
	ERRO_SISTEMA("/erro-sistema", "Erro no sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	USUARIO_COM_ENDERECO_EXISTENTE("/dados-invalidos", "Dados inválidos"),
	TIPO_DE_TELEFONE_INVALIDO("/tipo-telefone-invalido", "Tipo de telefone inválido"),
	MUDAR_DONO_DE_EMAIL("/mudar-dono-email", "Não é possível mudar o dono do email.");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "http://newcadastro.com.br" + path;
		this.title = title;
	}

}
