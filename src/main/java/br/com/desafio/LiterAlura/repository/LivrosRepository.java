package br.com.desafio.LiterAlura.repository;

import br.com.desafio.LiterAlura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livros,Long> {
}
