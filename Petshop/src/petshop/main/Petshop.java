package petshop.main;

import java.util.Scanner;

import petshop.modelo.Cliente;
import petshop.modelo.Funcionario;
import petshop.modelo.Animal;
import petshop.modelo.Atendimento;

import petshop.service.ClienteService;
import petshop.service.FuncionarioService;
import petshop.service.AnimalService;

import java.util.Map;
import java.util.HashMap;

/**
 * Classe principal do sistema de gerenciamento de petshop.
 * Permite a interação com o usuário para realizar diversas operações.
 */
public class Petshop {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        
        Map<String, Cliente> clientes = new HashMap<>();
        Map<String, Funcionario> funcionarios = new HashMap<>();
        Map<String, Animal> animais = new HashMap<>();
        Map<String, Atendimento> atendimentos = new HashMap<>();
        
        int opcao = 0;
        
        while(opcao != 5){
            System.out.println("\nSelecione a opção\n1 - Cliente\n2 - Funcionário\n3 - Animal\n4 - Atendimento\n5 - Encerrar Execução");
            opcao = leia.nextInt();             
            int acao;
            switch (opcao) {
                case 1 -> {
                    System.out.println("\nQue ação deseja realizar?\n1 - Cadastro de Cliente\n2 - Consulta de Cliente\n3 - Alteração de Cliente\n4 - Remoção de Cliente\n5 - Voltar");
                    acao = leia.nextInt();
                    leia.nextLine(); //Consumir a quebra de linha
                    ClienteService clienteService = new ClienteService(clientes);

                    switch (acao) {
                        case 1 -> {                 
                            clienteService.cadastrar(leia);
                        }

                        case 2 -> {
                            clienteService.consultar(leia);
                        }

                        case 3 -> {
                            clienteService.alterar(leia);
                        }

                        case 4 -> {
                            clienteService.remover(leia);
                        }
                    }
                }

                case 2 -> {
                    System.out.println("\nQue ação deseja realizar?\n1 - Cadastro de Funcionário\n2 - Consulta de Funcionário\n3 - Alteração de Funcionário\n4 - Remoção de Funcionário");
                    acao = leia.nextInt();
                    leia.nextLine(); //Consumir a quebra de linha
                    FuncionarioService funcionarioService = new FuncionarioService(funcionarios);

                    switch (acao) {
                        case 1 -> {                 
                            funcionarioService.cadastrar(leia);
                        }

                        case 2 -> {
                            funcionarioService.consultar(leia);
                        }

                        case 3 -> {
                            funcionarioService.alterar(leia);
                        }

                        case 4 -> {
                            funcionarioService.remover(leia);
                        }
                    }
                }

                case 3 -> {
                    System.out.println("\nQue ação deseja realizar?\n1 - Cadastro de Animal\n2 - Consulta de Animal\n3 - Alteração de Animal\n4 - Remoção de Animal");
                    acao = leia.nextInt();
                    leia.nextLine(); //Consumir a quebra de linha
                    AnimalService animalService = new AnimalService(animais);

                    switch (acao) {
                        case 1 -> {                 
                            animalService.cadastrar(leia);
                        }

                        case 2 -> {
                            animalService.consultar(leia);
                        }

                        case 3 -> {
                            animalService.alterar(leia);
                        }

                        case 4 -> {
                            animalService.remover(leia);
                        }
                    }
                }

                case 4 -> {
                    System.out.println("\nQue ação deseja realizar?\n1 - Cadastro de Atendimento\n2 - Consulta de Atendimento\n3 - Alteração de Atendimento\n4 - Remoção de Atendimento");
                    acao = leia.nextInt();
                    leia.nextLine(); //Consumir a quebra de linha
                    FuncionarioService funcionarioService = new FuncionarioService(funcionarios);

                    switch (acao) {
                        case 1 -> {                 
                            funcionarioService.cadastrar(leia);
                        }

                        case 2 -> {
                            funcionarioService.consultar(leia);
                        }

                        case 3 -> {
                            funcionarioService.alterar(leia);
                        }

                        case 4 -> {
                            funcionarioService.remover(leia);
                        }
                    }
                }

            }
        }
        
        System.out.println("\nEncerrando Execução...");
    }
}