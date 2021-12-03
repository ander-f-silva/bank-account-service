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

Importante enfatizar é que para nomeclatura e organização dos pacotes eu usei as conversões documentas ou mencionadas 
pela oracle, a empresa que cuida do spring boot

Hoje tenho conhecimento que dois tipo de organização de pacotes no mundo java. Uma é organização de package by feature e by layer
, neste projeto eu mesclei os dois modelos e para separação de nome que possui "-" eu substituo por "_" conforme a documentação.

Abaixo os links importantes para explicar o uso e conversões dos pacotes que apliquei no projeto:

- Pacote com "_" neste exemplo: bank-account-service.dock.com.br (http://account-service.dock.com.br) → package `br.com.dock.account_service`  [https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html)
- Modelo package by feacture [https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code)
- Modelo de packages [http://www.javapractices.com/topic/TopicAction.do?Id=205#:~:text=The package-by-feature style still honors the idea of,t seem necessary or desirable](http://www.javapractices.com/topic/TopicAction.do?Id=205#:~:text=The%20package%2Dby%2Dfeature%20style%20still%20honors%20the%20idea%20of,t%20seem%20necessary%20or%20desirable).
- Outras fontes [https://phauer.com/2020/package-by-feature/](https://phauer.com/2020/package-by-feature/)

**Nota**: Um ponto importante é sobre a implementação dos teste, como eu fiz um desenvolvimento que começou na camada de api, fiz os teste de integrações que cobre vários cenários da aplicação e como
muitas operações se tratavam de manipular dados da base me foquei neles no teste, porém pretendo no futuro implementar os teste de unidade, principalmente para operações de saque e deposito.

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

![Open API](https://github.com/ander-f-silva/bank-account-service/blob/develop/document/image/swagger.png)

### Acess as api através do CURS

#### Registrar a conta

```shell
curl --location --request POST 'http://localhost:8090/accounts' \
--header 'Accept-Version: v1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "person": {
        "name": "Anderson",
        "document": "65499776067",
        "dateBirthday": "1990-01-01"
    },
    "withdrawalDayLimit": 10000.00,
    "accountType": "CHECKING_ACCOUNT"
}'
```

#### Registrar o saque

```shell
curl --location --request POST 'http://localhost:8090/withdraws?accountId=1' \
--header 'Accept-Version: v1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 500.0
}'
```

#### Registrar o deposito

```shell
curl --location --request POST 'http://localhost:8090/deposits?accountId=1' \
--header 'Accept-Version: v1' \
--header 'Content-Type: application/json' \
--data-raw '{
"amount": 1000.0
}'
```

#### Consultar o saldo

```shell
curl --location --request GET 'http://localhost:8090/balances?accountId=1' \
--header 'Accept-Version: v1'
```

#### Consultar o extrato

```shell
curl --location --request GET 'http://localhost:8090/accounts/1/transactions' \
--header 'Accept-Version: v1'
```

#### Bloquear a conta

```shell
curl --location --request PATCH 'http://localhost:8090/accounts/1/blocks' \
--header 'Accept-Version: v1' \
--header 'Content-Type: application/json' \
--data-raw '{
"value":true
}'
```

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
