# Documentação sobre arquitetura do Projeto

## Arquitetura inicial

Primeiramente, a minha idéa foi pensar em uma arquitetura simples, visando somente no que a aplicação fazer, que neste caso é administrar uma conta para cliente com funcionalidade de cadastro básico, ter operações para movimentar a conta e relizar consultas para ver quais as movimentações realizadas. Para atender este requisito de forma simplista, eu pensei em uma aplicação banckend recebendo os dados de uma cliente externo e registrando em uma base de dados. Com esta visão eu consigo ver melhor alguns pontos de falha e pensar em alguma soluções.

Nota imporante para esta solução é que não será a implementação para o teste e sim uma idéia para melhoras futuras.

![Arq1](https://github.com/ander-f-silva/account-service/blob/main/document/image/start_architecture.png)

Para finalizar, geralmente com esta visão inicial podemos ver que a aplicação tem um ponto de falha que a comunição somente uma inbstancia de banco de dados e caso o banco estaja fora do ar a aplicação não funcionará, outro ponto, dependendo do hardware que esta aplicação esta, podemos ter problemas de limitação de recurso como memória, disco e rede, neste caso vamos pensar um pouco melhor em uma solução de réplica (Lembrando que estou focando a aplicação rodará em um container, mas ela será aguinóstica ao ambimente local ou a infra). 

Para os próxima solução vou ilustrar o que eu pensei para resolver este problema inicialmente. 