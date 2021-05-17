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

}
