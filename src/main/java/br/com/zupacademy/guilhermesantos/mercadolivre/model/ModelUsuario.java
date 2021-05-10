package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.SenhaCriptografadaDTO;

@Entity
@Table(name = "usuario")
public class ModelUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O Login deve ser Informado!")
	@Email(message = "Formato de E-mail Inválido!")
	private String login;

	@Size(min = 6, message = "A Senha precisa ter no minimo 6 Caracteres!")
	@NotBlank(message = "A Senha deve ser Informada!")
	private String senha;

	private LocalDateTime dataRegistro = LocalDateTime.now();
	
	public ModelUsuario(String login, SenhaCriptografadaDTO senha) {
		this.login = login;
		this.senha = senha.criptografaSenha();
	}
	
	@Deprecated
	public ModelUsuario() {
		
	}

}
