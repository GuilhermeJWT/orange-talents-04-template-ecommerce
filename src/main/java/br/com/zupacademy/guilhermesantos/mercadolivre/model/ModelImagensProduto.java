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

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "imagens")
public class ModelImagensProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@NotNull(message = "O Produto deve ser Informado para essa as Imagens")
	private ModelProdutos modelProdutos;
	
	@URL
	@NotEmpty(message = "A URL não pode ser vazia")
	private String link;
	
	public ModelImagensProduto(ModelProdutos modelProdutos, String links) {
		
	}
	
	@Deprecated
	public ModelImagensProduto() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getLink() {
		return link;
	}
	
	public ModelProdutos getModelProdutos() {
		return modelProdutos;
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
		ModelImagensProduto other = (ModelImagensProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
