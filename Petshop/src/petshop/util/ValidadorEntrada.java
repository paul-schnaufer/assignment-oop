package petshop.util;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import petshop.modelo.Animal;
import petshop.modelo.Atendimento;
import petshop.modelo.Cliente;
import petshop.modelo.Funcionario;

import petshop.util.BancoDeDadosEmMemoria;

/**
 * Classe utilitária para verificar entradas de dados no sistema de Petshop.
 * Esta classe pode conter métodos estáticos para validação de entradas,
 * como verificar se um CPF é válido, se um nome não está vazio, etc.
 */
public class ValidadorEntrada {
    private Map<String, Cliente> clientes;
    private Map<String, Animal> animais;
    private Map<String, Funcionario> funcionarios;
    private Map<String, Atendimento> atendimentos;

    public ValidadorEntrada(
            Map<String, Cliente> clientes,
            Map<String, Animal> animais,
            Map<String, Funcionario> funcionarios,
            Map<String, Atendimento> atendimentos) {
        this.clientes = clientes;
        this.animais = animais;
        this.funcionarios = funcionarios;
        this.atendimentos = atendimentos;
    }

    // Métodos estáticos para validação de entradas podem ser adicionados aqui.
    /**
     * Verifica se o nome fornecido não está vazio.
     * @param nome O nome a ser verificado.
     * @return true se o nome não estiver vazio, false caso contrário.
     */
    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    /**
     * Obtém um cpf válido do usuário com 11 dígitos numéricos.
     * @param cpf O CPF a ser verificado.
     * @return true se o CPF for válido, false caso contrário.
     */
    public static String lerCpfValido() {
        String cpf;
        while (true) {
            System.out.println("Digite o CPF (11 dígitos, apenas números): ");
            String cpf = leia.nextLine();
            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido. Deve conter 11 dígitos numéricos.");
            }
        }

        String cpfFormatado = formataCpf(cpf);

        return cpfFormatado;
    }

    /**
     * Formata um CPF de 11 dígitos em uma string no formato "XXX.XXX.XXX-XX".
     * @param cpf O CPF a ser formatado.
     * @return O CPF formatado.
     */
    public static String formataCpf(String cpf) {
        return String.format("%s.%s.%s-%s", 
                cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9, 11));
    }

    /**
     * Verifica se a entrada fornecida existe em um banco de dados.
     * @param entrada A entrada a ser verificada.
     * @return true se a entrada for válida, false caso contrário.
     */
    public static boolean validarEntradaEmBancoDeDados(String entrada) {
        BancoDeDadosEmMemoria
    }

    /**
    * Obtém um inteiro válido do usuário dentro de um intervalo específico.
    * @param input O inteiro a ser verificado.
    * @param min O valor mínimo do intervalo.
    * @param max O valor máximo do intervalo.
    * @return true se o inteiro estiver dentro do intervalo, false caso contrário.
    */
    public static int lerInteiroValido(Scanner leia, int min, int max) {
        int valor;
        while (true) {
            try {
                valor = Integer.parseInt(leia.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("Número fora do intervalo permitido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
            }
            
        }
    }

    public static float lerFloatPositivo(Scanner leia, String mensagem) {
        float valor;
        while (true) {
            try {
                System.out.println(mensagem);
                valor = Float.parseFloat(leia.nextLine());
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }
}
