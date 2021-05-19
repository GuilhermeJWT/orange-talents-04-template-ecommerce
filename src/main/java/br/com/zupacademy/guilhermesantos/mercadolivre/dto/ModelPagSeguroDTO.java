package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.NotNull;

import br.com.zupacademy.guilhermesantos.mercadolivre.enums.StatusResponsePagSeguro;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCompra;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelTransacao;
import br.com.zupacademy.guilhermesantos.mercadolivre.util.ModelRetornoGatewayPagamento;

public class ModelPagSeguroDTO implements ModelRetornoGatewayPagamento{

	@NotNull(message = "O ID da Transação deve ser Informado!")
	private String idTransacao;

	@NotNull(message = "O Status deve ser Informado!")
	private StatusResponsePagSeguro statusPagSeguro;
	
	public ModelPagSeguroDTO(String idTransacao, StatusResponsePagSeguro statusPagSeguro) {
		this.idTransacao = idTransacao;
		this.statusPagSeguro = statusPagSeguro;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusResponsePagSeguro getStatusPagSeguro() {
		return statusPagSeguro;
	}

	public ModelTransacao toTransacao(ModelCompra modelCompra) {
		return new ModelTransacao(statusPagSeguro.normaliza(), idTransacao, modelCompra);
	}

}
