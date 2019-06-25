
package atividade.dados;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class IndConnector {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/unovis",
                   "root", "senha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
    Supostamente, os objetos deveriam ser armazenados e resgatados do banco de dados.
    Inclusive para isso fiz o arquivo proun.sql para construir o BD
    porém a complexidade do Select de Obras seria tamanha para o prazinho mixuruca
    que a estrutura de listas foi mais que aceita.

    Então essa classe está aqui como uma segunda torre de igreja incompleta de MG ou BA, um cotoco de outros planos decepados sem conclusão.
*/