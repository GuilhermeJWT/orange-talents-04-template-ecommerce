package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelDetalheProdutoDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;

@RestController
@RequestMapping(value = "/detalhe")
public class DetalheProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping(value = "/produto/{id}")
	@Transactional
	public ModelDetalheProdutoDTO recuperaPaginaDetalhe(@PathVariable("id") Long id) {
		
		ModelProdutos modelProdutos = manager.find(ModelProdutos.class, id);
		
		return new ModelDetalheProdutoDTO(modelProdutos);
	}

}
