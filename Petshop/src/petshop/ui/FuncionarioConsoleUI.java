package petshop.ui;

import java.util.Scanner;

import petshop.model.Funcionario;
import petshop.util.ValidadorEntrada;

/**
 * Classe que representa a interface de usuário para o
 * gerenciamento de Funcionários no sistema de Petshop.
 * Esta classe estende BaseConsoleUI e fornece métodos para exibir menus,
 * solicitar entradas do usuário
 * e mostrar detalhes dos funcionários.
 */
public class FuncionarioConsoleUI extends BaseConsoleUI{
    /**
     * Construtor da classe FuncionarioConsoleUI.
     * Inicializa a interface de usuário do console para interações relacionadas a funcionários.
     */
    public FuncionarioConsoleUI(Scanner leia) {
        super(leia);
    }

    /**
     * Exibe o menu principal de funcionários.
     * Este método apresenta as opções disponíveis para o usuário interagir com os funcionários.
     */
    public void menuPrincipalFuncionario() {
        mostrarCabecalho("Menu Principal");
        System.out.println(
            "1 - Cadastrar Funcionário" +
            "\n2 - Buscar Funcionário por Matrícula" +
            "\n3 - Atualizar Funcionário" +
            "\n4 - Remover Funcionário" +
            "\n5 - Listar Funcionários" +
            "\n6 - Sair" +
            "\n" + SEPARADOR +
            );
    }

    /**
     * Exibe o menu de alteração de funcionários.
     * Este método apresenta as opções disponíveis para o
     * usuário alterar os dados de um funcionário.
     */
    public void menuAlterarFuncionario() {
        mostrarCabecalho("Menu de Alteração de Funcionário");
        System.out.println(
            "Quais dados do funcionário você deseja alterar?" +
            "\n1 - Alterar Nome" +
            "\n2 - Alterar Matrícula" +
            "\n3 - Alterar Qualificação" +
            "\n4 - Alterar Descrição da Função" +
            "\n5 - Alterar Carga Horária Semanal" +
            "\n6 - Todos os dados" +
            "\n7 - Voltar" +
            "\n" + SEPARADOR +
            );
    }

    /**
     * Exibe os detalhes de um funcionário.
     * Este método recebe um objeto Funcionario e exibe suas informações detalhadas no console.
     *
     * @param funcionario O objeto Funcionario cujos detalhes serão exibidos.
     */
    public void mostrarDetalhesFuncionario(Funcionario funcionario) {
        System.out.println(funcionario.toStringDetalhado());
    }

    /**
     * Solicita ao usuário o nome do funcionário.
     * @return O nome do funcionário como uma string.
     */
    public String solicitarNomeFuncionario() {
        System.out.println("Nome do funcionário: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário a matrícula do funcionário.
     * @return A matrícula do funcionário como uma string.
     */
    public String solicitarMatriculaFuncionario() {
        System.out.println("Matrícula do funcionário: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário a qualificação do funcionário.
     * @return A qualificação do funcionário como uma string.
     */
    public String solicitarQualificacaoFuncionario() {
        System.out.println("Qualificação do funcionário: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário a descrição da função do funcionário.
     * @return A descrição da função do funcionário como uma string.
     */
    public String solicitarDescricaoFuncaoFuncionario() {
        System.out.println("Descrição da função do funcionário: ");
        return leia.nextLine();
    }

    /**
     * Solicita ao usuário a carga horária semanal do funcionário.
     * @return A carga horária semanal do funcionário como um float positivo.
     */
    public float solicitarCargaHorariaFuncionario() {
        return ValidadorEntrada.lerFloatPositivo(
            leia, "Carga horária semanal do funcionário (em horas): "
            );
    }
}
