# 📚 LiterAlura

Bem-vindo ao **LiterAlura**, o seu catálogo de livros baseado em dados reais da API [Gutendex](https://gutendex.com/), que reúne mais de 70 mil títulos de domínio público! Este projeto foi desenvolvido com o objetivo de praticar o consumo de APIs REST e a persistência de dados em bancos de dados relacionais com **Java**, **Spring Boot** e **PostgreSQL**.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **IntelliJ IDEA**
- API pública **Gutendex**

---

## 🎯 Objetivo do Projeto

Consumir dados de uma API pública (Gutendex), permitir ao usuário interagir via terminal para:

1. Buscar livros pelo título.
2. Persistir os dados retornados da API em um banco de dados.
3. Realizar consultas nos dados armazenados.
4. Explorar filtros por autores, anos e idiomas.

---

## 💻 Funcionalidades

O projeto possui 5 opções interativas disponíveis via terminal:

### 1. 🔍 Buscar livro pelo título (consome API)
- Faz uma requisição à API Gutendex com base no nome informado.
- Exibe dados como título, autor, idioma e número de downloads.
- Persiste o livro e o autor no banco de dados.

### 2. 📚 Listar livros registrados (banco de dados)
- Mostra todos os livros previamente salvos.

### 3. 👨‍🏫 Listar autores cadastrados
- Exibe todos os autores com seus respectivos livros e dados como ano de nascimento e falecimento.

### 4. 🗓️ Listar autores vivos em determinado ano
- Filtra autores que estavam vivos no ano informado.

### 5. 🌍 Listar livros por idioma
- Filtra livros por idiomas suportados (PT, EN, ES, FR).
- Caso não haja livros no idioma solicitado, exibe mensagem de aviso.

---

## 🛠️ Como Executar

### Pré-requisitos

- Java 17+
- PostgreSQL
- Maven
- IDE (sugestão: IntelliJ)

### Passos

1. **Clone o projeto:**

```bash
git clone https://github.com/seu-usuario/literalura.git
cd literalura
