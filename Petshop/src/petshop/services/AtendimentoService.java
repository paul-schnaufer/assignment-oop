package petshop.services;

import java.util.Map;
import java.util.Scanner;

import petshop.model.Animal;
import petshop.model.Atendimento;
import petshop.model.Cliente;
import petshop.model.Funcionario;
import petshop.util.ValidadorEntrada;

import petshop.repository.BancoDeDadosEmMemoria;


/**
 * Classe responsável por gerenciar as operações relacionadas aos clientes do petshop.
 */
public class AtendimentoService implements Service {
    private Map<String, Atendimento> atendimentos;
    private Map<String, Cliente> clientes;
    private Map<String, Animal> animais;
    private Map<String, Funcionario> funcionarios;

    public AtendimentoService(Map<String, Atendimento> atendimentos, Map<String, Cliente> clientes) {
        this.atendimentos = atendimentos;
        this.clientes = clientes;
    }

    /**
     * Método para cadastrar um atendimento no sistema.
     * Solicita ao usuário as informações necessárias e cria um novo objeto Atendimento.
     * A chave do atentendimento é seu código.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void cadastrar(Scanner leia) {
        System.out.println("Insira o código do atendimento: ");
        String codigo = leia.nextLine();
        System.out.println("Insira a data do atendimento: ");
        String data = leia.nextLine();
        System.out.println("Insira o CPF do cliente: ");
        String cpf = leia.nextLine();
        Cliente cliente = clientes.get(cpf);
        System.out.println("Insira o nome do animal: ");
        String nome = leia.nextLine();
        Animal animal = animais.get(nome + " — " + cpf);        
        System.out.println("Insira a matrícula do funcionário: ");
        String matricula = leia.nextLine();
        Funcionario funcionario = funcionarios.get(matricula);

        String chave = codigo;
        atendimentos.put(chave, new Atendimento(codigo, data, cliente, animal, funcionario));

        System.out.println("Dados do atendimento cadastrado:");
        System.out.println(atendimentos.get(chave).toStringDetalhado());

        System.out.println("Os dados estão corretos? (S/N)");
        String resposta = leia.nextLine().trim().toUpperCase();

        if (!resposta.equals("S")) {
            System.out.println("Cadastro cancelado.");
            atendimentos.remove(chave);
            return;
        }

        System.out.println("Atendimento cadastrado com sucesso!");
        System.out.println("Gostaria de cadastrar outro atendimento? (S/N)");
        resposta = leia.nextLine().trim().toUpperCase();

        if (resposta.equals("S")) {
            cadastrar(leia);
        } else {
            System.out.println("Cadastro finalizado.");
        }
    }

    /**
     * Método para consultar um atendimento no sistema.
     * Solicita ao usuário o código do atendimento e busca pelo atendimento com esse código.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void consultar(Scanner leia) {
        System.out.println("Código do atendimento a ser consultado: ");
        String codigo = leia.nextLine();

        Atendimento atendimentoSelecionado = atendimentos.get(codigo);

        if (atendimentoSelecionado != null) {
            System.out.println("Dados do atendimento selecionado:");
            System.out.println(atendimentoSelecionado.toStringDetalhado());
        } else {
            System.out.println("Nenhum atendimento encontrado com o código: " + codigo);
        } 
    }

    /**
     * Método para alterar os dados de um atendimento no sistema.
     * Solicita ao usuário o código do atendimento e permite que ele escolha quais dados deseja alterar.
     * As opções incluem codigo, data, cliente, animal, funcionario, ou todos os dados.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void alterar(Scanner leia) {
        System.out.println("Código do atendimento a ser alterado: ");
        String codigo = leia.nextLine();

        Atendimento atendimentoSelecionado = atendimentos.get(codigo);

        if (atendimentoSelecionado == null) {
            System.out.println("Nenhum atendimento encontrado com o código: " + codigo);
            return;
        }

        System.out.println("Dados atuais do atendimento:");
        System.out.println(atendimentoSelecionado.toStringDetalhado());

        System.out.println("Quais dados do atendimento você deseja alterar?");
        System.out.println("1 — Código");
        System.out.println("2 — Data");
        System.out.println("3 — Cliente");
        System.out.println("4 — Animal");
        System.out.println("5 — Funcionário");
        System.out.println("6 — Todos os dados");

        int opcao = ValidadorEntrada.lerInteiroValido(leia, 1, 6);
        System.out.println("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1 -> {
                System.out.println("Insira o novo código do atendimento: ");
                String novoCodigo = leia.nextLine();
                atendimentos.remove(atendimentoSelecionado.getCodigo());
                atendimentoSelecionado.setCodigo(novoCodigo);
                atendimentos.put(novoCodigo, atendimentoSelecionado);
            }
            case 2 -> {
                System.out.println("Insira a nova data do atendimento: ");
                String novaData = leia.nextLine();
                atendimentoSelecionado.setData(novaData);
            }
            case 3 -> {
                System.out.println("Insira o CPF do novo cliente do atendimento: ");
                String novoCpf = leia.nextLine();
                Cliente novoCliente = clientes.get(novoCpf);
                atendimentoSelecionado.setCliente(novoCliente);
            }
            case 4 -> {
                System.out.println("Insira o nome do novo animal do atendimento: ");
                String novoNome = leia.nextLine();
                String Cpf = atendimentoSelecionado.getCliente().getCpf();
                Animal novoAnimal = animais.get(novoNome + " — " + Cpf);
                atendimentoSelecionado.setAnimal(novoAnimal);
            }
            case 5 -> {
                System.out.println("Insira a matrícula do novo funcionário do atendimento: ");
                String novaMatricula = leia.nextLine();
                Funcionario novoFuncionario = funcionarios.get(novaMatricula);
                atendimentoSelecionado.setFuncionario(novoFuncionario);
            }
            case 6 -> {
                atendimentos.remove(atendimentoSelecionado.getCodigo());
                cadastrar(leia);
            }
        }
    }

     /**
     * Método para remover um atendimento do sistema.
     * Solicita ao usuário o código do atendimento e remove o atendimento com esse código.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void remover(Scanner leia) {
        System.out.println("Código do atendimento a ser removido: ");
        String codigo = leia.nextLine();

        Atendimento atendimentoRemovido = atendimentos.remove(codigo);

        if (atendimentoRemovido == null) {
            System.out.println("Nenhum atendimento encontrado com o código: " + codigo);
        } else {
            System.out.println("Atendimento removido com sucesso!");
        } 
    }

    @Override
    public void listar(Scanner leia) {
        // Método para listar todos os atendimentos cadastrados
        if (clientes.isEmpty()) {
            System.out.println("Nenhum atendimento cadastrado.");
        } else {
            System.out.println("\n=== RELATÓRIO DE ATENDIMENTOS ===");

            int contador = 1;
            for (Atendimento atendimento : atendimentos.values()) {
                System.out.println(contador + ". Atendimento: ");
                System.out.println(atendimento.toStringDetalhado());
                System.out.println("--------------------------------");
                contador++;
            }
        }
    }
} 

