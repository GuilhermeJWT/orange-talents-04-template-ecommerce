package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.SenhaCriptografadaDTO;

@Entity
@Table(name = "usuario")
public class ModelUsuario implements UserDetails {

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

	@ManyToMany(fetch = FetchType.EAGER)
	private List<ModelPerfil> perfis = new ArrayList<>();
	
	private ModelUsuario userLogado;

	public ModelUsuario(String login, SenhaCriptografadaDTO senha) {
		this.login = login;
		this.senha = senha.criptografaSenha();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public List<ModelPerfil> getPerfis() {
		return perfis;
	}
	
	public ModelUsuario getUserLogado() {
		return userLogado;
	}
	
	public ModelUsuario get() {
		return userLogado;
	}
	
	@Deprecated
	public ModelUsuario() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelUsuario other = (ModelUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
