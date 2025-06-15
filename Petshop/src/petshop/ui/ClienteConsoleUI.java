package petshop.ui;

import petshop.model.Cliente;
import petshop.util.ValidadorEntrada;

/**
 * Classe responsável pela interface de usuário do console para interações relacionadas a clientes.
 * Esta classe estende BaseConsoleUI e fornece métodos para solicitar informações do usuário,
 * exibir menus e mostrar detalhes de clientes.
 */
public class ClienteConsoleUI extends BaseConsoleUI {
    /**
     * Construtor da classe ClienteConsoleUI.
     * Inicializa a interface de usuário do console para interações relacionadas a clientes.
     */
    public ClienteConsoleUI(Scanner leia) {
        super(leia);
    }

    /**
     * Exibe o cabeçalho do console com o título fornecido.
     */
    public void menuPrincipalCliente() {
        mostrarCabecalho("Menu Principal");
        System.out.println(
            "1 — Cadastrar Cliente"
            + "\n2 — Buscar Cliente por CPF"
            + "\n3 — Atualizar Cliente"
            + "\n4 — Remover Cliente"
            + "\n5 — Listar Clientes"
            + "\n6 — Sair"
            + "\n" + SEPARADOR);
    }

    /**
     * Exibe o menu de alteração de clientes.
     * Este método apresenta as opções disponíveis para o usuário alterar os dados de um cliente.
     */
    public void menuAlterarCliente() {
        mostrarCabecalho("Menu de Alteração de Cliente");
        System.out.println(
            "Quais dados do cliente você deseja alterar?"
            + "1 — Alterar Nome"
            + "\n2 — Alterar Telefone"
            + "\n3 — Alterar Email"
            + "\n4 — Alterar RG"
            + "\n5 — Alterar CPF"
            + "\n6 — Todos os dados"
            + "\n7 — Voltar"
            + "\n" + SEPARADOR);
    }

    /**
     * Exibe os detalhes de um cliente.
     * Este método recebe um objeto Cliente e exibe suas informações detalhadas no console.
     *
     * @param cliente O objeto Cliente cujos detalhes serão exibidos.
     */
    public void mostrarDetalhesCliente(Cliente cliente) {
        System.out.println(cliente.toStringDetalhado());
    }

    /**
     * Solicita ao usuário o nome do cliente.
     * @return O nome do cliente como uma string.
     */
    public String solicitarNomeCliente() {
        System.out.println("Nome do cliente: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário o telefone do cliente.
     * @return O telefone do cliente como uma string.
     */
    public String solicitarTelefoneCliente() {
        System.out.println("Telefone do cliente: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário o email do cliente.
     * @return O email do cliente como uma string.
     */
    public String solicitarEmailCliente() {
        System.out.println("Email do cliente: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário o RG do cliente.
     * @return O RG do cliente como uma string.
     */
    public String solicitarRgCliente() {
        return ValidadorEntrada.lerRgValido(leia);
    }

    /**
     * Solicita ao usuário o CPF do cliente.
     * @return O CPF do cliente como uma string, validado.
     */
    public String solicitarCpfCliente() {
        return ValidadorEntrada.lerCpfValido(leia);
    }
}    