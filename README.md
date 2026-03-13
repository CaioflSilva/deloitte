 Projeto Estoque - Deloitte Bootcamp Java

 Descrição
Este projeto implementa um sistema de CRUD de produtos utilizando Java e Spring Boot, com interface via 
 console e estrutura organizada em camadas.

O objetivo da atividade foi aplicar os princípios  SRP (Single Responsibility Principle)** e OCP (Open/Closed Principle) 
no desenvolvimento da solução.

 Repositório
Link do projeto no GitHub: (https://github.com/CaioflSilva/deloitte)

 Funcionalidades
Cadastrar produto
 Listar produtos
 Buscar produto por ID
 Atualizar produto
 Remover produto
 Validar dados do produto

 Estrutura do Projeto
O projeto foi organizado nas seguintes camadas:

 `controller`: responsável pelos endpoints da aplicação
 `service`: responsável pelas regras de negócio
 `repository`: responsável pelo acesso e manipulação dos dados
 `validation`: responsável pelas validações do sistema
 `exception`: responsável pelo tratamento de exceções
 `model`: representa a entidade Produto
 `console`: responsável pela interação via terminal

 Princípios aplicados

 SRP
    Cada classe possui uma responsabilidade específica:
 O `ProdutoService` centraliza a regra de negócio
 O `ProdutoRepository` cuida da persistência em memória
 Cada validação foi separada em sua própria classe
 O console apenas coleta dados e chama o service

 OCP
  As validações foram implementadas por meio da interface `ProdutoValidation` e executadas pelo `ProdutoValidator`.

Dessa forma, novas validações podem ser adicionadas sem alterar o `ProdutoService`.

 Desafio extra
Como desafio extra, foi criada a classe `DescricaoValidation`, adicionando uma nova validação ao sistema sem 
necessidade de modificar a lógica principal do service.

Também foram ajustados:
 o model `Produto`
 o fluxo do console
 os testes unitários

 Tecnologias utilizadas
 Java 17
 Spring Boot
 JUnit 5
 Mockito
 H2 Database

 Como executar
Clone o repositório
 Abra o projeto na IDE
 Execute a classe `SistemaEstoque`
 Utilize o menu exibido no console

Como rodar os testes
Execute os testes pela IDE ou rode a suíte de testes do projeto.

 Resultado
Todos os testes passaram com sucesso:

 22 testes executados
 22 testes aprovados

  Autor
    Caio Felipe