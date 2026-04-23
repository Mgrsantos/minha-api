# 🚀 Minha API REST — Spring Boot

<div align="center">

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

**API RESTful completa com autenticação JWT, Docker e boas práticas de mercado.**

[📖 Documentação](#-documentação) • [🚀 Como rodar](#-como-rodar) • [📋 Endpoints](#-endpoints) • [🧪 Testes](#-testes)

</div>

---

## ✨ Sobre o projeto

API desenvolvida do zero com **Spring Boot 3** e **Java 17**, seguindo arquitetura em camadas e boas práticas do mercado. O projeto cobre desde um CRUD completo até autenticação JWT, containerização com Docker e testes automatizados.

---

## 🛠️ Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Linguagem | Java 17 |
| Framework | Spring Boot 3 |
| Segurança | Spring Security + JWT (jjwt 0.11.5) |
| Banco de dados | PostgreSQL 15 |
| ORM | Spring Data JPA / Hibernate |
| Validação | Bean Validation (Jakarta) |
| Documentação | SpringDoc OpenAPI 3 (Swagger UI) |
| Testes | JUnit 5 + Mockito |
| Container | Docker + Docker Compose |

---

## ⚙️ Funcionalidades

- ✅ CRUD completo de Produtos
- ✅ Cadastro e autenticação de usuários
- ✅ Tokens JWT com expiração de 24h
- ✅ Senhas criptografadas com BCrypt
- ✅ Proteção de endpoints por autenticação
- ✅ Validação de dados com mensagens customizadas
- ✅ Tratamento global de erros (Exception Handler)
- ✅ Documentação interativa via Swagger UI
- ✅ Testes unitários e de integração
- ✅ Containerização completa com Docker

---

## 🚀 Como rodar

### Pré-requisitos
- [Java 17+](https://adoptium.net/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Git](https://git-scm.com/)

### ▶️ Rodando com Docker (recomendado)

```bash
# 1. Clone o repositório
git clone https://github.com/Mgrsantos/minha-api.git
cd minha-api

# 2. Suba os containers (API + PostgreSQL)
docker-compose up --build
```

A API estará disponível em: **`http://localhost:8080`**

### ▶️ Rodando localmente

```bash
# 1. Configure o banco no application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/minha_api
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

# 2. Execute o projeto
./mvnw spring-boot:run
```

---

## 📋 Endpoints

### 🔐 Autenticação (público)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/auth/registrar` | Cadastra novo usuário |
| `POST` | `/auth/login` | Retorna token JWT |

**Exemplo de registro:**
```json
POST /auth/registrar
{
  "email": "usuario@email.com",
  "senha": "123456"
}
```

**Exemplo de login:**
```json
POST /auth/login
{
  "email": "usuario@email.com",
  "senha": "123456"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 📦 Produtos (requer token JWT)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Busca produto por ID |
| `POST` | `/produtos` | Cria novo produto |
| `PUT` | `/produtos/{id}` | Atualiza produto |
| `DELETE` | `/produtos/{id}` | Remove produto |

**Header obrigatório:**
```
Authorization: Bearer SEU_TOKEN_JWT
```

**Exemplo de criação:**
```json
POST /produtos
{
  "nome": "Notebook",
  "preco": 3500.00
}
```

---

## 📖 Documentação

Com a aplicação rodando, acesse o **Swagger UI**:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 Testes

```bash
# Rodar todos os testes
./mvnw test
```

**Cobertura atual:** 11 testes — `ProdutoServiceTest` (7) + `ProdutoControllerTest` (3) + `MinhaApiApplicationTests` (1)

---

## 🏗️ Arquitetura

```
src/
├── main/java/com/gapi/minha_api/
│   ├── controller/        # Endpoints REST
│   ├── service/           # Regras de negócio
│   ├── repository/        # Acesso ao banco
│   ├── model/             # Entidades JPA
│   ├── dto/               # Data Transfer Objects
│   ├── security/          # JWT, Filtros, Config
│   └── exception/         # Tratamento global de erros
└── test/                  # Testes unitários e de integração
```

---

## 👩‍💻 Autora

**Gabriela Rondon** — Desenvolvedora Backend

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/seu-linkedin)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Mgrsantos)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:Gabrielasaantos294@gmail.com)

---

<div align="center">
  Desenvolvido com ☕ e muito Java
</div>
