package pac.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Conexão com banco de dados
public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/lista_tarefas?useTimezone=true&serverTimezone=UTC";

    private static final String USUARIO = "root";

    private static final String SENHA = System.getenv("DB_PASSWORD");

    public Connection recuperarConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao Banco de Dados.", e);
        }
    }
}
