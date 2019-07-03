
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
            throw new NullPointerException ("Falha na Conexão com Banco de Dados " + 
											"[deixamos a mensagem de erro assustadora logo ao final dessa aqui mais fofinha]\\n" + 
											"Calma, pode ser que funcione depois.... \\n\\n JAVA SQL DRIVER MANAGER OFICIALMENTE PARA PROUN/PROETO:\\n" + sql);
					//"Não se assuste, só a conexão com o Banco de Dados que não foi bem sucedida mesmo");
            //	return null; */
        }
    }
}

