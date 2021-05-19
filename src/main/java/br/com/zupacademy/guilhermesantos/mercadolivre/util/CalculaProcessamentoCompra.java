package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCompra;

@Service
public class CalculaProcessamentoCompra {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private SimulaNotaFiscalRankingEmail simulaMetodosGeneric;

	public String processaPagamentos(Long idCompra, ModelRetornoGatewayPagamento modelRetornoGatewayPagamento) {
		ModelCompra compraFeita = manager.find(ModelCompra.class, idCompra);
		compraFeita.processaPagamentoCompra(modelRetornoGatewayPagamento);

		manager.merge(compraFeita);
		
		if(compraFeita.processadaComSucesso()) {
			simulaMetodosGeneric.processaEnviaNotaFiscal(compraFeita);
			simulaMetodosGeneric.processaRanking(compraFeita);
		}

		return "Deu tudo Certo";
	}

}
