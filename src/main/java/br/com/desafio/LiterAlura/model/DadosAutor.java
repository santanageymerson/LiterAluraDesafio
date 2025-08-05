package br.com.desafio.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name") String name,
                         @JsonAlias("birth_year") String anoNascimento,
                         @JsonAlias("death_year") String anoMorte) {
}
