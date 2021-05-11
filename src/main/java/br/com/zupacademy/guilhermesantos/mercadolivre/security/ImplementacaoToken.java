package br.com.zupacademy.guilhermesantos.mercadolivre.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ImplementacaoToken {

	@Value("${mercadolivre.jwt.expiration}")
	private String expiration;

	@Value("${mercadolivre.jwt.secret}")
	private String senhaSecreta;

	public String geraToken(Authentication autenticacao) {

		ModelUsuario modelUsuario = (ModelUsuario) autenticacao.getPrincipal();
		Date dataAtual = new Date();
		Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("Mercado Livre - Guilherme Santos").setSubject(modelUsuario.getId().toString())
				.setIssuedAt(dataAtual).setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, senhaSecreta)
				.compact();
	}

	public boolean validaToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.senhaSecreta).parseClaimsJws(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims corpo = Jwts.parser().setSigningKey(this.senhaSecreta).parseClaimsJws(token).getBody();
		return Long.parseLong(corpo.getSubject());
	}

}
