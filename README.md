# Sistema de Gerenciamento de Portfólio de Projetos

Sistema para gerenciar os dados do portfólio de projetos de uma empresa, permitindo o cadastro e acompanhamento de projetos, membros e pessoas.

## Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

## Instalação

1. Clone o repositório:
```bash
git clone https://github.com/DeveloperSeniorBR/Portfolio.git
```

2. Navegue até o diretório do projeto:
```bash
cd Portfolio
```

3. Compile o projeto:
```bash
mvn clean install
```

## Execução

1. Execute o projeto:
```bash
mvn spring-boot:run
```

2. Acesse a aplicação:
```
http://localhost:8080
```

3. Acesse o console do H2:
```
http://localhost:8080/h2-console
```

## API REST

O sistema disponibiliza web services REST para gerenciamento de projetos, pessoas e membros.

### API de Projetos

#### Endpoints
```
GET    /api/projetos          - Lista todos os projetos
GET    /api/projetos/{id}     - Busca um projeto específico
POST   /api/projetos          - Cria um novo projeto
PUT    /api/projetos/{id}     - Atualiza um projeto existente
DELETE /api/projetos/{id}     - Exclui um projeto
```

#### Exemplo de Requisição (POST/PUT)
```json
{
    "nome": "Projeto Teste",
    "dataInicio": "2024-01-01",
    "dataPrevisaoFim": "2024-12-31",
    "dataFim": null,
    "descricao": "Descrição do projeto",
    "status": "EM_ANALISE",
    "orcamento": 100000.00,
    "risco": "BAIXO",
    "gerenteId": 1
}
```

#### Validações
- O gerente deve existir
- O gerente deve ter atribuição de gerente
- Não é possível excluir projetos com status iniciado, em andamento ou encerrado

### API de Pessoas

#### Endpoints
```
GET    /api/pessoas          - Lista todas as pessoas
GET    /api/pessoas/{id}     - Busca uma pessoa específica
POST   /api/pessoas          - Cria uma nova pessoa
PUT    /api/pessoas/{id}     - Atualiza uma pessoa existente
DELETE /api/pessoas/{id}     - Exclui uma pessoa
```

#### Exemplo de Requisição (POST/PUT)
```json
{
    "nome": "João Silva",
    "dataNascimento": "1990-01-01",
    "cpf": "123.456.789-00",
    "funcionario": true,
    "gerente": false,
    "atribuicao": "Desenvolvedor"
}
```

### API de Membros

#### Endpoints
```
POST   /api/membros          - Cria um novo membro
```

#### Exemplo de Requisição
```json
{
    "pessoaId": 1,
    "projetoId": 1,
    "atribuicao": "Desenvolvedor Backend"
}
```

#### Validações
- A pessoa deve existir
- A pessoa deve ser funcionário
- O projeto deve existir

## Funcionalidades

### Projetos
- Cadastro de projetos com nome, data de início, gerente responsável, previsão de término, data real de término, orçamento total, descrição e status
- Classificação de risco (baixo, médio, alto)
- Status do projeto (em análise, análise realizada, análise aprovada, iniciado, planejado, em andamento, encerrado, cancelado)
- Restrição de exclusão para projetos com status iniciado, em andamento ou encerrado

### Membros
- Associação de membros aos projetos via web service
- Cadastro de membros com nome e atribuição (cargo)
- Restrição para associar apenas pessoas com atribuição de funcionário

### Pessoas
- Cadastro de pessoas com nome, data de nascimento, CPF e atribuição
- Definição de pessoa como funcionário ou não
- Definição de pessoa como gerente ou não

## Testes

O sistema possui testes unitários e de integração para garantir a qualidade do código e a correta implementação das regras de negócio.

### Testes Unitários

#### Projeto
- Testes para verificar a regra de exclusão baseada no status
- Cobertura de todos os status possíveis

#### Membro
- Testes para verificar a criação de membros com atribuição
- Testes para verificar a associação com pessoas não funcionárias

### Testes de Integração

#### Web Service de Membros
- Testes para verificar o cadastro de membros
- Testes para verificar a autenticação
- Testes para verificar a validação dos dados

### Executando os Testes

```bash
mvn test
```

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Spring Security
- Hibernate
- H2 Database
- JSP
- Bootstrap
- JUnit 5
- Hamcrest
- Spring Security Test

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/portfolio/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       └── PortfolioApplication.java
│   ├── resources/
│   │   └── application.properties
│   └── webapp/
│       └── WEB-INF/
│           └── views/
└── test/
    ├── java/
    │   └── com/portfolio/
    │       ├── controller/
    │       └── model/
    └── resources/
        └── application-test.properties
``` 