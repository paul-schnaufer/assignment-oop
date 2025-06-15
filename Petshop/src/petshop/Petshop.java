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
 * Classe principal do sistema Petshop.
 * Esta classe inicializa os repositórios, serviços e interfaces de usuário,
 * e gerencia o loop principal do menu do sistema.
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
            animalRepository,
            atendimentoRepository,
            clienteRepository,
            funcionarioRepository
            );
        ClienteService clienteService = new ClienteService(clienteUI, clienteRepository);
        FuncionarioService funcionarioService = new FuncionarioService(
            funcionarioUI, funcionarioRepository
            );
        // Loop de menus chamando os serviços normalmente
        int opcao = 0;

        while(opcao != 5){
            System.out.println(
                "\nSelecione a opção desejada:\n" +
                "1 - Cliente\n" +
                "2 - Funcionário\n" +
                "3 - Animal\n" +
                "4 - Atendimento\n" +
                "5 - Encerrar Execução"
                );
            opcao = petshop.util.ValidadorEntrada.lerInteiroValido(leia, 1, 5);

            int acao;
            switch (opcao) {
                case 1 -> {
                    clienteUI.menuPrincipalCliente();
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
                    funcionarioUI.menuPrincipalFuncionario();
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
                    animalUI.menuPrincipalAnimal();
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
                    atendimentoUI.menuPrincipalAtendimento();
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
        leia.close();
    }
}