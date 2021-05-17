package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelOpiniaoProduto;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;

public class ModelOpiniaoProdutoDTO {
	
	@NotEmpty(message = "O Título deve ser Informado!")
	private String titulo;

	@NotEmpty(message = "A Descrição deve ser Informada!")
	@Size(max = 500, message = "A Descrição deve ter no Máximo 500 Caracteres!")
	private String descricao;

	@Min(1)
	@Max(5)
	private int nota;
	
	public ModelOpiniaoProduto converte(ModelProdutos produtos, ModelUsuario usuarioComprador) {
		return new ModelOpiniaoProduto(titulo, descricao, nota, produtos, usuarioComprador);
	}
	
	public ModelOpiniaoProdutoDTO(String titulo, String descricao, int nota) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getNota() {
		return nota;
	}
	
}
