package br.com.desafio.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String anoNascimento;
    private String anoMorte;
   // @Transient
    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livros> livros = new ArrayList<>();

    public Autor(){}

    public Autor(Autor autor, Livros livro) {

        this.name = autor.getName();
        this.anoNascimento = autor.getAnoNascimento();
        this.anoMorte = autor.getAnoMorte();
        List<Livros> livros = new ArrayList<>();
        livros.add(livro);
        setLivros(livros);



    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        livros.forEach(l->l.setAutor(this));
        this.livros = livros;
    }

    public Autor(String name, String anoNascimento, String anoMorte) {
        this.name = name;
        this.anoNascimento = anoNascimento;
        this.anoMorte = anoMorte;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(String anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id) && Objects.equals(name, autor.name) && Objects.equals(anoNascimento, autor.anoNascimento) && Objects.equals(anoMorte, autor.anoMorte) && Objects.equals(livros, autor.livros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, anoNascimento, anoMorte, livros);
    }

    @Override
    public String toString() {
        return  "  ******Autor******\n"+
                "  name= " + name  +"\n"+
                "  anoNascimento= " + anoNascimento +"\n"+
                "  anoMorte= " + anoMorte ;
    }

    public void adicionarLivro(Livros livro) {
        livro.setAutor(this); // garante que o livro conhece seu autor
        this.livros.add(livro); // adiciona o livro à lista do autor
    }
}
