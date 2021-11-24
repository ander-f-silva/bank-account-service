# Documentação do contrato de API

Nesta etapa eu prefiro primeiro desenhar a api em quadro branco e definir quais serão recurso, status http e métodos que elas terão para facilitar o desenvolvimento.
Abaixo tem a ilustração da imagem com o contrato da API, onde pensei seguir o máximo o padrão ```REST```, não esta 100%, mas acredito que o form que foi construi, ajuda também na implementação.

![Contract](https://github.com/ander-f-silva/bank-account-service/blob/main/document/image/contract_api.png)

## Versão de API

Para versionar uma API estou utilizando o header ```Accept-version``` com v.X.

