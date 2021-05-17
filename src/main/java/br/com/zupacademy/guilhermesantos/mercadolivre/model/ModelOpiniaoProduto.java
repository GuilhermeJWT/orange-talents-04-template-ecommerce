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
import javax.validation.constraints.Size;

@Entity
@Table(name = "opiniao")
public class ModelOpiniaoProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O Título deve ser Informado!")
	private String titulo;

	@NotEmpty(message = "A Descrição deve ser Informada!")
	@Size(max = 500, message = "A Descrição deve ter no Máximo 500 Caracteres!")
	private String descricao;

	@Size(min = 1, max = 5, message = "A Nota deve ser Entre 1 - 5!")
	private int nota;

	@ManyToOne(optional = false)
	@NotNull(message = "O Produto deve ser Informado!")
	private ModelProdutos produtos;

	@ManyToOne(optional = false)
	@NotNull(message = "O Usuário deve ser Informado!")
	private ModelUsuario usuarioComprador;

	public ModelOpiniaoProduto(String titulo, String descricao, int nota, ModelProdutos produtos,ModelUsuario usuarioComprador) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
		this.produtos = produtos;
		this.usuarioComprador = usuarioComprador;
	}

	@Deprecated
	public ModelOpiniaoProduto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public ModelProdutos getProduto() {
		return produtos;
	}

	public void setProduto(ModelProdutos produto) {
		this.produtos = produto;
	}

	public ModelUsuario getUsuarioComprador() {
		return usuarioComprador;
	}

	public void setUsuarioComprador(ModelUsuario usuarioComprador) {
		this.usuarioComprador = usuarioComprador;
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
		ModelOpiniaoProduto other = (ModelOpiniaoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
