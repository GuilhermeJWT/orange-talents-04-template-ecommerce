package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import java.math.BigDecimal;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;

public class ModelDetalheProdutoDTO {

	private String descricao;
	private String nome;
	private BigDecimal valor;

	public ModelDetalheProdutoDTO(ModelProdutos modelProdutos) {
		this.descricao = modelProdutos.getDescricao();
		this.nome = modelProdutos.getNome();
		this.valor = modelProdutos.getValor();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

}
