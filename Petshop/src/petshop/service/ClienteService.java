package petshop.service;

import java.util.List;

import petshop.model.Cliente;
import petshop.ui.ClienteConsoleUI;
import petshop.repository.ClienteRepository;

/**
 * Classe ClienteService que implementa a interface Service.
 * Esta classe é responsável por gerenciar as operações relacionadas a clientes,
 * como cadastro, consulta, alteração, remoção e listagem de clientes.
 */
public class ClienteService implements Service {
    private ClienteConsoleUI ui;
    private ClienteRepository clienteRepository;

    /**
     * Construtor da classe ClienteService.
     * Inicializa a interface de usuário e o repositório de clientes.
     *
     * @param ui A interface de usuário para interações com o usuário
     * @param clienteRepository O repositório de clientes para armazenar e recuperar dados
     */
    public ClienteService(ClienteConsoleUI ui, ClienteRepository clienteRepository) {
        this.ui = ui;
        this.clienteRepository = clienteRepository;
    }

    /**
     * Método para cadastrar um novo cliente no sistema.
     * Solicita ao usuário os dados do cliente e os armazena no repositório.
     * Se o CPF já estiver cadastrado, exibe uma mensagem de erro.
     */
    @Override
    public void cadastrar() {
        String cpfCliente = ui.solicitarCpfCliente();

        String chave = cpfCliente;

        if (clienteRepository.exists(chave)) {
            ui.mostrarMensagem("Já existe um cliente cadastrado com o CPF: " + cpfCliente);
            return;
        }

        String nomeCliente = ui.solicitarNomeCliente();
        String telefoneCliente = ui.solicitarTelefoneCliente();
        String emailCliente = ui.solicitarEmailCliente();
        String rgCliente = ui.solicitarRgCliente();

        Cliente novoCliente = new Cliente(
            nomeCliente, telefoneCliente, emailCliente, rgCliente, cpfCliente);
        clienteRepository.save(chave, novoCliente);

        ui.mostrarMensagem("Dados do cliente cadastrado:");
        ui.mostrarDetalhesCliente(novoCliente);

        if (!ui.receberConfirmacao("Os dados estão corretos?")) {
            ui.mostrarMensagem("Cadastro cancelado.");
            clienteRepository.delete(chave);
            return;
        }

        ui.mostrarMensagem("Cliente cadastrado com sucesso.");
    
        if (ui.receberConfirmacao("Gostaria de cadastrar outro cliente?")) {
            cadastrar();
        } else {
            ui.mostrarMensagem("Cadastro finalizado.");
        }
    }

    /**
     * Método para consultar os dados de um cliente no sistema.
     * Solicita ao usuário o CPF do cliente e exibe os detalhes do cliente correspondente.
     * Se o cliente não for encontrado, exibe uma mensagem informando isso.
     */
    @Override
    public void consultar() {
        String cpfCliente = ui.solicitarCpfCliente();

        Cliente clienteSelecionado = clienteRepository.getByKey(cpfCliente);

        if (clienteSelecionado != null) {
            ui.mostrarMensagem("Dados do cliente selecionado:");
            ui.mostrarDetalhesCliente(clienteSelecionado);
        } else {
            ui.mostrarMensagem("Nenhum cliente encontrado com o CPF: " + cpfCliente);
        } 
    }

    /**
     * Método para alterar os dados de um cliente no sistema.
     * Solicita ao usuário o CPF do cliente e permite a alteração de seus dados.
     * Exibe um menu com opções de alteração e atualiza os dados conforme a escolha do usuário.
     */
    @Override
    public void alterar() {
        String cpfCliente = ui.solicitarCpfCliente();

        Cliente clienteSelecionado = clienteRepository.getByKey(cpfCliente);

        if (clienteSelecionado == null) {
            ui.mostrarMensagem("Nenhum cliente encontrado com o CPF: " + cpfCliente);
            return;
        }

        ui.mostrarMensagem("Dados atuais do cliente:");
        ui.mostrarDetalhesCliente(clienteSelecionado);

        // Exibe o menu de opções de alteração
        ui.menuAlterarCliente();
        int opcao = ui.capturarInteiro(1, 6);
        ui.mostrarMensagem("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1 -> {
                String novoNome = ui.solicitarNomeCliente()
                clienteSelecionado.setNome(novoNome);
            }
            case 2 -> {
                String novoTelefone = ui.solicitarTelefoneCliente();
                clienteSelecionado.setTelefone(novoTelefone);
            }
            case 3 -> {
               String novoEmail = ui.solicitarEmailCliente();
                clienteSelecionado.setEmail(novoEmail);
            }
            case 4 -> {
                String novoRg = ui.solicitarRgCliente();
                clienteSelecionado.setRg(novoRg);
            }
            case 5 -> {
                String novoCpf = ui.solicitarCpfCliente();
                String cpfAntigo = clienteSelecionado.getCpf();

                if (clienteRepository.exists(novoCpf)) {
                    ui.mostrarMensagem("Já existe um cliente cadastrado com o CPF: " + novoCpf);
                    return;
                }

                clienteRepository.delete(cpfAntigo);
                clienteSelecionado.setCpf(novoCpf);
                clienteRepository.save(novoCpf, clienteSelecionado);
            }
            case 6 -> {
                String cpfAntigo = clienteSelecionado.getCpf();

                ui.mostrarMensagem("Você escolheu alterar todos os dados do cliente.");
                clienteRepository.delete(cpfAntigo);
                ui.mostrarMensagem("Por favor, insira os novos dados do cliente:");


                String nomeAlterado = ui.solicitarNomeCliente();
                String telefoneAlterado = ui.solicitarTelefoneCliente();
                String emailAlterado = ui.solicitarEmailCliente();
                String rgAlterado = ui.solicitarRgCliente();
                String cpfAlterado = ui.solicitarCpfCliente();

                if (clienteRepository.exists(cpfAlterado)) {
                    ui.mostrarMensagem(
                        "Já existe um cliente cadastrado com o CPF: " + cpfAlterado);
                    return;
                }

                Cliente clienteAlterado = new Cliente(
                    nomeAlterado, telefoneAlterado, emailAlterado, rgAlterado, cpfAlterado);
                clienteRepository.save(cpfAlterado, clienteAlterado);
                ui.mostrarMensagem("Dados do cliente alterados com sucesso.");
                return;
            }
        }
    }

    /**
     * Método para remover um cliente do sistema.
     * Solicita ao usuário o CPF do cliente e, se encontrado, remove o cliente após confirmação.
     * Se o cliente não for encontrado, exibe uma mensagem informando isso.
     */
    @Override
    public void remover() {
        String cpf = ui.solicitarCpfCliente();

        boolean clienteExiste = clienteRepository.exists(cpf);

       if (!clienteExiste) {
            ui.mostrarMensagem("Nenhum cliente encontrado com o CPF: " + cpf);
            return;
        }

        boolean confirmacao = ui.receberConfirmacao("Você tem certeza que deseja remover o cliente com CPF: " + cpf + "?");

        if (confirmacao) {
            clienteRepository.delete(cpf);
            ui.mostrarMensagem("Cliente removido com sucesso.");
        } else {
            ui.mostrarMensagem("Remoção cancelada.");
        }
    }

    /**
     * Método para listar todos os clientes cadastrados no sistema.
     * Exibe um relatório com o total de clientes e os detalhes de cada cliente.
     * Se não houver clientes cadastrados, exibe uma mensagem informando isso.
     */
    @Override
    public void listar() {
        // Método para listar todos os animais cadastrados
        if (clienteRepository.isEmpty()) {
            ui.mostrarMensagem("Nenhum cliente cadastrado.");
            return;
        } else {
            ui.mostrarCabecalho("Relatório de Clientes");
            ui.mostrarMensagem("Total de clientes cadastrados: " + clienteRepository.size());
            int contador = 1;

            for (Cliente cliente : clienteRepository.getAll()) {
                ui.mostrarMensagem("Cliente " + contador + ":");
                ui.mostrarDetalhesCliente(cliente);
                contador++;
            }
        }
    }
}