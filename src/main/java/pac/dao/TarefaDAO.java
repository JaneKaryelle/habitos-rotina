package pac.dao;

import pac.infra.ConnectionFactory;
import pac.modelo.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TarefaDAO {

    private ConnectionFactory fabrica;

    public TarefaDAO() {
        this.fabrica = new ConnectionFactory();
    }

    public void salvar(Tarefa tarefa) {
        try (Connection conexao = fabrica.recuperarConexao()) {
            String sql = "INSERT INTO tarefa (descricao, finalizada) VALUES (?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isFinalizada());
            stmt.execute();


            System.out.println("Salvando uma tarefa... " + tarefa.getDescricao());

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar tarefa no banco!", e);
        }
    }

    public void listarTodas() {

        String sql = "SELECT * FROM tarefa";
        try (Connection conexao = fabrica.recuperarConexao()) {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet relatorio = stmt.executeQuery();

            while (relatorio.next()) {
                Long id = relatorio.getLong("id");
                String descricao = relatorio.getString("descricao");
                boolean finalizada = relatorio.getBoolean("finalizada");
                System.out.println(id + "- Tarefa: " + descricao + " - " + finalizada);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tarefas", e);
        }
    }

    public void atualizar(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET descricao = ?, finalizada = ? WHERE id = ?";
        try (Connection conexao = fabrica.recuperarConexao()) {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isFinalizada());
            stmt.setLong(3, tarefa.getId());
            stmt.execute();
            System.out.println("Atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tarefas no banco!", e);
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        try (Connection conexao = fabrica.recuperarConexao()) {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir tarefas no banco!", e);
        }
    }
}
