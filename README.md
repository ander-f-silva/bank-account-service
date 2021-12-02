# Projeto Serviço de Conta para Banco

Desenvolvi uma solução para realizar a criação de contas para um banco e realizar as operações de movimentão como deposito e saque.

- Registrar a conta;
- Registrar o saque;
- Registrar o deposito;
- Consultar o saldo;
- Consultar o extrato;
- Bloquear a conta.

## Reflexão sobre o problema

Para construir a aplicação utilizei o framework Spring, pois, por ter maior conhecimento nos seus recursos para criar os testes, configurar o acesso ao banco de dados e desenvolver as apis de forma rápida.

Para este projeto não será há necessidade de instalar as ferramentas para fazer o build e o deploy. Neste caso estou a utilizar o wrapper do maven que está embutido no projeto e docker-compose que monta o container do banco de dados, da aplicação e do proxy reverso. 

Por entender que o mundo de desenvolvimento esta globalizado, utilizei como idioma o Inglês para escrever o código e as apis.

Abaixo há os tópicos com o que documentatei antes de escrever a código onde há algumas sessões para temas:

- [Arquitetura](https://github.com/ander-f-silva/bank-account-service/blob/develop/document/architecture.md)
- [Desenho das APIS](https://github.com/ander-f-silva/bank-account-service/blob/develop/document/contract_api.md)
- [Desenho das DER](https://github.com/ander-f-silva/bank-account-service/blob/develop/document/database.md)


## Tecnologias utilizadas

* Linguagem Java - Versão 11

``` shell script
openjdk 11.0.5 2019-10-15 LTS
OpenJDK Runtime Environment Zulu11.35+15-CA (build 11.0.5+10-LTS)
OpenJDK 64-Bit Server VM Zulu11.35+15-CA (build 11.0.5+10-LTS, mixed mode)
```

* Maven 3 - Ferramenta de Build

``` shell script
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /Users/andferreira/.m2/wrapper/dists/apache-maven-3.6.3-bin/1iopthnavndlasol9gbrbg6bf2/apache-maven-3.6.3
Java version: 11.0.5, vendor: Azul Systems, Inc., runtime: /Users/andferreira/.sdkman/candidates/java/11.0.5-zulu
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.7", arch: "x86_64", family: "mac"

```

* Repositório e versão de código - Github e Git;

* Migration - Flayway;

* Banco de Dados - H2 (teste) e Mysql (docker);

* Spring Web (MVC) - Framerwork Web para geração das API's (versão 2.5.7) com Tomcat 9;

* Spring Boot - Setup de projeto.


## Documentação através do swagger (versão 3.X)

O projeto possui uma documentação de API atravez do Open API.

Acesse http://localhost:8090/swagger-ui/index.html#/ para ver e testar os endpoints.

![Open API](https://github.com/ander-f-silva/bank-account-service/blob/develop/document/image/open_api.png)

### Acess as api através do CURS

#### Registrar a conta
#### Registrar o saque
#### Registrar o deposito
#### Consultar o saldo
#### Consultar o extrato
#### Bloquear a conta

## Para realizar o build e os testes do programa

Primeiro passo faça o clone do projeto e depois siga os passos abaixo.

Segue:

Acesse o projeto

```
cd bank-account-service
```

### Executar os testes

Execute na raiz do comando para rodar os testes:

```shell script
./mvnw test
```

### Executar o build

Para executar o build:

```shell script
./mvnw clean package
```

### Executar o deploy


Fazer o deploy e construir a infra com replica de base de dados e aplicação com load balance com proxy reverso:

```shell script
docker-compose up --scale app=3
```

Se tudo ocorreu bem acesso o ``http://localhost:8090/actuator/health`` para verificar se aplicação esta no ar.

## Gestão do Projeto e técnicas para construção da API

Não precisei usar Kaban para administrar as atividades, mas sempre me foquei na documentação passada.

As etapas foram:

*  Criação do projeto no https://start.spring.io/;
*  Construção dos testes integrados;
*  Construção da camada de aplicação;
*  Construção das migrations para criação das tabelas;
*  Construção da camada de infraestrutura;
*  Construção da camada de business;

Comecei a aplicação pensando em TDD para me ajudar a construir e validar alguns cenários para entradas das apis.

Pode acessar a página de PR para histórico da construção. Página: [Pull Requests](https://github.com/ander-f-silva/bank-account-service/pulls?q=is%3Apr+is%3Aclosed)

As tarefas foram gerenciadas por este Projecto [Account Service](https://github.com/ander-f-silva/bank-account-service/projects/1) com o Kanban 
