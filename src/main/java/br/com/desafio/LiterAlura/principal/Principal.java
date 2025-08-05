package br.com.desafio.LiterAlura.principal;

import br.com.desafio.LiterAlura.model.*;
import br.com.desafio.LiterAlura.repository.AutorRepository;
import br.com.desafio.LiterAlura.repository.LivrosRepository;
import br.com.desafio.LiterAlura.service.ConsumoApi;
import br.com.desafio.LiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECOAPI = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private LivrosRepository repository;
    private AutorRepository autorRepository;

    public Principal(LivrosRepository repository,AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository=autorRepository;
    }


    public void exibirMenu() {

        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Busca de livro por título
                    2 - Listar Livros registrado
                    3 - Lista autores registrado
                    4 - Listar autores vivos em um determinado ano
                    5 - listar livro em um determinado idioma  
                    6 - Buscar autor por nome:                       
                    0 - Sair                                 
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivrosPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutorRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmUmDeterminadoAno();
                    break;
                case 5:
                    listarLivroEmUmDeterminadoIdioma();
                    break;
                case 6:
                    buscarAutorPorNome();
                    break;

            }
        }

    }

    private void buscarLivrosPorTitulo() {
        DadosResultado dadosResultado =getDadosLivro();
        List<String> lIdomas = dadosResultado.dados().stream().flatMap(d->d.idiomas().stream()).toList();
        List<Livros> livros = dadosResultado.dados().stream()
                .map(d->new Livros(d.titulo(),d.autor(),d.idiomas(),d.numeroDownloads()))
                .toList();
        List<Autor> autors = livros.stream()
                        .flatMap(l->l.getAutors().stream())
                                 .collect(Collectors.toList());
        String idioma = lIdomas.get(0);
        Autor autor = autors.get(0);
        Livros livro =new Livros(livros.get(0),idioma);

        Autor autor1 = new Autor(autor,livro);
        System.out.println(autor1);
        var autorCompare = autorRepository.findByName(autor.getName());
        if (autorCompare.isPresent()){
            autorCompare.get().adicionarLivro(livro);
            autorRepository.save(autorCompare.get());
        }else{
            //repository.save(livro);
            autorRepository.save(autor1);
        }

    }

    private DadosResultado getDadosLivro(){
        System.out.println("digite o nome do livro");
        String nomeLivro = leitura.nextLine();
        String url = ENDERECOAPI+nomeLivro.replace(" ","+").toLowerCase();

        String json = consumoApi.obterDados(url);

        DadosResultado dados = conversor.obterDados(json,DadosResultado.class);

        return dados;
    }

    private void listarLivrosRegistrados() {

        List<Livros> livros = autorRepository.findByLivros();
        livros.stream()
                .sorted(Comparator.comparing(Livros::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutorRegistrados() {

        List<Autor> autor = autorRepository.findAll();
        autor.stream()
                .sorted(Comparator.comparing(Autor::getName))
                .forEach(System.out::println);
    }

    private void listarAutoresVivosEmUmDeterminadoAno(){
        System.out.println("digite o ano que deseja buscar autores vivos:");
        var ano = leitura.nextInt();
        List<Autor> autor = autorRepository.listarAutoresVivosEmUmDeterminadoAno(ano);
        autor.stream()
                .sorted(Comparator.comparing(Autor::getName))
                .forEach(System.out::println);

    }

    private void listarLivroEmUmDeterminadoIdioma(){
        System.out.println("""
                Insira o idioma para realizar a buscar
                
                es- espanhol
                en- inglês
                fr- francês
                pt- português
                """);

        var idioma = leitura.nextLine();

        List<Livros> livros = autorRepository.listarLivrosEmUmDeterminadoIdioma(idioma);
        livros.stream()
                .sorted(Comparator.comparing(Livros::getTitulo))
                .forEach(System.out::println);

    }

    private void buscarAutorPorNome(){
        System.out.println("digite o nome do autor que deseja buscar: ");
        var autor = leitura.nextLine();
        List<Autor> autors = autorRepository.listarAutoresPorNome(autor);
        autors.stream()
                .sorted(Comparator.comparing(Autor::getName))
                .forEach(System.out::println);

    }


}
