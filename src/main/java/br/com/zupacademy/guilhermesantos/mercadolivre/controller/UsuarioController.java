package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelUsuarioDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;
import br.com.zupacademy.guilhermesantos.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "/salvar")
	@Transactional
	public ResponseEntity<ModelUsuarioDTO> salvaUsuario(@RequestBody @Valid ModelUsuarioDTO modelUsuarioDTO){
		
		ModelUsuario modelUsuario = modelUsuarioDTO.converter();
		usuarioRepository.save(modelUsuario);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
