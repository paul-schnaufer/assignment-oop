package petshop.services;

/**
 * Interface Service que define os métodos básicos para operações de cadastro,
 * consulta, alteração e remoção de entidades no sistema de petshop.
 */
public interface Service {
    void cadastrar();
    void consultar();
    void alterar();
    void remover();
    void listar();
}
