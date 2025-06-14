package petshop.main;

import java.util.Scanner;

import petshop.modelo.Cliente;
import petshop.modelo.Funcionario;
import petshop.service.ClienteService;

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

        System.out.println("Selecione a opção\n1 - Cliente\n2 - Funcionário\n3 - Animal\n4 - Atendimento");
        int acao = leia.nextInt();
        
        switch (acao) {
            case 1 -> {
                System.out.println("Que ação deseja realizar?\n1 - Cadastro de Cliente\n2 - Consulta de Cliente\n3 - Alteração de Cliente\n4 - Remoção de Cliente");
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
                
            }
        }
    }
}