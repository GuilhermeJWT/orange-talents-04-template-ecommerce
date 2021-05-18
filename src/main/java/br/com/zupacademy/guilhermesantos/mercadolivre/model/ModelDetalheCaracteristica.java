package br.com.zupacademy.guilhermesantos.mercadolivre.model;

public class ModelDetalheCaracteristica {
	
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
