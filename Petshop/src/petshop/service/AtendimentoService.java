package petshop.service;

import petshop.model.Animal;
import petshop.model.Cliente;
import petshop.model.Funcionario;

import petshop.model.Atendimento;
import petshop.ui.AtendimentoConsoleUI;

import petshop.repository.AnimalRepository;
import petshop.repository.AtendimentoRepository;
import petshop.repository.ClienteRepository;
import petshop.repository.FuncionarioRepository;


/**
 * Classe responsável por gerenciar as operações relacionadas aos atendimentos do petshop.
 * Implementa a interface Service para definir os métodos de cadastro, consulta, alteração,
 * remoção e listagem de atendimentos.
 */
public class AtendimentoService implements Service {
    private AtendimentoConsoleUI ui;
    private AnimalRepository animalRepository;
    private AtendimentoRepository atendimentoRepository;
    private ClienteRepository clienteRepository;
    private FuncionarioRepository funcionarioRepository;

    /**
     * Construtor da classe AtendimentoService.
     * Inicializa os repositórios e a interface de usuário.
     *
     * @param ui Interface de usuário para interações com o console
     * @param animalRepository Repositório de animais
     * @param atendimentoRepository Repositório de atendimentos
     * @param clienteRepository Repositório de clientes
     * @param funcionarioRepository Repositório de funcionários
     */
    public AtendimentoService(
            AtendimentoConsoleUI ui,
            AnimalRepository animalRepository,
            AtendimentoRepository atendimentoRepository,
            ClienteRepository clienteRepository,
            FuncionarioRepository funcionarioRepository
            ) {
        this.ui = ui;
        this.animalRepository = animalRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    /**
     * Método para cadastrar um novo atendimento no sistema.
     * Solicita ao usuário os dados necessários e salva o atendimento no repositório.
     */
    @Override
    public void cadastrar() {
        String codigo = ui.solicitarCodigoAtendimento();
        String chave = codigo;

        if (atendimentoRepository.exists(chave)) {
            ui.mostrarMensagem("Já existe um atendimento cadastrado com o código: " + codigo);
            return;
        }

        String data = ui.solicitarDataAtendimento();
        String chaveCliente = ui.solicitarChaveClienteAtendimento();
        Cliente cliente = clienteRepository.getByKey(chaveCliente);

        if (cliente == null) {
            ui.mostrarMensagem("Nenhum cliente encontrado com o CPF: " + chaveCliente);
            return;
        }

        String chaveAnimal = ui.solicitarChaveAnimalAtendimento();
        Animal animal = animalRepository.getByKey(chaveAnimal);

        if (animal == null) {
            ui.mostrarMensagem("Nenhum animal encontrado com a chave: " + chaveAnimal);
            return;
        }

        String chaveFuncionario = ui.solicitarChaveFuncionarioAtendimento();
        Funcionario funcionario = funcionarioRepository.getByKey(chaveFuncionario);

        if (funcionario == null) {
            ui.mostrarMensagem(
                "Nenhum funcionário encontrado com a matrícula: " + chaveFuncionario
                );
            return;
        }

        Atendimento novoAtendimento = new Atendimento(codigo, data, cliente, animal, funcionario);
        atendimentoRepository.save(chave, novoAtendimento);

        ui.mostrarMensagem("Dados do atendimento cadastrado:");
        ui.mostrarDetalhesAtendimento(novoAtendimento);

        if (!ui.receberConfirmacao("Os dados estão corretos?")) {
            ui.mostrarMensagem("Cadastro cancelado.");
            atendimentoRepository.delete(chave);
            return;
        }

        ui.mostrarMensagem("Atendimento cadastrado com sucesso.");

        if (ui.receberConfirmacao("Gostaria de cadastrar outro atendimento?")) {
            cadastrar();
        } else {
            ui.mostrarMensagem("Cadastro finalizado.");
        }
    }

    /**
     * Método para consultar um atendimento no sistema.
     * Solicita ao usuário o código do atendimento e
     * exibe os detalhes do atendimento correspondente.
     */
    @Override
    public void consultar() {
        String codigo = ui.solicitarCodigoAtendimento();

        Atendimento atendimentoSelecionado = atendimentoRepository.getByKey(codigo);

        if (atendimentoSelecionado != null) {
            ui.mostrarMensagem("Dados do atendimento selecionado:");
            ui.mostrarDetalhesAtendimento(atendimentoSelecionado);
        } else {
            ui.mostrarMensagem("Nenhum atendimento encontrado com o código: " + codigo);
        } 
    }

    /**
     * Método para alterar os dados de um atendimento existente.
     * Solicita ao usuário o código do atendimento e permite a alteração de seus dados.
     */
    @Override
    public void alterar() {
        String codigo = ui.solicitarCodigoAtendimento();

        Atendimento atendimentoSelecionado = atendimentoRepository.getByKey(codigo);

        if (atendimentoSelecionado == null) {
            ui.mostrarMensagem("Nenhum atendimento encontrado com o código: " + codigo);
            return;
        }

        ui.mostrarMensagem("Dados atuais do atendimento:");
        ui.mostrarDetalhesAtendimento(atendimentoSelecionado);

        ui.menuAlterarAtendimento();
        int opcao = ui.capturarInteiro(1, 6);
        ui.mostrarMensagem("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1 -> {
                String novoCodigo = ui.solicitarCodigoAtendimento();
                String antigoCodigo = atendimentoSelecionado.getCodigo();

                if (atendimentoRepository.exists(novoCodigo)) {
                    ui.mostrarMensagem(
                        "Já existe um atendimento cadastrado com o código: " + novoCodigo);
                    return;
                }

                atendimentoRepository.delete(antigoCodigo);
                atendimentoSelecionado.setCodigo(novoCodigo);
                atendimentoRepository.save(novoCodigo, atendimentoSelecionado);
            }
            case 2 -> {
                String novaData = ui.solicitarDataAtendimento();
                atendimentoSelecionado.setData(novaData);
            }
            case 3 -> {
                String novaChaveCliente = ui.solicitarChaveClienteAtendimento();

                if (!clienteRepository.exists(novaChaveCliente)) {
                    ui.mostrarMensagem("Nenhum cliente encontrado com o CPF: " + novaChaveCliente);
                    return;
                }

                Cliente novoCliente = clienteRepository.getByKey(novaChaveCliente);
                atendimentoSelecionado.setCliente(novoCliente);
            }
            case 4 -> {
                String novaChaveAnimal = ui.solicitarChaveAnimalAtendimento();

                if (!animalRepository.exists(novaChaveAnimal)) {
                    ui.mostrarMensagem("Nenhum animal encontrado com o nome: " + novaChaveAnimal);
                    return;
                }

                Animal novoAnimal = animalRepository.getByKey(novaChaveAnimal);
                atendimentoSelecionado.setAnimal(novoAnimal);
            }
            case 5 -> {
                String novaChaveFuncionario = ui.solicitarChaveFuncionarioAtendimento();

                if (!funcionarioRepository.exists(novaChaveFuncionario)) {
                    ui.mostrarMensagem(
                        "Nenhum funcionário encontrado com a matrícula: " + novaChaveFuncionario);
                    return;
                }

                Funcionario novoFuncionario = funcionarioRepository.getByKey(novaChaveFuncionario);
                atendimentoSelecionado.setFuncionario(novoFuncionario);
            }
            case 6 -> {
                String antigoCodigo = atendimentoSelecionado.getCodigo();

                ui.mostrarMensagem("Você escolheu alterar todos os dados do atendimento.");
                atendimentoRepository.delete(antigoCodigo);
                ui.mostrarMensagem("Por favor, insira os novos dados do atendimento:");

                String novoCodigo = ui.solicitarCodigoAtendimento();

                if (atendimentoRepository.exists(novoCodigo)) {
                    ui.mostrarMensagem(
                        "Já existe um atendimento cadastrado com o código: " + novoCodigo
                        );
                    return;
                }

                String novaData = ui.solicitarDataAtendimento();
                String novaChaveCliente = ui.solicitarChaveClienteAtendimento();
                Cliente novoCliente = clienteRepository.getByKey(novaChaveCliente);
                
                if (novoCliente == null) {
                    ui.mostrarMensagem("Nenhum cliente encontrado com o CPF: " + novaChaveCliente);
                    return;
                }

                String novaChaveAnimal = ui.solicitarChaveAnimalAtendimento();
                Animal novoAnimal = animalRepository.getByKey(novaChaveAnimal);

                if (novoAnimal == null) {
                    ui.mostrarMensagem("Nenhum animal encontrado com o nome: " + novaChaveAnimal);
                    return;
                }

                String novaChaveFuncionario = ui.solicitarChaveFuncionarioAtendimento();
                Funcionario novoFuncionario = funcionarioRepository.getByKey(novaChaveFuncionario);

                if (novoFuncionario == null) {
                    ui.mostrarMensagem(
                        "Nenhum funcionário encontrado com a matrícula: " + novaChaveFuncionario
                        );
                    return;
                }

                Atendimento novoAtendimento = new Atendimento(
                        novoCodigo, novaData, novoCliente, novoAnimal, novoFuncionario
                        );
                atendimentoRepository.save(novoCodigo, novoAtendimento);
                ui.mostrarMensagem("Dados do atendimento alterados com sucesso.");
                return;
            }
        }
    }

    /**
     * Método para remover um atendimento do sistema.
     * Solicita ao usuário o código do atendimento e remove o atendimento correspondente.
     */
    @Override
    public void remover() {
        String codigo = ui.solicitarCodigoAtendimento();

        boolean atendimentoExiste = atendimentoRepository.exists(codigo);

        if (!atendimentoExiste) {
            ui.mostrarMensagem("Nenhum atendimento encontrado com o código: " + codigo);
            return;
        }

        boolean confirmacao = ui.receberConfirmacao(
            "Tem certeza que deseja remover o atendimento com código: " + codigo + "?"
            );
        
        if (confirmacao) {
            atendimentoRepository.delete(codigo);
            ui.mostrarMensagem("Atendimento removido com sucesso.");
        } else {
            ui.mostrarMensagem("Remoção cancelada.");
        }
    }

    /**
     * Método para listar todos os atendimentos cadastrados no sistema.
     * Exibe um relatório com os detalhes de cada atendimento.
     */
    @Override
    public void listar() {
        // Método para listar todos os atendimentos cadastrados
        if (atendimentoRepository.isEmpty()) {
            ui.mostrarMensagem("Nenhum atendimento cadastrado.");
            return;
        } else {
            ui.mostrarCabecalho("Relatório de Atendimentos");
            ui.mostrarMensagem(
                "Total de atendimentos cadastrados: " + atendimentoRepository.size()
                );
            int contador = 1;

            for (Atendimento atendimento : atendimentoRepository.getAll()) {
                ui.mostrarMensagem("Atendimento " + contador + ":");
                ui.mostrarDetalhesAtendimento(atendimento);
                ui.mostrarMensagem("-----------------------------------------------------");
                contador++;
            }
        }
    }
}