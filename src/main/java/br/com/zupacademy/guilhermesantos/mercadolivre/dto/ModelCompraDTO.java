package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.guilhermesantos.mercadolivre.enums.PagamentoGateway;

public class ModelCompraDTO {
	
	@NotNull(message = "O ID deve ser Informado para a Compra!")
	private Long id;
	
	@Positive(message = "A Quantidade deve ser maior que 1!")
	@NotNull(message = "A Quantidade deve ser Informada!")
	private int quantidade;
	
	@NotNull(message = "O Modo de Pagamento deve ser Informado!")
	private PagamentoGateway pagamento;
	
	public ModelCompraDTO(Long id, int quantidade, PagamentoGateway pagamento) {
		this.id = id;
		this.quantidade = quantidade;
		this.pagamento = pagamento;
	}
	
	public Long getId() {
		return id;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public PagamentoGateway getPagamento() {
		return pagamento;
	}

}
