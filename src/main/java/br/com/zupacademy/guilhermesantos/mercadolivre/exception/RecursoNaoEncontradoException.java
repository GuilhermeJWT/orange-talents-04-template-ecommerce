package br.com.zupacademy.guilhermesantos.mercadolivre.exception;

public class RecursoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	  public RecursoNaoEncontradoException() {
	        super("Recurso não Encontrado!");
	    }

}
