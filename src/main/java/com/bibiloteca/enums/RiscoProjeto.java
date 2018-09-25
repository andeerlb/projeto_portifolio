package com.bibiloteca.enums;

public enum RiscoProjeto {
	BaixoRisco("Baixo risco"),
	MedioRisco("Médio Risco"),
	AltoRisco("Alto risco");
	
	private final String descricao;
	
	RiscoProjeto(String status){
		this.descricao = status;
	}

	public String getDescricao() {
		return descricao;
	}
}
