package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelPerguntaProdutoDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelPerguntaProduto;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;
import br.com.zupacademy.guilhermesantos.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.guilhermesantos.mercadolivre.util.EnviaEmail;

@RestController
@RequestMapping(value = "/pergunta")
public class PerguntaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnviaEmail email;
	
	@PostMapping(value = "/salvar/{id}")
	@Transactional
	public ResponseEntity<ModelPerguntaProdutoDTO> salvaPergunta(@RequestBody @Valid ModelPerguntaProdutoDTO modelPerguntaProdutoDTO, @PathVariable("id") Long id) {
		
		ModelProdutos produto = manager.find(ModelProdutos.class, id);
		ModelUsuario usuario = usuarioRepository.findByLogin("guitestezup@gmail.com").get();
		
		ModelPerguntaProduto modelPergunta = modelPerguntaProdutoDTO.converte(usuario, produto);
		manager.persist(modelPergunta);
		
		email.novaPergunta(modelPergunta);
		
		return new ResponseEntity<ModelPerguntaProdutoDTO>(HttpStatus.OK);
		
	}

}
