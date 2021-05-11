package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "categoria")
public class ModelCategoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O Nome deve ser Informado!")
	private String nome;
	
	@ManyToOne
	private ModelCategoria maeCategoria;
	
	public ModelCategoria(String nome) {
		this.nome = nome;
	}
	
	public void setMae(ModelCategoria maeCategoria) {
		this.maeCategoria = maeCategoria;
	}
	
	@Deprecated
	public ModelCategoria() {
		
	}

	

}
