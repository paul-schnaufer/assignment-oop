package petshop.ui;

import java.util.Scanner;

import petshop.util.ValidadorEntrada;

/**
 * Classe base para a interface de usuário do console.
 * Fornece métodos comuns para exibir mensagens, cabeçalhos e capturar entradas do usuário.
 */
public abstract class BaseConsoleUI {
    protected static final String CABECALHO_MARCADOR = "===";
    protected static final String SEPARADOR = "-----------------------------------";
    protected Scanner leia;

    /**
     * Construtor da classe BaseConsoleUI.
     * Inicializa o Scanner para leitura de entradas do usuário.
     */
    public BaseConsoleUI(Scanner leia) {
        this.leia = leia;
    }

    /**
     * Exibe um cabeçalho com o título fornecido.
     * @param titulo O título a ser exibido no cabeçalho.
     */
    public void mostrarCabecalho(String titulo) {
        System.out.println(CABECALHO_MARCADOR + " " + titulo + " " + CABECALHO_MARCADOR);
        System.out.println(SEPARADOR);
    }

    /**
     * Exibe uma mensagem para o usuário.
     * @param mensagem A mensagem a ser exibida.
     */
    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Solicita uma confirmação do usuário com uma mensagem específica.
     * @param mensagem A mensagem a ser exibida para o usuário.
     * @return true se o usuário responder "S" (sim), false caso contrário.
     */
    public boolean receberConfirmacao(String mensagem) {
        System.out.println(mensagem + " (S/N): ");
        String resposta = leia.nextLine().trim().toUpperCase();
        return resposta.equals("S");
    }

    /**
     * Captura um inteiro do usuário dentro de um intervalo específico.
     * @param min O valor mínimo permitido.
     * @param max O valor máximo permitido.
     * @return O inteiro capturado pelo usuário.
     */
    public int capturarInteiro(int min, int max) {
        return ValidadorEntrada.lerInteiroValido(leia, min, max);
    }
}