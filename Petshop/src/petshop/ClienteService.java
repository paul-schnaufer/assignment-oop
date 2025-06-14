package petshop;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class ClienteService {
    private Map<String, Cliente> clientes;

    public ClienteService(Map<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public void cadastrar(Scanner leia) {
        System.out.println("Insira o Nome");
        String nome = leia.nextLine();
        System.out.println("Insira o telefone");
        String telefone = leia.nextLine();
        System.out.println("Insira o e-mail"); 
        String email = leia.nextLine();
        System.out.println("Insira o RG");
        String rg = leia.nextLine();
        System.out.println("Insira o CPF");
        String cpf = leia.nextLine();

        clientes.put(cpf, new Cliente(nome, telefone, email, rg, cpf));
        clientes.get(cpf).consultarCliente();
    }

    public void consultar(Scanner leia) {
        System.out.println("Insira o CPF do cliente a ser consultado");
        String cpf = leia.nextLine();
        
        if (clientes.containsKey(cpf)) {
            clientes.get(cpf).consultarCliente();
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}