
package atividade.dados;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class IndConnector {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/vkhutemas",
                   "root", "senha");
        } catch (SQLException sql) {
            throw new NullPointerException ("Não se assuste, só a conexão com o Banco de Dados que não foi bem sucedida mesmo....\\n\\n"
                    + "ABAIXO, A MENSAGEM DE ERRO OFICIAL DO JAVA SQL DRIVER MANAGER PARA PROUN/PROETO:\\n" + sql);
            // return null; 
        }
    }
}

