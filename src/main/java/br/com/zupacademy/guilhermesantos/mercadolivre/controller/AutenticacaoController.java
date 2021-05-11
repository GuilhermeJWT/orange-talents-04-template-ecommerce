package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelLoginDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.dto.TokenDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.security.ImplementacaoToken;

@RestController
@RequestMapping(value = "/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private ImplementacaoToken implementacaoToken;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticaUsuario(@RequestBody @Valid ModelLoginDTO modelLoginDTO) {

		UsernamePasswordAuthenticationToken dados = modelLoginDTO.converte();

		try {
			Authentication autenticacao = manager.authenticate(dados);
			
			String token = implementacaoToken.geraToken(autenticacao);
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException exception) {
			return ResponseEntity.badRequest().build();
		}
	}

}
