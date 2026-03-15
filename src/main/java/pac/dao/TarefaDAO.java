package pac.dao;

import pac.infra.ConnectionFactory;
import pac.modelo.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TarefaDAO {

    private ConnectionFactory fabrica;

    public TarefaDAO() {
        this.fabrica = new ConnectionFactory();
    }
    public void salvar(Tarefa tarefa) {
        try(Connection conexao = fabrica.recuperarConexao()) {
            String sql = "INSERT INTO tarefa (descricao, finalizada) VALUES (?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isFinalizada());
            stmt.execute();


            System.out.println("Salvando uma tarefa... " + tarefa.getDescricao());

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar tarefa no banco!",e);
        }
    }
}
