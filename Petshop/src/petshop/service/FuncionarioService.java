package petshop.service;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import petshop.model.Funcionario;
import petshop.util.ValidadorEntrada;

/**
 * Classe responsável por gerenciar as operações relacionadas aos funcionários do petshop.
 */
public class FuncionarioService implements Service {
    private Map<String, Funcionario> funcionarios;

    public FuncionarioService(Map<String, Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     * Método para cadastrar um funcionario no sistema.
     * Solicita ao usuário as informações necessárias e cria um novo objeto Funcionario.
     * A chave do funcionario é sua matrícula.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void cadastrar(Scanner leia) {
        System.out.println("Insira o nome do funcionário: ");
        String nome = leia.nextLine();
        System.out.println("Insira a matrícula do funcionário: ");
        String matricula = leia.nextLine();
        System.out.println("Insira a qualificação do funcionário: ");
        String qualificacao = leia.nextLine();
        System.out.println("Insira uma descrição da função do funcionário: ");
        String descricaoFuncao = leia.nextLine();        
        System.out.println("Insira a carga horária semanal do funcionário: ");
        float cargaHoraria = leia.nextFloat();
        leia.nextLine();

        String chave = matricula;
        funcionarios.put(chave, new Funcionario(nome, matricula, qualificacao, descricaoFuncao, cargaHoraria));

        System.out.println("Dados do funcionário cadastrado:");
        System.out.println(funcionarios.get(chave).toStringDetalhado());

        System.out.println("Os dados estão corretos? (S/N)");
        String resposta = leia.nextLine().trim().toUpperCase();

        if (!resposta.equals("S")) {
            System.out.println("Cadastro cancelado.");
            funcionarios.remove(chave);
            return;
        }

        System.out.println("Funcionário cadastrado com sucesso!");
        System.out.println("Gostaria de cadastrar outro funcionario? (S/N)");
        resposta = leia.nextLine().trim().toUpperCase();

        if (resposta.equals("S")) {
            cadastrar(leia);
        } else {
            System.out.println("Cadastro finalizado.");
        }
    }

    /**
     * Método para consultar um funcionário no sistema.
     * Solicita ao usuário a matricula do funcionário e busca pelo funcionário com esse CPF.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void consultar(Scanner leia) {
        System.out.println("Matrícula do funcionário a ser consultado: ");
        String matricula = leia.nextLine();

        Funcionario funcionarioSelecionado = funcionarios.get(matricula);

        if (funcionarioSelecionado != null) {
            System.out.println("Dados do funcionario selecionado:");
            System.out.println(funcionarioSelecionado.toStringDetalhado());
        } else {
            System.out.println("Nenhum funcionario encontrado com a matrícula: " + matricula);
        } 
    }

    /**
     * Método para alterar os dados de um funcionario no sistema.
     * Solicita ao usuário a matrícula do funcionario e permite que ele escolha quais dados deseja alterar.
     * As opções incluem nome, matricula, qualificacao, descricaoFuncao, cargaHoraria, ou todos os dados.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void alterar(Scanner leia) {
        System.out.println("Matrícula do funcionario a ser alterado: ");
        String matricula = leia.nextLine();

        Funcionario funcionarioSelecionado = funcionarios.get(matricula);

        if (funcionarioSelecionado == null) {
            System.out.println("Nenhum funcionario encontrado com a matrícula: " + matricula);
            return;
        }

        System.out.println("Dados atuais do funcionario:");
        System.out.println(funcionarioSelecionado.toStringDetalhado());

        System.out.println("Quais dados do funcionario você deseja alterar?");
        System.out.println("1 — Nome");
        System.out.println("2 — Matrícula");
        System.out.println("3 — Qualificação");
        System.out.println("4 — Descrição da função");
        System.out.println("5 — Carga horária");
        System.out.println("6 — Todos os dados");

        int opcao = ValidadorEntrada.lerInteiroValido(leia, 1, 6);
        System.out.println("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1 -> {
                System.out.println("Insira o novo nome do funcionário: ");
                String novoNome = leia.nextLine();
                funcionarioSelecionado.setNome(novoNome);
            }
            case 2 -> {
                System.out.println("Insira a nova matrícula do funcionário: ");
                String novaMatricula = leia.nextLine();
                funcionarios.remove(funcionarioSelecionado.getMatricula());
                funcionarioSelecionado.setMatricula(novaMatricula);                
                funcionarios.put(novaMatricula, funcionarioSelecionado);
            }
            case 3 -> {
                System.out.println("Insira a nova qualificação do funcionário: ");
                String novaQualificacao = leia.nextLine();
                funcionarioSelecionado.setQualificacao(novaQualificacao);
            }
            case 4 -> {
                System.out.println("Insira a nova descricação da função do funcionário: ");
                String novaDescricaoFuncao = leia.nextLine();
                funcionarioSelecionado.setDescricaoFuncao(novaDescricaoFuncao);
            }
            case 5 -> {
                System.out.println("Insira a nova carga horária do funcionário: ");
                float novaCargaHoraria = leia.nextFloat();
                leia.nextLine();
                funcionarioSelecionado.setCargaHoraria(novaCargaHoraria);
            }
            case 6 -> {
                funcionarios.remove(funcionarioSelecionado.getMatricula());
                cadastrar(leia);
            }
        }
    }

     /**
     * Método para remover um funcionário do sistema.
     * Solicita ao usuário a matrícula do funcionário e remove o funcionário com essa matrícula.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void remover(Scanner leia) {
        System.out.println("Matrícula do funcionário a ser removido: ");
        String matricula = leia.nextLine();

        Funcionario funcionarioRemovido = funcionarios.remove(matricula);

        if (funcionarioRemovido == null) {
            System.out.println("Nenhum funcionário encontrado com a matrícula: " + matricula);
        } else {
            System.out.println("Funcionário removido com sucesso!");
        } 
    }

    @Override
    public void listar() {
        // Método para listar todos os animais cadastrados
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("\n=== RELATÓRIO DE FUNCIONÁRIOS ===");

            int contador = 1;
            for (Funcionario funcionario : funcionarios.values()) {
                System.out.println(contador + ". Funcionário: ");
                System.out.println(funcionario.toStringDetalhado());
                System.out.println("--------------------------------");
                contador++;
            }
        }
    }
} 