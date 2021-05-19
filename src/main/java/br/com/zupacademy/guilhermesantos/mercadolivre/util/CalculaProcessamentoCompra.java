package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCompra;

public class CalculaProcessamentoCompra {
	
	@PersistenceContext
	private EntityManager manager;

	public String processaPagamentos(Long idCompra, ModelRetornoGatewayPagamento modelRetornoGatewayPagamento) {
		ModelCompra compraFeita = manager.find(ModelCompra.class, idCompra);
		compraFeita.processaPagamentoCompra(modelRetornoGatewayPagamento);

		manager.merge(compraFeita);

		return "Deu tudo Certo";
	}

}
