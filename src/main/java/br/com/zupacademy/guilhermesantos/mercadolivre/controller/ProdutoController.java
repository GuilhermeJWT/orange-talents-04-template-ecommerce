 package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelProdutosDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping(value = "/salvar")
    public ResponseEntity<ModelProdutosDTO> salvaProduto(@RequestBody @Valid ModelProdutosDTO modelProdutosDTO){
    	
    	ModelProdutos modelProdutos = modelProdutosDTO.converte(manager);
        produtoRepository.save(modelProdutos);

        return new ResponseEntity<ModelProdutosDTO>(HttpStatus.OK);

    }
}
