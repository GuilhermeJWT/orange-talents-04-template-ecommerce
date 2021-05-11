package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelLoginDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/auth")
public class AutenticacaoController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<?> autenticaUsuario(@RequestBody @Valid ModelLoginDTO modelLoginDTO){
		
		return ResponseEntity.ok().build();
		
	}

}
