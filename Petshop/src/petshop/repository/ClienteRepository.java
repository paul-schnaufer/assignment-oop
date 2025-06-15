package petshop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import petshop.model.Cliente;

/**
 * Classe responsável por gerenciar o repositório de clientes do petshop.
 * Esta classe fornece métodos para adicionar, atualizar, buscar e remover clientes,
 * além de verificar a existência de clientes e obter informações sobre o repositório.
 */
public class ClienteRepository {
    private Map<String, Cliente> clientes;

    /**
     * Construtor da classe ClienteRepository.
     * Inicializa o repositório de clientes com o banco de dados em memória.
     */
    public ClienteRepository() {
        this.clientes = BancoDeDadosEmMemoria.clientes;
    }

    /**
     * Verifica se o repositório de clientes está vazio.
     * 
     * @return true se não houver clientes, false caso contrário
     */
    public boolean isEmpty() {
        return clientes.isEmpty();
    }

    /**
     * Obtém um cliente pelo identificador (chave).
     * 
     * @param chave A chave do cliente a ser buscado
     * @return O objeto Cliente correspondente à chave, ou null se não encontrado
     */
    public Cliente getByKey(String chave) {
        return clientes.get(chave);
    }

    /**
     * Adiciona ou atualiza um cliente no repositório.
     * 
     * @param chave A chave do cliente a ser adicionado ou atualizado
     * @param cliente O objeto Cliente a ser salvo
     */
    public void save(String chave, Cliente cliente) {
        clientes.put(chave, cliente);
    }

    /**
     * Remove um cliente do repositório.
     * 
     * @param chave A chave do cliente a ser removido
     * @return true se o cliente foi removido com sucesso, false se não foi encontrado
     */
    public boolean delete(String chave) {
        if (clientes.containsKey(chave)) {
            clientes.remove(chave);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se um cliente existe no repositório.
     * 
     * @param chave A chave do cliente a ser verificada
     * @return true se o cliente existir, false caso contrário
     */
    public boolean exists(String chave) {
        return clientes.containsKey(chave);
    }

    /**
     * Obtém o número total de clientes no repositório.
     * 
     * @return O número de clientes no repositório
     */
    public int size() {
        return clientes.size();
    }

    /**
     * Obtém uma lista de todos os clientes no repositório.
     * 
     * @return Uma lista contendo todos os objetos Cliente armazenados
     */
    public List<Cliente> getAll() {
        return new ArrayList<>(clientes.values());
    }
}
