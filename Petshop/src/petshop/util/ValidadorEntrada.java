package petshop.util;

import java.util.Scanner;
/**
 * Classe utilitária para verificar entradas de dados no sistema de Petshop.
 * Esta classe pode conter métodos estáticos para validação de entradas,
 * como verificar se um CPF é válido, se um nome não está vazio, etc.
 */
public class VerificaEntrada {
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
    * Verifica se o inteiro fornecido está dentro de um intervalo específico.
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
