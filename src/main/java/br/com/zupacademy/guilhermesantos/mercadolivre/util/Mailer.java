package br.com.zupacademy.guilhermesantos.mercadolivre.util;

public interface Mailer {
	
	 void send(String body,
			   String subject,
			   String nameFrom,
			   String from,
			   String to);

}
