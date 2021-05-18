package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;

public class ModelDetalheCaracteristica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	
	public ModelDetalheCaracteristica(ModelCaracteristica modelCaracteristica) {
		this.nome = modelCaracteristica.getNome();
		this.descricao = modelCaracteristica.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
