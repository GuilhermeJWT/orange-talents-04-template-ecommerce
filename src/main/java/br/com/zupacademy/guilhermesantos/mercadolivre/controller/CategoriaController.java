package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelCategoriaDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCategoria;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/salvar")
	@Transactional
	public ResponseEntity<ModelCategoriaDTO> salvaCategoria(@RequestBody @Valid ModelCategoriaDTO modelCategoriaDTO){
		
		ModelCategoria modelCategoria = modelCategoriaDTO.converte(manager);
		manager.persist(modelCategoria);
		
		return new ResponseEntity<ModelCategoriaDTO>(HttpStatus.OK);
		
	}
	
}
