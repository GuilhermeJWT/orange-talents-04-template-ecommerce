package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.NotEmpty;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelPerguntaProduto;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;

public class ModelPerguntaProdutoDTO {
	
	@NotEmpty(message = "O Título deve ser Informado!")
	private String titulo;
	
	public ModelPerguntaProduto converte(ModelUsuario usuario, ModelProdutos produto) {
		return new ModelPerguntaProduto(titulo, produto, usuario);
	}
	
	public ModelPerguntaProdutoDTO(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
