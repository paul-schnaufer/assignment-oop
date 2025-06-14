package petshop.service;

import java.util.Map;
import java.util.Scanner;

import petshop.modelo.Cliente;

/**
 * Classe responsável por gerenciar as operações relacionadas aos clientes do petshop.
 */
public class ClienteService implements Service {
    private Map<String, Cliente> clientes;

    public ClienteService(Map<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public void cadastrar(Scanner leia) {
        System.out.println("Insira o nome: ");
        String nome = leia.nextLine();
        System.out.println("Insira o telefone: ");
        String telefone = leia.nextLine();
        System.out.println("Insira o e-mail: "); 
        String email = leia.nextLine();
        System.out.println("Insira o RG: ");
        String rg = leia.nextLine();
        System.out.println("Insira o CPF: ");
        String cpf = leia.nextLine();

        clientes.put(cpf, new Cliente(nome, telefone, email, rg, cpf));
        
    }

    @Override
    public void consultar(Scanner leia) {
        System.out.println("Insira o CPF do cliente a ser consultado: ");
        String cpf = leia.nextLine();

        if (clientes.containsKey(cpf)) {
            
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    @Override
    public void alterar(Scanner leia) {
        System.out.println("Insira o CPF do cliente a ser alterado: ");
        String cpf = leia.nextLine();

        if (clientes.containsKey(cpf)) {
            System.out.println("Novo nome: ");
            String nome = leia.nextLine();
            System.out.println("Novo telefone: ");
            String telefone = leia.nextLine();
            System.out.println("Novo e-mail: ");
            String email = leia.nextLine();
            System.out.println("Novo RG: ");
            String rg = leia.nextLine();

            Cliente cliente = clientes.get(cpf);
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);
            cliente.setRg(rg);
            
            System.out.println("Cliente alterado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    @Override
    public void remover(Scanner leia) {
        System.out.println("Insira o CPF do cliente a ser removido: ");
        String cpf = leia.nextLine();

        if (clientes.containsKey(cpf)) {
            clientes.remove(cpf);
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}