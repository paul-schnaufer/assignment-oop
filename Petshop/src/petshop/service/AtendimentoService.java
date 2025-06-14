package petshop.service;

import java.util.Map;
import java.util.Scanner;
import petshop.modelo.Atendimento;
import petshop.util.ValidadorEntrada;
import petshop.util.BancoDeDadosEmMemoria;

/**
 * Classe responsável por gerenciar as operações relacionadas aos clientes do petshop.
 */
public class AtendimentoService implements Service {
    private Map<String, Atendimento> atendimentos;

    public AtendimentoService(Map<String, Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    /**
     * Método para cadastrar um atendimento no sistema.
     * Solicita ao usuário as informações necessárias e cria um novo objeto Cliente.
     * A chave do cliente é seu CPF.
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
        System.out.println("Insira o telefone do animal: ");
        Animal animal = leia.nextLine();        
        System.out.println("Insira o CPF do cliente: ");
        Funcionario funcionario = leia.nextLine();

        String chave = cpf;
        atendimento.put(chave, new Atendimento(codigo, data, cliente, animal, funcionario));

        System.out.println("Dados do cliente cadastrado:");
        System.out.println(clientes.get(chave).toStringDetalhado());

        System.out.println("Os dados estão corretos? (S/N)");
        String resposta = leia.nextLine().trim().toUpperCase();

        if (!resposta.equals("S")) {
            System.out.println("Cadastro cancelado.");
            clientes.remove(chave);
            return;
        }

        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Gostaria de cadastrar outro cliente? (S/N)");
        resposta = leia.nextLine().trim().toUpperCase();

        if (resposta.equals("S")) {
            cadastrar(leia);
        } else {
            System.out.println("Cadastro finalizado.");
        }
    }

    /**
     * Método para consultar um cliente no sistema.
     * Solicita ao usuário o CPF do cliente e busca pelo cliente com esse CPF.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void consultar(Scanner leia) {
        System.out.println("CPF do cliente a ser consultado: ");
        String cpf = leia.nextLine();

        Cliente clienteSelecionado = clientes.get(cpf);

        if (clienteSelecionado != null) {
            System.out.println("Dados do cliente selecionado:");
            System.out.println(clienteSelecionado.toStringDetalhado());
        } else {
            System.out.println("Nenhum cliente encontrado com o CPF: " + cpf);
        } 
    }

    /**
     * Método para alterar os dados de um cliente no sistema.
     * Solicita ao usuário o CPF do cliente e permite que ele escolha quais dados deseja alterar.
     * As opções incluem nome, telefone, email, RG, CPF, ou todos os dados.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void alterar(Scanner leia) {
        System.out.println("CPF do cliente a ser alterado: ");
        String cpf = leia.nextLine();

        Cliente clienteSelecionado = clientes.get(cpf);

        if (clienteSelecionado == null) {
            System.out.println("Nenhum cliente encontrado com o CPF: " + cpf);
            return;
        }

        System.out.println("Dados atuais do cliente:");
        System.out.println(clienteSelecionado.toStringDetalhado());

        System.out.println("Quais dados do cliente você deseja alterar?");
        System.out.println("1 — Nome");
        System.out.println("2 — Telefone");
        System.out.println("3 — E-mail");
        System.out.println("4 — RG");
        System.out.println("5 — CPF");
        System.out.println("6 — Todos os dados");

        int opcao = ValidadorEntrada.lerInteiroValido(leia, 1, 6);
        System.out.println("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1 -> {
                System.out.println("Insira o novo nome do cliente: ");
                String novoNome = leia.nextLine();
                clienteSelecionado.setNome(novoNome);
            }
            case 2 -> {
                System.out.println("Insira o novo telefone do cliente: ");
                String novoTelefone = leia.nextLine();
                clienteSelecionado.setTelefone(novoTelefone);
            }
            case 3 -> {
                System.out.println("Insira o novo e-mail do cliente: ");
                String novoEmail = leia.nextLine();
                clienteSelecionado.setEmail(novoEmail);
            }
            case 4 -> {
                System.out.println("Insira o novo RG do cliente: ");
                String novoRg = leia.nextLine();
                clienteSelecionado.setRg(novoRg);
            }
            case 5 -> {
                System.out.println("Insira o novo CPF do cliente: ");
                String novoCpf = leia.nextLine();
                clienteSelecionado.setCpf(novoCpf);
                clientes.remove(clienteSelecionado.getCpf());
                clientes.put(novoCpf, clienteSelecionado);
            }
            case 6 -> {
                clientes.remove(clienteSelecionado.getCpf());
                cadastrar(leia);
            }
        }
    }

     /**
     * Método para remover um cliente do sistema.
     * Solicita ao usuário o CPF do cliente e remove o cliente com esse CPF.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void remover(Scanner leia) {
        System.out.println("CPF do cliente a ser removido: ");
        String cpf = leia.nextLine();

        Cliente clienteRemovido = clientes.remove(cpf);

        if (clienteRemovido == null) {
            System.out.println("Nenhum cliente encontrado com o CPF: " + cpf);
        } else {
            System.out.println("Cliente removido com sucesso!");
        } 
    }

    @Override
    public void listar(Scanner leia) {
        // Método para listar todos os animais cadastrados
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\n=== RELATÓRIO DE CLIENTES ===");

            int contador = 1;
            for (Cliente cliente : clientes.values()) {
                System.out.println(contador + ". Cliente: ");
                System.out.println(cliente.toStringDetalhado());
                System.out.println("--------------------------------");
                contador++;
            }
        }
    }
} 

