package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelImagensDTO;

@RestController
@RequestMapping(value = "/upload")
public class ImagemController {
	
	@PostMapping(value = "/produto/{id}")
	public ResponseEntity<ModelImagensDTO> salvaImagem(@PathVariable("id") @Valid ModelImagensDTO modelImagensDTO){
		return null;
		
	}

}
