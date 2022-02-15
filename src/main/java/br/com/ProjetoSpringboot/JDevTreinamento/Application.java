package br.com.ProjetoSpringboot.JDevTreinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe de inicialização do SpringBoot
 * 
 * @author bruno
 */
@SpringBootApplication /* Inicia a aplicação web rodando springboot */
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args); /* É a linha principal, que roda o projeto Java SpringBoot */
	}
}
