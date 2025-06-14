package petshop.service;

import java.util.Scanner;

/**
 * Interface Service que define os métodos básicos para operações de cadastro,
 * consulta, alteração e remoção de entidades no sistema de petshop.
 */
public interface Service {
    void cadastrar(Scanner leia);
    void consultar(Scanner leia);
    void alterar(Scanner leia);
    void remover(Scanner leia);
}
