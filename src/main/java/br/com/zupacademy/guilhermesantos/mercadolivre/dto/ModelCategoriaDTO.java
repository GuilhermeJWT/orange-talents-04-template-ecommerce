package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.guilhermesantos.mercadolivre.anotation.GenericUniqueColumn;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCategoria;

public class ModelCategoriaDTO {
	
	@GenericUniqueColumn(domainClass = ModelCategoria.class, fieldName = "nome", message = "Categoria já Cadastrada! Informe Outra")
	@NotEmpty(message = "O Nome deve ser Informado!")
	private String nome;
	
	@NotNull(message = "O ID da Categoria deve ser Informado!")
	private Long idCategoriaMae;
	
	public ModelCategoria converte(EntityManager manager) {
		ModelCategoria modelCategoria = new ModelCategoria(nome);
		
		if(idCategoriaMae != null) {
			ModelCategoria maeCategoria = manager.find(ModelCategoria.class, idCategoriaMae);
			Assert.notNull(maeCategoria, "O Id da categoria Mãe é Inválido!");
			modelCategoria.setMae(maeCategoria);
		}
		
		return modelCategoria;
		
	}
	
	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}
	
	public String getNome() {
		return nome;
	}

}
