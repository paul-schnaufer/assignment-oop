package petshop.ui;

import java.util.List;
import java.util.Scanner;

import petshop.model.Animal;
import petshop.util.ValidadorEntrada;

/**
 * Classe AnimalConsoleUI que representa a interface de usuário para interações relacionadas a animais.
 * Esta classe fornece métodos para solicitar informações do usuário, exibir mensagens e detalhes de animais,
 * e capturar entradas do usuário.
 */
public class AnimalConsoleUI {
    final String CABECALHO = "===";
    final String SEPARADOR = "-----------------------------------";
    private Scanner leia = new Scanner(System.in);

    /**
     * Solicita ao usuário o nome do animal.
     * @return O nome do animal como uma string.
     */
    public String solicitarNomeAnimal() {
        System.out.println("Nome do animal: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário o peso do animal.
     * @return O peso do animal como um float.
     */
    public float solicitarPesoAnimal() {
        return ValidadorEntrada.lerFloatPositivo(leia, "Peso do animal: ");
    }

    /**
     * Solicita ao usuário a altura do animal.
     * @return A altura do animal como um float.
     */
    public float solicitarAlturaAnimal() {
        return ValidadorEntrada.lerFloatPositivo(leia, "Altura do animal: ");
    }

    /**
     * Solicita ao usuário o CPF do dono do animal.
     * @return O CPF do dono do animal como uma string.
     */
    public String solicitarCpfDonoAnimal() {
        return ValidadorEntrada.lerCpfValido(leia);
    }

    /**
     * Solicita ao usuário o tipo de animal a ser cadastrado.
     * @return O tipo de animal como uma string.
     */
    public int escolherAnimal(List<String> chavesAnimaisEncontrados) {
        for (int i = 0; i < chavesAnimaisEncontrados.size(); i++) {
            String cpfDono = chavesAnimaisEncontrados.get(i).split(" — ")[1];
            System.out.println((i + 1) + " — Dono CPF: " + cpfDono);
        }
        System.out.println("Escolha o número correspondente: ");
        return ValidadorEntrada.lerInteiroValido(leia, 1, chavesAnimaisEncontrados.size());
    }

    /**
     * Exibe o cabeçalho com o título fornecido.
     * @param titulo O título a ser exibido no cabeçalho.
     */
    public void mostrarCabecalho(String titulo) {
        System.out.println(CABECALHO + " " + titulo + " " + CABECALHO);
        System.out.println(SEPARADOR);
    }

    /**
     * Exibe uma mensagem para o usuário.
     * @param msg A mensagem a ser exibida.
     */
    public void mostrarMensagem(String msg) {
        System.out.println(msg);
    }

    /**
     * Exibe os detalhes de um animal de forma detalhada.
     * @param animal O objeto Animal cujos detalhes serão exibidos.
     */
    public void mostrarDetalhesAnimal(Animal animal) {
        System.out.println(animal.toStringDetalhado());
    }

    /**
     * Solicita ao usuário uma confirmação para continuar com uma ação.
     * @param mensagem A mensagem a ser exibida para o usuário.
     * @return true se o usuário confirmar, false caso contrário.
     */
    public boolean receberConfirmacao(String mensagem) {
        System.out.println(mensagem + " (S/N): ");
        String resposta = leia.nextLine().trim().toUpperCase();
        return resposta.equals("S)");
    }

    /**
     * Captura um número inteiro dentro de um intervalo específico.
     * @param min O valor mínimo do intervalo.
     * @param max O valor máximo do intervalo.
     * @return O número inteiro capturado.
     */
    public int capturarInteiro(int min, int max) {
        int inteiroCapturado = ValidadorEntrada.lerInteiroValido(leia, min, max);

        return inteiroCapturado;
    }

    /**
     * Exibe um menu para o usuário escolher uma ação relacionada a animais.
     */
    public void menuAlterarAnimal() {
        System.out.println("Quais dados do animal você deseja alterar?"
                + "\n1 — Nome"
                + "\n2 — Peso"
                + "\n3 — Altura"
                + "\n4 — Dono (CPF)"
                + "\n5 — Nome do animal e dono (CPF)"
                + "\n6 — Todos os dados");
    }
}

// TODO: Verificar quando fechar o Scanner leia, se necessário.
//       Scanner leia.close(); // Fechar o Scanner quando não for mais necessário