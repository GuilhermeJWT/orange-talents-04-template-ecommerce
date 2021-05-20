package br.com.zupacademy.guilhermesantos.mercadolivre.swagger;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public Docket configuracao() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.zupacademy.guilhermesantos.mercadolivre.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(informacoesApi());
	}
	
	private ApiInfo informacoesApi() {
		return new ApiInfoBuilder()
				.title("Mercado Livre API")
				.description("Api das Funcionalidades Simples do Mercado Livre - OT4")
				.version("1.0")
				.contact(dadosMercadoLivreGuilherme())
				.build();
	}
	
	private Contact dadosMercadoLivreGuilherme() {
		return new Contact("Guilherme Santos",
				"https://github.com/GuilhermeJWT/orange-talents-04-template-ecommerce", 
				"Documentação Mercado Livre");
	}

}
