package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelOpiniaoProdutoDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelOpiniaoProduto;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;

@RestController
@RequestMapping(value = "/opiniao")
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/salvar/{id}")
	@Transactional
	public ResponseEntity<ModelOpiniaoProdutoDTO> salvaOpiniao(@RequestBody @Valid ModelOpiniaoProdutoDTO modelOpiniaoProdutoDTO,@PathVariable("id") Long id, @AuthenticationPrincipal ModelUsuario usuario){
		
		ModelProdutos modelProdutos = manager.find(ModelProdutos.class, id);
		ModelUsuario usuarioComprador = usuario.get();
		
		ModelOpiniaoProduto modelOpiniaoSalva = modelOpiniaoProdutoDTO.converte(modelProdutos, usuarioComprador);
		manager.persist(modelOpiniaoSalva);
		
		return new ResponseEntity<ModelOpiniaoProdutoDTO>(HttpStatus.OK);
		
	}
	
}
