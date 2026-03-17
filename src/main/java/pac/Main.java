package pac;

import pac.dao.TarefaDAO;
import pac.modelo.Tarefa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Pergunta ao usuario a tarefa
        System.out.println("--- Sistema de Tarefas ---");
        System.out.println("Qual tarefa você deseja salvar no banco de dados?");
        System.out.print("Digite aqui: ");


        String descricaoDigitada = teclado.nextLine();

        Tarefa novaTarefa = new Tarefa();


        novaTarefa.setDescricao(descricaoDigitada);
        novaTarefa.setFinalizada(false); // Como é uma tarefa nova, ela fica como "não finalizada"


        TarefaDAO operario = new TarefaDAO();
        operario.salvar(novaTarefa);

        System.out.println("✅ Tarefa '" + descricaoDigitada + "' salva com sucesso!");

        //lista todas as tarefas do banco
        TarefaDAO operacao = new TarefaDAO();
        operacao.listarTodas();

        // UPDATE no banco de dados
        TarefaDAO operario2 = new TarefaDAO();

            Tarefa tarefaModificada = new Tarefa();
            tarefaModificada.setId(3L);
            tarefaModificada.setDescricao("Trocar a torneira");
            tarefaModificada.setFinalizada(true);
            operario2.atualizar(tarefaModificada);

        //Excluir tarefa
        TarefaDAO operacao3 = new TarefaDAO();
        operacao3.excluir(3L);
        operacao3.listarTodas();
    }

}