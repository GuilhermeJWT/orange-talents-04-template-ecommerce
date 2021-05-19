package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.NotNull;

public class ModelRankingDTO {
	
	@NotNull(message = "O ID da Compra deve ser Informado!")
	private Long idCompra;
	
	@NotNull(message = "O ID do Proprietário do Produto deve ser Informado! ")
	private Long idDonoProduto;
	
	public ModelRankingDTO(Long idCompra, Long idDonoProduto) {
		this.idCompra = idCompra;
		this.idDonoProduto = idDonoProduto;
	}
	
	public Long getIdCompra() {
		return idCompra;
	}
	
	public Long getIdDonoProduto() {
		return idDonoProduto;
	}

}
