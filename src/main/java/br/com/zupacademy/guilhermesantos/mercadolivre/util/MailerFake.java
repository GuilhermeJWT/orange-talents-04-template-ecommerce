package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MailerFake implements Mailer {

  @Override
  public void send(String body, String subject, String nameFrom, String from, String to) {
    System.out.println("Carregando...");
    System.out.println("body " + body);
    System.out.println("subject " + subject);
    System.out.println("nameFrom " + nameFrom);
    System.out.println("from " + from);
    System.out.println("to " + to);
  }
}
