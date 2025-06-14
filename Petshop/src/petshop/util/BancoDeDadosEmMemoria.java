package petshop.util;

import java.util.HashMap;
import java.util.Map;
import petshop.modelo.Animal;
import petshop.modelo.Atendimento;
import petshop.modelo.Cliente;
import petshop.modelo.Funcionario;

/**
 * Classe que simula um banco de dados em memória para armazenar informações
 * sobre clientes, animais, atendimentos e funcionários do petshop.
 * Utilizada para funcionalidade de persistência de dados durante a execução do sistema.
 * (Memória volátil/primária, os dados não são persistidos após o encerramento do programa)
 */
public class BancoDeDadosEmMemoria {
    public static Map<String, Cliente> clientes = new HashMap<>();
    public static Map<String, Animal> animais = new HashMap<>();
    public static Map<String, Atendimento> atendimentos = new HashMap<>();
    public static Map<String, Funcionario> funcionarios = new HashMap<>();
}