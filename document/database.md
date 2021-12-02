# Documentação sobre a base de dados

## Migração dos dados


Hoje para executar o script de base de dados estou utilizando o utilitario flyway vinculado a spring boot, ou seja, quado a aplicação for iniciada scripts de ddl serão executados.

Os script estão no diretório: ```src/main/resources/db/migration```

## Modelo DER

Abaixo esta o desenho da solução:

![Arq1](https://github.com/ander-f-silva/bank-account-service/blob/develop/document/image/database.png)


Para os próxima solução vou ilustrar o que eu pensei para resolver este problema inicialmente. 


## Replicação da base de dados.

Esta solução é transparente para aplicação pois estou resolvendo isso utilizando a string de conexão.
No docker-compose olhe que dois serviços ```mysql-master``` e ```mysql-slave``` conforme a documentação da arquitetura 
nas sessão -[Solução para a Arquitetura inicial](https://github.com/ander-f-silva/bank-account-service/blob/develop/document/image/database.png)

Convido a fazer um teste interessante, que é derrubar uma instancia e as operações, veja que ao subir a instancia derrubada o banco fará a replicação automaticamente.