package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.guilhermesantos.mercadolivre.enums.StatusTransacao;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCompra;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelTransacao;
import br.com.zupacademy.guilhermesantos.mercadolivre.util.ModelRetornoGatewayPagamento;

public class ModelPayPalDTO implements ModelRetornoGatewayPagamento{
	
	@NotBlank(message = "O ID da Transação deve ser Informado!")
	private String idTransacao;
	
	@Min(value = 0, message = "Número de Status Incorreto!")
	@Max(value = 1, message = "Número de Status Incorreto!")
	private int statusPayPal;
	
	public ModelPayPalDTO(String idTransacao, int statusPayPal) {
		this.idTransacao = idTransacao;
		this.statusPayPal = statusPayPal;
	}
	
	public ModelTransacao toTransacao(ModelCompra modelCompra) {
		
		StatusTransacao statusFinalPayPal = this.statusPayPal == 0 ? StatusTransacao.ERRO : StatusTransacao.SUCESSO;
		
		return new ModelTransacao(statusFinalPayPal, idTransacao, modelCompra);
	}
	
	public String getIdTransacao() {
		return idTransacao;
	}
	
	public int getStatusPayPal() {
		return statusPayPal;
	}

}
