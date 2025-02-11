package br.com.zupacademy.guilhermesantos.mercadolivre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacademy.guilhermesantos.mercadolivre.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class EnableWebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private ImplementacaoToken implementacaoToken;
	
	@Autowired
	private UsuarioRepository usuarioReposotory;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers("/usuario/**").permitAll()
		.antMatchers("/produto/**").permitAll()
		.antMatchers("/pergunta/**").permitAll()
		.antMatchers("/upload/**").permitAll()
		.antMatchers("/opiniao/**").permitAll()
		.antMatchers("/detalhe/**").permitAll()
		.antMatchers("/categoria/**").permitAll()
		.antMatchers("/actuator/**").permitAll()
		.antMatchers("/fechamentocompra/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new GenerateAutenticateFilterToken(implementacaoToken, usuarioReposotory), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/***",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}
	
}
