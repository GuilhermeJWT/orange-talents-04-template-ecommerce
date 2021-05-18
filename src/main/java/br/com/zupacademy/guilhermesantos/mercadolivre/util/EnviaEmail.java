package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelPerguntaProduto;

@Service
public class EnviaEmail {
	
	@Autowired
	private Mailer mailer;
	
	  @Autowired
	  public EnviaEmail(Mailer mailer) {
	    this.mailer = mailer;
	  }

	  public void novaPergunta(ModelPerguntaProduto pergunta) {
	    mailer.send(
	        String.format("<html>%s</html>", pergunta.getTitulo()),
	        "Nova pergunta",
	        "Mercado Livre",
	        "guitestezup@mercadolivre.com.br",
	        pergunta.getProprietario().getLogin());
	  }

}
