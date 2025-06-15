package petshop.service;

import petshop.model.Funcionario;
import petshop.ui.FuncionarioConsoleUI;
import petshop.repository.FuncionarioRepository;

/**
 * Classe responsável por gerenciar as operações relacionadas aos funcionários do petshop.
 * Esta classe implementa a interface Service e fornece métodos para cadastrar, consultar,
 * alterar, remover e listar funcionários.
 */
public class FuncionarioService implements Service {
    private FuncionarioConsoleUI ui;
    private FuncionarioRepository funcionarioRepository;

    /**
     * Construtor da classe FuncionarioService.
     * Inicializa a interface de usuário e o repositório de funcionários.
     *
     * @param ui A interface de usuário para interações com o usuário
     * @param funcionarioRepository O repositório de funcionários para armazenar e recuperar dados
     */
    public FuncionarioService(
        FuncionarioConsoleUI ui, FuncionarioRepository funcionarioRepository
        ) {
        this.ui = ui;
        this.funcionarioRepository = funcionarioRepository;
    }

    /**
     * Método para cadastrar um novo funcionário no sistema.
     * Solicita ao usuário os dados do funcionário e os armazena no repositório.
     * Se a matrícula já estiver cadastrada, exibe uma mensagem de erro.
     */
    @Override
    public void cadastrar() {
        String matriculaFuncionario = ui.solicitarMatriculaFuncionario();

        String chave = matriculaFuncionario;

        if (funcionarioRepository.exists(chave)) {
            ui.mostrarMensagem(
                "Já existe um funcionário cadastrado com a matrícula: " + matriculaFuncionario
                );
            return;
        }

        String nomeFuncionario = ui.solicitarNomeFuncionario();
        String qualificacaoFuncionario = ui.solicitarQualificacaoFuncionario();
        String descricaoFuncaoFuncionario = ui.solicitarDescricaoFuncaoFuncionario();
        float cargaHorariaFuncionario = ui.solicitarCargaHorariaFuncionario();

        Funcionario novoFuncionario = new Funcionario(
            nomeFuncionario,
            matriculaFuncionario,
            qualificacaoFuncionario,
            descricaoFuncaoFuncionario,
            cargaHorariaFuncionario
            );

        ui.mostrarMensagem("Dados do funcionário cadastrado:");
        ui.mostrarDetalhesFuncionario(novoFuncionario);

        if (!ui.receberConfirmacao("Os dados estão corretos?")) {
            ui.mostrarMensagem("Cadastro cancelado.");
            funcionarioRepository.delete(chave);
            return;
        } else {
            funcionarioRepository.save(chave, novoFuncionario);
        }

        ui.mostrarMensagem("Funcionário cadastrado com sucesso.");

        if (ui.receberConfirmacao("Gostaria de cadastrar outro funcionário?")) {
            cadastrar();
        } else {
            ui.mostrarMensagem("Cadastro finalizado.");
        }
    }

    /**
     * Método para consultar um funcionário pelo número de matrícula.
     * Solicita ao usuário a matrícula do funcionário e
     * exibe os detalhes do funcionário correspondente.
     * Se o funcionário não for encontrado,
     * exibe uma mensagem informando que não há funcionários com essa matrícula.
     */
    @Override
    public void consultar() {
        String matricula = ui.solicitarMatriculaFuncionario();

        Funcionario funcionarioSelecionado = funcionarioRepository.getByKey(matricula);

        if (funcionarioSelecionado != null) {
            ui.mostrarMensagem("Dados do funcionário selecionado:");
            ui.mostrarDetalhesFuncionario(funcionarioSelecionado);
        } else {
            ui.mostrarMensagem("Nenhum funcionario encontrado com a matrícula: " + matricula);
        } 
    }

    /**
     * Método para consultar os dados de um funcionário no sistema.
     * Solicita ao usuário a matrícula do funcionário e
     * exibe os detalhes do funcionário correspondente.
     * Se o funcionário não for encontrado, exibe uma mensagem informando isso.
     */
    @Override
    public void alterar() {
        String matricula = ui.solicitarMatriculaFuncionario();

        Funcionario funcionarioSelecionado = funcionarioRepository.getByKey(matricula);

        if (funcionarioSelecionado == null) {
            ui.mostrarMensagem("Nenhum funcionario encontrado com a matrícula: " + matricula);
            return;
        }

        ui.mostrarMensagem("Dados atuais do funcionario:");
        ui.mostrarDetalhesFuncionario(funcionarioSelecionado);

        ui.menuAlterarFuncionario();
        int opcao = ui.capturarInteiro(1, 6);
        ui.mostrarMensagem("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1 -> {
                String novoNome = ui.solicitarNomeFuncionario();
                funcionarioSelecionado.setNome(novoNome);
                ui.mostrarMensagem("Nome do funcionário alterado com sucesso.");
            }
            case 2 -> {
                String antigaMatricula = funcionarioSelecionado.getMatricula();
                String novaMatricula = ui.solicitarMatriculaFuncionario();

                funcionarioRepository.delete(antigaMatricula);

                if (funcionarioRepository.exists(novaMatricula)) {
                    ui.mostrarMensagem(
                        "Já existe um funcionário cadastrado com a matrícula: " + novaMatricula
                        );
                    return;
                }

                funcionarioSelecionado.setMatricula(novaMatricula);              
                funcionarioRepository.save(novaMatricula, funcionarioSelecionado);
                ui.mostrarMensagem("Matrícula do funcionário alterada com sucesso.");
            }
            case 3 -> {
                String novaQualificacao = ui.solicitarQualificacaoFuncionario();
                funcionarioSelecionado.setQualificacao(novaQualificacao);
                ui.mostrarMensagem("Qualificação do funcionário alterada com sucesso.");
            }
            case 4 -> {
                String novaDescricaoFuncao = ui.solicitarDescricaoFuncaoFuncionario();
                funcionarioSelecionado.setDescricaoFuncao(novaDescricaoFuncao);
                ui.mostrarMensagem("Descrição da função do funcionário alterada com sucesso.");
            }
            case 5 -> {
                float novaCargaHoraria = ui.solicitarCargaHorariaFuncionario();
                funcionarioSelecionado.setCargaHoraria(novaCargaHoraria);
                ui.mostrarMensagem("Carga horária do funcionário alterada com sucesso.");
            }
            case 6 -> {
                String antigaMatricula = funcionarioSelecionado.getMatricula();
                
                ui.mostrarMensagem("Você escolheu alterar todos os dados do funcionário.");
                funcionarioRepository.delete(antigaMatricula);
                ui.mostrarMensagem("Por favor, insira os novos dados do funcionário:");

                String nomeAlterado = ui.solicitarNomeFuncionario();
                String matriculaAlterada = ui.solicitarMatriculaFuncionario();
                String qualificacaoAlterada = ui.solicitarQualificacaoFuncionario();
                String descricaoFuncaoAlterada = ui.solicitarDescricaoFuncaoFuncionario();
                float cargaHorariaAlterada = ui.solicitarCargaHorariaFuncionario();

                if (funcionarioRepository.exists(matriculaAlterada)) {
                    ui.mostrarMensagem(
                        "Já existe um funcionário cadastrado com a matrícula: " + matriculaAlterada
                        );
                    return;
                }

                Funcionario funcionarioAlterado = new Funcionario(
                    nomeAlterado,
                    matriculaAlterada,
                    qualificacaoAlterada,
                    descricaoFuncaoAlterada,
                    cargaHorariaAlterada
                    );
                funcionarioRepository.save(matriculaAlterada, funcionarioAlterado);
                ui.mostrarMensagem("Dados do funcionário alterados com sucesso.");
                return;
            }
        }
    }

    /**
     * Método para remover um funcionário do sistema.
     * Solicita ao usuário a matrícula do funcionário a ser removido e confirma a remoção.
     * Se o funcionário não for encontrado, exibe uma mensagem informando isso.
     */
    @Override
    public void remover() {
        String matricula = ui.solicitarMatriculaFuncionario();

        boolean funcionarioExiste = funcionarioRepository.exists(matricula);

        if (!funcionarioExiste) {
            ui.mostrarMensagem("Nenhum funcionário encontrado com a matrícula: " + matricula);
            return;
        }

        boolean confirmacao = ui.receberConfirmacao(
            "Tem certeza que deseja remover o funcionário com matrícula: " + matricula + "?"
            );

        if (confirmacao) {
            funcionarioRepository.delete(matricula);
            ui.mostrarMensagem("Funcionário removido com sucesso.");
        } else {
            ui.mostrarMensagem("Remoção cancelada.");
        }
    }

    /**
     * Método para listar todos os funcionários cadastrados no sistema.
     * Exibe uma mensagem informando o total de funcionários e os detalhes de cada um.
     * Se não houver funcionários cadastrados, exibe uma mensagem informando isso.
     */
    @Override
    public void listar() {
        // Método para listar todos os animais cadastrados
        if (funcionarioRepository.isEmpty()) {
            ui.mostrarMensagem("Nenhum funcionário cadastrado.");
            return;
        } else {
            ui.mostrarCabecalho("Relatório de Funcionários");
            ui.mostrarMensagem(
                "Total de funcionários cadastrados: " + funcionarioRepository.size()
                );
            int contador = 1;

            for (Funcionario funcionario : funcionarioRepository.getAll()) {
                ui.mostrarMensagem("Funcionário " + contador + ":");
                ui.mostrarDetalhesFuncionario(funcionario);
                ui.mostrarMensagem("-----------------------------------------------------");
                contador++;
            }
        }
    }
} 