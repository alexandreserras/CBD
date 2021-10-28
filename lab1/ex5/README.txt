Neste exercicio vou utilizar um projeto maven.
O código java basicamente é um menu, onde um dado utilizador, após estar logado, pode escolher
qual funcionalidade quer utilizar

    Ideia do lado do Redis:
        Subscrições de um utilizador ficam num set
        As mensagens de um utilizador ficam numa lista
        
    Criei a JList -> classe onde todas as funcionalidades que utilizei com listas foram criadas
    Criei a JSet-> classe que tem as funcionalidades relacionadas com sets

Estrutura de como os dados ficam no redis:
    utilizadores:<user> -> Set de onde  todos os utilizadores
    utilizadores:subscrições:<user> -> Set com todas as subscrições de um user
    utilizadores:message:<user> -> Lista onde ficam todas as mensagens de um utilizador
