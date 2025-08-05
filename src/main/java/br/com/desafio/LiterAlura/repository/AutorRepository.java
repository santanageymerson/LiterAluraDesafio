package br.com.desafio.LiterAlura.repository;

import br.com.desafio.LiterAlura.model.Autor;
import br.com.desafio.LiterAlura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    @Query("select l from Autor a join a.livros l")
    List<Livros> findByLivros();

    Optional<Autor> findByName(String name);

    @Query("select a from Autor a where a.anoNascimento <= :ano ")
    List<Autor> listarAutoresVivosEmUmDeterminadoAno(Integer ano);

    @Query("SELECT l FROM Autor a JOIN a.livros l WHERE l.idioma = :idioma")
    List<Livros> listarLivrosEmUmDeterminadoIdioma(String idioma);

    @Query("SELECT a FROM Autor a WHERE a.name = :autor")
    List<Autor> listarAutoresPorNome(String autor);
}
