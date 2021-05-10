package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.zupacademy.guilhermesantos.mercadolivre.anotation.GenericUniqueColumn;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;

public class ModelUsuarioDTO {
	
	@GenericUniqueColumn(domainClass = ModelUsuario.class, fieldName = "login", message = "E-mail já Cadastrado! Informe Outro")
	@NotEmpty(message = "O Login deve ser Informado!")
	@Email(message = "Formato de E-mail Inválido!")
	private String login;
	
	@Size(min = 6, message = "A Senha precisa ter no minimo 6 Caracteres!")
	@NotBlank(message = "A Senha deve ser Informada!")
	private String senha;
	
	public ModelUsuario converter() {
		return new ModelUsuario(this.login, new SenhaCriptografadaDTO(senha));
	}
	
	public ModelUsuarioDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
}
