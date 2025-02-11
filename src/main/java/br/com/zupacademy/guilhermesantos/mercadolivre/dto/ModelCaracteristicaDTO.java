package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.NotEmpty;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCaracteristica;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;

public class ModelCaracteristicaDTO {
	
	@NotEmpty(message = "O Nome da Caracteristica deve ser Informado!")
	private String nome;
	
	@NotEmpty(message = "A Descrição da Caracteristica deve ser Informada!")
	private String descricao;
	
	public ModelCaracteristicaDTO(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ModelCaracteristica converte(ModelProdutos produto) {
		return new ModelCaracteristica(nome, descricao, produto);
	}

}
