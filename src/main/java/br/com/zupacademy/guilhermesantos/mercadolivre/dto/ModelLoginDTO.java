package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class ModelLoginDTO {

	@NotEmpty(message = "O Login deve ser Informado!")
	@Email(message = "O E-mail deve ser Válido!")
	private String login;
	
	@NotEmpty(message = "A Senha deve ser Informada!")
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converte() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}

}
