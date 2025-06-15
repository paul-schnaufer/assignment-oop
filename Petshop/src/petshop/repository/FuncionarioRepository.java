package petshop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import petshop.model.Funcionario;

/**
 * Classe responsável por gerenciar o repositório de funcionários do petshop.
 * Esta classe fornece métodos para adicionar, atualizar, buscar e remover funcionários,
 * além de verificar a existência de funcionários e obter informações sobre o repositório.
 */
public class FuncionarioRepository {
    private Map<String, Funcionario> funcionarios;

    /**
     * Construtor da classe FuncionarioRepository.
     * Inicializa o repositório de funcionários com o banco de dados em memória.
     */
    public FuncionarioRepository() {
        this.funcionarios = BancoDeDadosEmMemoria.funcionarios;
    }

    /**
     * Verifica se o repositório de funcionários está vazio.
     * 
     * @return true se não houver funcionários, false caso contrário
     */
    public boolean isEmpty() {
        return funcionarios.isEmpty();
    }

    /**
     * Obtém um funcionário pelo identificador (chave).
     * 
     * @param chave A chave do funcionário a ser buscado
     * @return O objeto Funcionario correspondente à chave, ou null se não encontrado
     */
    public Funcionario getByKey(String chave) {
        return funcionarios.get(chave);
    }

    /**
     * Adiciona ou atualiza um funcionário no repositório.
     * 
     * @param chave A chave do funcionário a ser adicionado ou atualizado
     * @param funcionario O objeto Funcionario a ser salvo
     */
    public void save(String chave, Funcionario funcionario) {
        funcionarios.put(chave, funcionario);
    }

    /**
     * Remove um funcionário do repositório.
     * 
     * @param chave A chave do funcionário a ser removido
     * @return true se o funcionário foi removido com sucesso, false se não foi encontrado
     */
    public boolean delete(String chave) {
        if (funcionarios.containsKey(chave)) {
            funcionarios.remove(chave);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se um funcionário existe no repositório.
     * 
     * @param chave A chave do funcionário a ser verificada
     * @return true se o funcionário existir, false caso contrário
     */
    public boolean exists(String chave) {
        return funcionarios.containsKey(chave);
    }

    /**
     * Obtém o número total de funcionários no repositório.
     * 
     * @return O número de funcionários cadastrados
     */
    public int size() {
        return funcionarios.size();
    }

    /**
     * Obtém uma lista de todos os funcionários cadastrados no repositório.
     * 
     * @return Uma lista contendo todos os objetos Funcionario
     */
    public List<Funcionario> getAll() {
        return new ArrayList<>(funcionarios.values());
    }
}