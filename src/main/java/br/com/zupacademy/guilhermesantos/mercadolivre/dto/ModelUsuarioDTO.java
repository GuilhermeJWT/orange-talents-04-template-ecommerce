package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ModelUsuarioDTO {
	
	@NotEmpty(message = "O Login deve ser Informado!")
	@Email(message = "Formato de E-mail Inválido!")
	private String login;
	
	public String getLogin() {
		return login;
	}

}
