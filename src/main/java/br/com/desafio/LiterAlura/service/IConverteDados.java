package br.com.desafio.LiterAlura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe); // cabeçalho do meto para converte dados genericos
}
