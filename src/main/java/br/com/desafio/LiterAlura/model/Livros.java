package br.com.desafio.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo ;
    //@Transient
    @Transient
    private List<Autor> autors = new ArrayList<>();
    @Transient
    //@ElementCollection
    private List<String> idiomas = new ArrayList<>();
    private String idioma;
    private Long numeroDownloads;

    @ManyToOne
    private Autor autor;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Livros(){
    }
    public Livros(String titulo, List<DadosAutor> autor, List<String> idiomas, Long numerosDownloads) {
        this.titulo = titulo;
        this.autors = autor.stream().map(d->new Autor(d.name(), d.anoNascimento(), d.anoMorte()))
                .toList();
        this.idiomas = idiomas;
        this.numeroDownloads = numerosDownloads;
    }

    public Livros(Livros livros, String idioma) {
        this.titulo = livros.getTitulo();
        this.idioma = idioma;
        this.numeroDownloads = livros.getNumeroDownloads();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void setAutors(List<Autor> autors) {
        this.autors = autors;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Long getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Long numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "******Livros******\n" +
                "  titulo='" + titulo + '\n' +
                "  idioma='" + idioma + '\n' +
                "  numeroDownloads=" + numeroDownloads +"\n"+
                "  autor=" + autor.getName() ;
    }
}
