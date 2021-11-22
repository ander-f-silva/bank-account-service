# Documentação sobre arquitetura do Projeto

## Arquitetura inicial

Primeiramente, a minha idéa foi pensar em uma arquitetura simples, visando somente no que a aplicação fazer, que neste caso é administrar uma conta para cliente com funcionalidade de cadastro básico, ter operações para movimentar a conta e relizar consultas para ver quais as movimentações realizadas. Para atender este requisito de forma simplista, eu pensei em uma aplicação banckend recebendo os dados de uma cliente externo e registrando em uma base de dados. Com esta visão eu consigo ver melhor alguns pontos de falha e pensar em alguma soluções.

Nota importante para esta solução é que não será a implementação para o teste e sim uma idéia para melhoras futuras.

![Arq1](https://github.com/ander-f-silva/account-service/blob/main/document/image/start_architecture.png)

Para finalizar, geralmente com esta visão inicial podemos ver que a aplicação tem um ponto de falha que a comunição somente uma inbstancia de banco de dados e caso o banco estaja fora do ar a aplicação não funcionará, outro ponto, dependendo do hardware que esta aplicação esta, podemos ter problemas de limitação de recurso como memória, disco e rede, neste caso vamos pensar um pouco melhor em uma solução de réplica (Lembrando que estou focando a aplicação rodará em um container, mas ela será aguinóstica ao ambimente local ou a infra). 

Para os próxima solução vou ilustrar o que eu pensei para resolver este problema inicialmente. 


## Solução para a Arquitetura inicial

Neste momento comecei a pensar em trabalhar com distribuir a carga da aplicação usando proxy reverço que encanhará a requisição para o container, dessa forma vamos exigir menos de algumas instancias (pensando em deploiar em uma cloud, penso em usar K8 que já tem várias soluções nativas para este caso, usando deploy, service e outros recursos). Em relaçõa base estou trabalhando com replicas para trabalhar no caso de um banco cair a outra instancia toma o controle, outro ponto interensante é que podemos distruir as funcionalidade para um banco de escrita e outra para leitura.

Nota importate para esta solução é que pretendo implementa esta solução para rodar locamente usando docker com docker-compose, para ambiente e cloud se possível vou usar a AWS.

![Arq2](https://github.com/ander-f-silva/account-service/blob/main/document/image/second_architecture.png)

Para finalizar a outra alternativa para estes problemas que visa mais implementação e que poderia ser ideal entre "aspas" e vou ilustrar a idéias aqui. Esta solução não será implementa por questões de tempo.

## Solução "ideal" para a Arquitetura inicial
