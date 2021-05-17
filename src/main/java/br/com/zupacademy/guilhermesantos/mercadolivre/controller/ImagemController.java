package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelImagensDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.util.UploadFotoProduto;

@RestController
@RequestMapping(value = "/upload")
public class ImagemController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired	
	private UploadFotoProduto uploadFotoProduto;
	
	@PostMapping(value = "/produto/{id}")
	public void salvaImagem(@PathVariable("id")Long id, @Valid ModelImagensDTO modelImagensDTO){
		
		Set<String> links = (Set<String>) uploadFotoProduto.salvaFotos(modelImagensDTO.getFotosProdutos());
		ModelProdutos modelProdutos = manager.find(ModelProdutos.class, id);
		
		modelProdutos.salvaImagensProdutos(links);
		
	}

}
