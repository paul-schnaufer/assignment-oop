package petshop.service;

/**
 * Interface Service que define os métodos básicos para operações de serviço.
 * As classes que implementam esta interface devem fornecer implementações
 * para cadastrar, consultar, alterar, remover e listar entidades.
 */
public interface Service {
    void cadastrar();
    void consultar();
    void alterar();
    void remover();
    void listar();
}