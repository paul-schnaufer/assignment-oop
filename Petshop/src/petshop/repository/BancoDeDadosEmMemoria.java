package petshop.repository;

import java.util.HashMap;
import java.util.Map;

import petshop.model.Animal;
import petshop.model.Atendimento;
import petshop.model.Cliente;
import petshop.model.Funcionario;

/**
 * Classe que representa um banco de dados em memória para armazenar
 * informações sobre clientes, animais, atendimentos e funcionários.
 * 
 * Esta classe utiliza mapas para armazenar os dados, permitindo acesso rápido
 * e eficiente aos registros.
 */
public class BancoDeDadosEmMemoria {
    public static Map<String, Cliente> clientes = new HashMap<>();
    public static Map<String, Animal> animais = new HashMap<>();
    public static Map<String, Atendimento> atendimentos = new HashMap<>();
    public static Map<String, Funcionario> funcionarios = new HashMap<>();
}