package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "caracteristica")
public class ModelCaracteristica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "O Nome da Caracteristica deve ser Informado!")
	private String nome;
	
	@NotEmpty(message = "A Descrição")
	private String descricao;
	
	@ManyToOne
	@NotNull(message = "O Produto deve ser Informado!")
	private ModelProdutos produto;
	
	public ModelCaracteristica(String nome, String descricao, ModelProdutos produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	@Deprecated
	public ModelCaracteristica() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public ModelProdutos getProduto() {
		return produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelCaracteristica other = (ModelCaracteristica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
