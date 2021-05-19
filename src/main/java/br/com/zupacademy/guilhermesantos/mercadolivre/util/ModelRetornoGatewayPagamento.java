package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCompra;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelTransacao;

public interface ModelRetornoGatewayPagamento {
	
	ModelTransacao toTransacao(ModelCompra modelCompra);

}
