package petshop.ui;

import java.util.List;
import java.util.Scanner;

import petshop.model.Animal;
import petshop.util.ValidadorEntrada;

/**
 * Classe responsável pela interface de usuário do console para interações relacionadas a animais.
 * Esta classe estende BaseConsoleUI e fornece métodos para solicitar informações do usuário,
 * exibir menus e mostrar detalhes de animais.
 */
public class AnimalConsoleUI extends BaseConsoleUI {

    public AnimalConsoleUI(Scanner leia) {
        super(leia);
    }

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
     * Exibe um cabeçalho com o título fornecido.
     * @param titulo O título a ser exibido no cabeçalho.
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
     * Exibe os detalhes de um animal.
     * @param animal O animal cujos detalhes serão exibidos.
     */
    public void mostrarDetalhesAnimal(Animal animal) {
        System.out.println(animal.toStringDetalhado());
    }

    /**
     * Exibe o menu principal para interações relacionadas a animais.
     */
    public void menuPrincipalAnimal() {
        mostrarCabecalho("Menu Principal");
        System.out.println(
            "Escolha uma opção:" +
            "\n1 — Cadastrar animal" +
            "\n2 — Consultar animal" +
            "\n3 — Alterar animal" +
            "\n4 — Excluir animal" +
            "\n5 — Listar animais" +
            "\n6 — Sair" +
            "\n" + SEPARADOR
            );
    }

    /**
     * Exibe o menu de alteração de dados do animal.
     */
    public void menuAlterarAnimal() {
        mostrarCabecalho("Menu de Alteração de Animal");
        System.out.println(
            "Quais dados do animal você deseja alterar?" +
            "\n1 — Nome" +
            "\n2 — Peso" +
            "\n3 — Altura" +
            "\n4 — Dono (CPF)" +
            "\n5 — Nome do animal e dono (CPF)" +
            "\n6 — Todos os dados" +
            "\n7 — Voltar" +
            "\n" + SEPARADOR
            );
    }
}