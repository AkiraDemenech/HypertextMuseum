
package atividade.dados;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class IndConnector {
    public static EntityManager getConnection() {
        return Persistence.createEntityManagerFactory("lunarcharsky").createEntityManager();
    }
}

/*
    Supostamente, os objetos deveriam ser armazenados e resgatados do banco de dados.
    Inclusive para isso fiz o arquivo proun.sql para construir o BD
    porém a complexidade do Select de Obras seria tamanha para o prazinho mixuruca
    que a estrutura de listas foi mais que aceita.

    Então essa classe está aqui como uma segunda torre de igreja incompleta de MG ou BA, um cotoco de outros planos decepados sem conclusão.

  //POSFÁCIO À SEGUNDA EDIÇÃO: obsolescência desfeita pela responsabilidade da JPA;
*/