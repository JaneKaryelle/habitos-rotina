package pac;

import pac.dao.TarefaDAO;
import pac.modelo.Tarefa;

public class Main {
    public static void main(String[] args) {

        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDescricao("Arrumar casa");
        tarefa1.setFinalizada(Boolean.FALSE);

        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.salvar(tarefa1);


    }
}