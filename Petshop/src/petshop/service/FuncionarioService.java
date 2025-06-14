package petshop.service;

import java.util.Map;
import java.util.Scanner;

import petshop.modelo.Funcionario;

/**
 * Classe responsável por gerenciar as operações relacionadas aos funcionários do petshop.
 */
public class FuncionarioService implements Service {
    private Map<String, Funcionario> funcionarios;

    public FuncionarioService(Map<String, Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public void cadastrar(Scanner leia) {
        System.out.println("Insira o nome: ");
        String nome = leia.nextLine();
        System.out.println("Insira a matrícula: ");
        String matricula = leia.nextLine();
        System.out.println("Insira a qualificacao");
        String qualificacao = leia.nextLine();
        System.out.println("Insira a descrição da função: ");
        String descricaoFuncao = leia.nextLine();
        System.out.println("Insira a carga horária semanal: ");
        float cargaHoraria = Float.parseFloat(leia.nextLine());

        funcionarios.put(matricula, new Funcionario(nome, matricula, qualificacao, descricaoFuncao, cargaHoraria));
        funcionarios.get(matricula).consultarFuncionario();
    }
    
    @Override
    public void consultar(Scanner leia) {
        System.out.println("Insira a matrícula do funcionário a ser consultado: ");
        String matricula = leia.nextLine();

        if (funcionarios.containsKey(matricula)) {
            funcionarios.get(matricula).consultarFuncionario();
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    @Override
    public void alterar(Scanner leia) {
        System.out.println("Insira a matrícula do funcionário a ser alterado: ");
        String matricula = leia.nextLine();

        if (funcionarios.containsKey(matricula)) {
            System.out.println("Novo nome: ");
            String nome = leia.nextLine();
            System.out.println("Nova matrícula: ");
            String novaMatricula = leia.nextLine();
            System.out.println("Nova qualificação: ");
            String qualificacao = leia.nextLine();
            System.out.println("Nova descrição da função: ");
            String descricaoFuncao = leia.nextLine();
            System.out.println("Nova carga horária semanal: ");
            float cargaHoraria = Float.parseFloat(leia.nextLine());

            funcionarios.get(matricula).alterarCadastro(nome, novaMatricula, qualificacao, descricaoFuncao, cargaHoraria);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    @Override
    public void remover(Scanner leia) {
        System.out.println("Insira a matrícula do funcionário a ser removido: ");
        String matricula = leia.nextLine();

        if (funcionarios.containsKey(matricula)) {
            funcionarios.remove(matricula);
            System.out.println("Funcionário removido com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
}