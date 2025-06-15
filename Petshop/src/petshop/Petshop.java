package petshop;

import java.util.Scanner;

import petshop.repository.AnimalRepository;
import petshop.repository.AtendimentoRepository;
import petshop.repository.BancoDeDadosEmMemoria;
import petshop.repository.ClienteRepository;
import petshop.repository.FuncionarioRepository;

import petshop.service.AnimalService;
import petshop.service.AtendimentoService;
import petshop.service.ClienteService;
import petshop.service.FuncionarioService;

import petshop.ui.AnimalConsoleUI;
import petshop.ui.AtendimentoConsoleUI;
import petshop.ui.ClienteConsoleUI;
import petshop.ui.FuncionarioConsoleUI;

/**
 * Classe principal do sistema de gerenciamento de petshop.
 * Permite a interação com o usuário para realizar diversas operações.
 */
public class Petshop {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        // Inicialização dos repositórios com os dados em memória
        AnimalRepository animalRepository = new AnimalRepository();
        AtendimentoRepository atendimentoRepository = new AtendimentoRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

        // Inicialização das UIs correspondentes
        AnimalConsoleUI animalUI = new AnimalConsoleUI(leia);
        AtendimentoConsoleUI atendimentoUI = new AtendimentoConsoleUI(leia);
        ClienteConsoleUI clienteUI = new ClienteConsoleUI(leia);
        FuncionarioConsoleUI funcionarioUI = new FuncionarioConsoleUI(leia);

        // Inicialização dos serviços com as UIs e Repositories correspondentes
        AnimalService animalService = new AnimalService(animalUI, animalRepository);
        AtendimentoService atendimentoService = new AtendimentoService(
                                        atendimentoUI,
                                        atendimentoRepository,
                                        clienteRepository,
                                        animalRepository,
                                        funcionarioRepository);
        ClienteService clienteService = new ClienteService(clienteUI, clienteRepository);
        FuncionarioService funcionarioService = new FuncionarioService(
            funcionarioUI, funcionarioRepository);

        /*
         * TODO:
         * Implementar as classes de UI (Console) para cada entidade, conforme o modelo 
         * definido: 
         * AnimalConsoleUI.java.
         * 
         * TODO:
         * Implementar as classes de Service para cada entidade, conforme o modelo
         * definido:
         * AniimalService.java.
         */
    
        // Loop de menus chamando os serviços normalmente
        int opcao = 0;

        while(opcao != 5){
            System.out.println("\nSelecione a opção + desejada:\n" +
                               "1 - Cliente\n" +
                               "2 - Funcionário\n" +
                               "3 - Animal\n" +
                               "4 - Atendimento\n" +
                               "5 - Encerrar Execução");
            opcao = petshop.util.ValidadorEntrada.lerInteiroValido(leia, 1, 5);

            int acao;
            switch (opcao) {
                case 1 -> {
                    clienteUI.menuPrincipal();
                    acao = petshop.util.ValidadorEntrada.lerInteiroValido(leia, 1, 6);
                    switch (acao) {
                        case 1 -> { clienteService.cadastrar(); }
                        case 2 -> { clienteService.consultar(); }
                        case 3 -> { clienteService.alterar(); }
                        case 4 -> { clienteService.remover(); }
                        case 5 -> { clienteService.listar(); }
                        case 6 -> {}
                    }
                }
                case 2 -> {
                    funcionarioUI.menuPrincipal();
                    acao = petshop.util.ValidadorEntrada.lerInteiroValido(leia, 1, 6);
                    switch (acao) {
                        case 1 -> { funcionarioService.cadastrar(); }
                        case 2 -> { funcionarioService.consultar(); }
                        case 3 -> { funcionarioService.alterar(); }
                        case 4 -> { funcionarioService.remover(); }
                        case 5 -> { funcionarioService.listar(); }
                        case 6 -> {}
                    }
                }
                case 3 -> {
                    animalUI.menuPrincipal();
                    acao = petshop.util.ValidadorEntrada.lerInteiroValido(leia, 1, 6);
                    switch (acao) {
                        case 1 -> { animalService.cadastrar(); }
                        case 2 -> { animalService.consultar(); }
                        case 3 -> { animalService.alterar(); }
                        case 4 -> { animalService.remover(); }
                        case 5 -> { animalService.listar(); }
                        case 6 -> {}
                    }
                }
                case 4 -> {
                    atendimentoUI.menuPrincipal();
                    acao = petshop.util.ValidadorEntrada.lerInteiroValido(leia, 1, 6);
                    switch (acao) {
                        case 1 -> { atendimentoService.cadastrar(); }
                        case 2 -> { atendimentoService.consultar(); }
                        case 3 -> { atendimentoService.alterar(); }
                        case 4 -> { atendimentoService.remover(); }
                        case 5 -> { atendimentoService.listar(); }
                        case 6 -> {}
                    }
                }
                case 5 -> {
                    System.out.println("Encerrando o sistema...");
                    leia.close();
                    return; // Encerra o loop e o programa
                }
            }
        }     
        System.out.println("\nEncerrando Execução...");
    }
}

// TODO:
// Para cada Entidade (Cliente, Funcionario, Atendimento):
// Model (Cliente.java, Funcionario.java, Atendimento.java):
// Mantenha-os como POJOs simples com atributos, construtores, getters, setters e um método
// toStringDetalhado().
// Documente com Javadoc.

// Repository
// No construtor, atribua o mapa estático correspondente de BancoDeDadosEmMemoria
// (ex: this.clientes = BancoDeDadosEmMemoria.clientes;).
// Implemente métodos CRUD básicos (save, getByKey, delete, getAll, exists, isEmpty, size) e
// quaisquer métodos de busca específicos necessários
// (como acharChavesPeloNome em AnimalRepository).

// UI (ClienteConsoleUI.java, etc.):
// Esta classe será responsável por TODA a interação com o console para aquela entidade
// (solicitar dados, exibir mensagens, menus específicos).
// Ela terá uma instância de Scanner.
// Utilize ValidadorEntrada para validar as entradas.

// Service (ClienteService.java, etc.):
// Deve implementar a interface Service.
// No construtor, receba instâncias da UI e do Repository correspondentes
// (ex: public ClienteService(ClienteConsoleUI ui, ClienteRepository clienteRepository)).
// Os métodos (cadastrar, consultar, alterar, remover, listar) não devem mais receber Scanner
// leia como parâmetro. Eles orquestrarão a lógica, chamando métodos da UI para obter dados
// e do Repository para persistir/recuperar.

// TODO: implementar as classes de UI e Service para cada entidade, conforme o modelo definido:
// AnimalConsoleUI.java.