package com.bibiloteca.enums;

public enum StatusProjeto {

	/*BaixoRisco("Baixo risco"),
	MedioRisco("Médio Risco"),
	AltoRisco("Alto risco");*/
	
	Analise("Em analise"),
	Realizado("Análise realizada"),
	Aprovado("Análise aprovada"),
	Iniciado("Iniciado"),
	Planejado("Planejado"),
	Andamento("Em andamento"),
	Encerrado("Encerrado"),
	Cancelado("Cancelado");
	
	private final String descricao;
	
	StatusProjeto(String status){
		this.descricao = status;
	}

	public String getDescricao() {
		return descricao;
	}
}
