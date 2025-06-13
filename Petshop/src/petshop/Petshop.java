/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petshop;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/** Manipula múltiplas classes para administrar um petshop. */
public class Petshop {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        
        System.out.println("Selecione a opção\n1 - Cliente\n2 - Funcionário\n3 - Animal\n4 - Atendimento");
        int acao = leia.nextInt();
        
        switch (acao) {
            case 1 -> {
                System.out.println("Que ação deseja realizar?\n1 - Cadastro de Cliente\n2 - Consulta de Cliente\n3 - Alteração de Cliente\n4 - Remoção de Cliente");
                acao = leia.nextInt();
                leia.nextLine(); //Consumir a quebra de linha
                Map<String, Cliente> clientes = new HashMap<>();
                Map<String, Funcionario> funcionarios = new HashMap<>();

                switch (acao) {
                    case 1 -> {
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

                    case 2 -> {
                        System.out.println("Insira o Nome");
                        String nome = leia.nextLine();
                        System.out.println("Insira a Matrícula");
                        String matricula = leia.nextLine();
                        System.out.println("Insira a Qualificação"); 
                        String qualificacao = leia.nextLine();
                        System.out.println("Insira a Descrição da Função Realizada"); 
                        String descricaoFuncao = leia.nextLine();
                        System.out.println("Insira a Carga Horária Semanal");
                        float cargaHoraria = Float.parseFloat(leia.nextLine());
                       
                        funcionarios.put(matricula, new Funcionario(nome, matricula, qualificacao, descricaoFuncao, cargaHoraria));
                        funcionarios.get(matricula).consultarFuncionario();
               
            }
                }
            }
            case 2 -> {
            }
        }
    }
}
