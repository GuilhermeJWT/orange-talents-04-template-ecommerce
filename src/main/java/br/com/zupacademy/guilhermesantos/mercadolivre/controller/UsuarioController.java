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

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelUsuarioDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/salvar")
	@Transactional
	public ResponseEntity<ModelUsuarioDTO> salvaUsuario(@RequestBody @Valid ModelUsuarioDTO modelUsuarioDTO){
		
		ModelUsuario modelUsuario = modelUsuarioDTO.converter();
		
		manager.persist(modelUsuario);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
