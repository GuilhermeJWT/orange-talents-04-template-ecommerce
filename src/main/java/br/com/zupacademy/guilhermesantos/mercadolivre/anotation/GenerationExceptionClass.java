package br.com.zupacademy.guilhermesantos.mercadolivre.anotation;

public class GenerationExceptionClass extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GenerationExceptionClass(String mensagem) {
		super(mensagem);
	}

}
