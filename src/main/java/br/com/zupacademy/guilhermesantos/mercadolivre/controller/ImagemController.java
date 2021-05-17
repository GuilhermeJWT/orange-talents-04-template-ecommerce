package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelImagensDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;
import br.com.zupacademy.guilhermesantos.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.guilhermesantos.mercadolivre.util.UploadFotoProduto;

@RestController
@RequestMapping(value = "/upload")
public class ImagemController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired	
	private UploadFotoProduto uploadFotoProduto;
	
	@PostMapping(value = "/produto/{id}")
	@Transactional
	public ResponseEntity<ModelImagensDTO> salvaImagem(@PathVariable("id")Long id, @Valid ModelImagensDTO modelImagensDTO){
		
		ModelUsuario fotosProdutoUsuario = usuarioRepository.findByLogin("guitestezup@gmail.com").get();
		ModelProdutos modelProdutos = manager.find(ModelProdutos.class, id);
		
		if(!modelProdutos.pertenceUsuario(fotosProdutoUsuario)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = (Set<String>) uploadFotoProduto.salvaFotos(modelImagensDTO.getFotosProdutos());
		
		modelProdutos.salvaImagensProdutos(links);
		manager.merge(modelProdutos);
		
		return new ResponseEntity<ModelImagensDTO>(HttpStatus.OK);
		
	}

}
