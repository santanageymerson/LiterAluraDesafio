package br.com.desafio.LiterAlura;


import br.com.desafio.LiterAlura.principal.Principal;

import br.com.desafio.LiterAlura.repository.AutorRepository;
import br.com.desafio.LiterAlura.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	private LivrosRepository repository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,autorRepository);
		principal.exibirMenu();

	}
}
