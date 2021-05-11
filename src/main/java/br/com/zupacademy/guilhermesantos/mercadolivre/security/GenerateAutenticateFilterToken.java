package br.com.zupacademy.guilhermesantos.mercadolivre.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;
import br.com.zupacademy.guilhermesantos.mercadolivre.repository.UsuarioRepository;

public class GenerateAutenticateFilterToken extends OncePerRequestFilter{
	
	private ImplementacaoToken implementacaoToken;
	
	private UsuarioRepository usuarioRepository;
	
	public GenerateAutenticateFilterToken(ImplementacaoToken implementacaoToken, UsuarioRepository usuarioRepository) {
		this.implementacaoToken = implementacaoToken;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		
		String token = recuperaToken(request);
		boolean tokenValido = implementacaoToken.validaToken(token);
		
		if(tokenValido) {
			autenticaUsuario(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticaUsuario(String token) {
		
		Long idUsuario = implementacaoToken.getIdUsuario(token);
		ModelUsuario modelUsuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken autenticado = new UsernamePasswordAuthenticationToken(modelUsuario, null, modelUsuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(autenticado);
	}

	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
