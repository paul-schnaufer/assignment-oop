package petshop.ui;

import java.util.List;
import java.util.Scanner;

import petshop.model.Cliente;
import petshop.util.ValidadorEntrada;

/**
 * Classe AnimalConsoleUI que representa a interface de usuário para interações relacionadas a animais.
 * Esta classe fornece métodos para solicitar informações do usuário, exibir mensagens e detalhes de animais,
 * e capturar entradas do usuário.
 */
public class ClienteConsoleUI {
    final String CABECALHO = "===";
    final String SEPARADOR = "-----------------------------------";
    private Scanner leia = new Scanner(System.in);

    public void menuPrincipal() {
        System.out.println(CABECALHO + " Menu Principal " + CABECALHO);
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Buscar Cliente por CPF");
        System.out.println("3 - Atualizar Cliente");
        System.out.println("4 - Remover Cliente");
        System.out.println("5 - Listar Clientes");
        System.out.println("0 - Sair");
        System.out.println(SEPARADOR);
    }
}    