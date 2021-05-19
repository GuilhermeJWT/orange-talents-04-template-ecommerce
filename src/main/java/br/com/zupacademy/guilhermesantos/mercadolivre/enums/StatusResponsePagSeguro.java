package br.com.zupacademy.guilhermesantos.mercadolivre.enums;

public enum StatusResponsePagSeguro {

	SUCESSO, ERRO;

	public StatusTransacao normaliza() {

		if (this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}

		return StatusTransacao.ERRO;

	}

}
