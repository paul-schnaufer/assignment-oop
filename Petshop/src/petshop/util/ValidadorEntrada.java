package petshop.util;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import petshop.model.Animal;
import petshop.model.Atendimento;
import petshop.model.Cliente;
import petshop.model.Funcionario;
import petshop.repository.BancoDeDadosEmMemoria;

/**
 * Classe utilitária para validação de entradas do usuário.
 * Esta classe contém métodos estáticos para validar entradas como CPF, RG, nomes,
 * e outros dados necessários para o funcionamento do sistema de petshop.
 */
public class ValidadorEntrada {
    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    /**
     * Lê um CPF válido do usuário, garantindo que ele tenha 11 dígitos numéricos.
     * @param leia O Scanner para ler a entrada do usuário.
     * @return O CPF formatado no padrão "XXX.XXX.XXX-XX".
     */
    public static String lerCpfValido(Scanner leia) {
        String cpf;
        while (true) {
            System.out.println("Digite o CPF (11 dígitos, apenas números): ");
            cpf = leia.nextLine();
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
        return String.format(
            "%s.%s.%s-%s", 
            cpf.substring(0, 3),
            cpf.substring(3, 6),
            cpf.substring(6, 9),
            cpf.substring(9, 11)
            );
    }

    /**
     * Formata um RG de 10 dígitos em uma string no formato "XX.XXX.XXX-XX".
     * @param leia O Scanner para ler a entrada do usuário.
     * @return O RG formatado no padrão "XX.XXX.XXX-XX".
     */
    public static String formataRg(String rg) {
        return String.format(
            "%s.%s.%s-%s", 
            rg.substring(0, 2),
            rg.substring(2, 5),
            rg.substring(5, 8),
            rg.substring(8, 10)
            );
    }

    /**
     * Lê um RG válido do usuário, garantindo que ele tenha 10 dígitos numéricos.
     * @param leia O Scanner para ler a entrada do usuário.
     * @return O RG formatado no padrão "XX.XXX.XXX-XX".
     */
    public static String lerRgValido(Scanner leia) {
        while (true) {
            System.out.println("Digite o RG (10 dígitos, apenas números): ");
            String rg = leia.nextLine();
            if (rg.matches("\\d{10}")) {
                return formataRg(rg);
            } else {
                System.out.println("RG inválido. Deve conter 10 dígitos numéricos.");
            }
        }
    }

    /**
     * Lê um número inteiro válido do usuário, dentro de um intervalo específico.
     * @param leia O Scanner para ler a entrada do usuário.
     * @return O nome fornecido pelo usuário.
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

    /**
     * Lê uma data válida do usuário no formato dd/MM/yyyy.
     * @param leia O Scanner para ler a entrada do usuário.
     * @return A data fornecida pelo usuário, validada.
     */
    public static String lerDataValida(Scanner leia) {
        while (true) {
            System.out.println("Digite a data no formato dd/MM/yyyy: ");
            String data = leia.nextLine();
            if (data.matches("\\d{2}/\\d{2}/\\d{4}")) {
                return data;
            } else {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }

    /**
     * Lê um valor float positivo do usuário.
     * @param leia O Scanner para ler a entrada do usuário.
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @return O valor float positivo fornecido pelo usuário.
     */
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
