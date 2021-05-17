package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
@Table(name = "pergunta")
public class ModelPerguntaProduto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O Título deve ser Informado!")
	private String titulo;
	
	private LocalDateTime dataRegistro = LocalDateTime.now();
	
	@ManyToOne(optional = false)
	@NotNull(message = "O Produto deve ser Informado!")
	private ModelProdutos produtos;
	
	@ManyToOne(optional = false)
	@NotNull(message = "O Usuário deve ser Informado!")
	private ModelUsuario usuario;
	
	public ModelPerguntaProduto(String titulo, ModelProdutos produtos, ModelUsuario usuario) {
		Assert.notNull(produtos, "O Produto deve ser Informado!");
		Assert.notNull(usuario, "O Usuário deve ser Informado!");
		this.titulo = titulo;
		this.produtos = produtos;
		this.usuario = usuario;
		
	}
	
	@Deprecated
	public ModelPerguntaProduto() {
		
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public ModelProdutos getProdutos() {
		return produtos;
	}

	public ModelUsuario getUsuario() {
		return usuario;
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
		ModelPerguntaProduto other = (ModelPerguntaProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
