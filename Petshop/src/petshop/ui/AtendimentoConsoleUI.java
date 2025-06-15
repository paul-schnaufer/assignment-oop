package petshop.ui;

import java.util.Scanner;

import petshop.model.Atendimento;
import petshop.model.Animal;
import petshop.model.Cliente;
import petshop.model.Funcionario;
import petshop.util.ValidadorEntrada;

/**
 * Classe que representa a interface de usuário para
 * o gerenciamento de Atendimentos no sistema de Petshop.
 * Esta classe estende BaseConsoleUI e fornece métodos para exibir menus,
 * solicitar entradas do usuário
 * e mostrar detalhes dos atendimentos.
 */
public class AtendimentoConsoleUI extends BaseConsoleUI {

    /**
     * Construtor da classe AtendimentoConsoleUI.
     * Inicializa a interface de usuário do console para interações relacionadas a atendimentos.
     */
    public AtendimentoConsoleUI(Scanner leia) {
        super(leia);
    }

    /**
     * Exibe o menu principal de atendimentos.
     * Este método apresenta as opções disponíveis para o usuário interagir com os atendimentos.
     */
    public void menuPrincipalAtendimento() {
        mostrarCabecalho("Menu Principal");
        System.out.println(
            "1 - Cadastrar Atendimento" +
            "\n2 - Buscar Atendimento por Código" +
            "\n3 - Atualizar Atendimento" +
            "\n4 - Remover Atendimento" +
            "\n5 - Listar Atendimentos" +
            "\n6 - Sair" +
            "\n" + SEPARADOR
            );
    }

    /**
     * Exibe o menu de alteração de atendimentos.
     * Este método apresenta as opções disponíveis
     * para o usuário alterar os dados de um atendimento.
     */
    public void menuAlterarAtendimento() {
        mostrarCabecalho("Menu de Alteração de Atendimento");
        System.out.println(
            "Quais dados do atendimento você deseja alterar?" +
            "\n1 - Alterar Data" +
            "\n2 - Alterar Cliente" +
            "\n3 - Alterar Animal" +
            "\n4 - Alterar Funcionário" +
            "\n5 - Todos os dados" +
            "\n6 - Voltar" +
            "\n" + SEPARADOR
            );
    }

    /**
     * Exibe os detalhes de um atendimento.
     * Este método recebe um objeto Atendimento e exibe suas informações detalhadas no console.
     *
     * @param atendimento O objeto Atendimento cujos detalhes serão exibidos.
     */
    public void mostrarDetalhesAtendimento(Atendimento atendimento) {
        System.out.println(atendimento.toStringDetalhado());
    }

    /**
     * Solicita ao usuário o código do atendimento.
     * Este método lê a entrada do usuário e retorna o código como uma string.
     *
     * @return O código do atendimento fornecido pelo usuário.
     */
    public String solicitarCodigoAtendimento() {
        System.out.println("Código do atendimento: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário a data do atendimento.
     * Este método utiliza o ValidadorEntrada para garantir que a data seja válida.
     *
     * @return A data do atendimento fornecida pelo usuário, validada.
     */
    public String solicitarDataAtendimento() {
        return ValidadorEntrada.lerDataValida(leia);
    }

    /**
     * Solicita ao usuário a chave do cliente para o atendimento.
     * Este método lê a entrada do usuário e retorna o CPF do cliente como uma string.
     *
     * @return O CPF do cliente fornecido pelo usuário.
     */
    public String solicitarChaveClienteAtendimento() {
        System.out.println("Digite o CPF do cliente: ");
        String cpf = leia.nextLine();

        String chaveCliente = cpf;

        return chaveCliente;
    }

    /**
     * Solicita ao usuário a chave do animal para o atendimento.
     * Este método lê o nome do animal e o CPF do dono, e retorna uma string formatada como chave.
     *
     * @return A chave do animal no formato "nome — cpfDono".
     */
    public String solicitarChaveAnimalAtendimento() {
        System.out.println("Digite o nome do animal: ");
        String nome = leia.nextLine();
        System.out.println("Digite o CPF do dono do animal: ");
        String cpfDono = leia.nextLine();

        String chaveAnimal = nome + " — " + cpfDono;

        return chaveAnimal;
    }

    /**
     * Solicita ao usuário a chave do funcionário responsável pelo atendimento.
     * Este método lê a matrícula do funcionário e retorna como chave.
     *
     * @return A chave do funcionário, que é a matrícula fornecida pelo usuário.
     */
    public String solicitarChaveFuncionarioAtendimento() {
        System.out.println("Digite a matrícula do funcionário: ");
        String matricula = leia.nextLine();
        String chaveFuncionario = matricula;

        return chaveFuncionario;
    }
}