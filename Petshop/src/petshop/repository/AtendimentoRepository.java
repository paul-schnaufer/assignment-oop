package petshop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import petshop.model.Atendimento;

/**
 * Classe responsável por gerenciar o repositório de atendimentos do petshop.
 * Esta classe fornece métodos para adicionar, atualizar, buscar e remover atendimentos,
 * além de verificar a existência de atendimentos e obter informações sobre o repositório.
 */
public class AtendimentoRepository {
    private Map<String, Atendimento> atendimentos;

    /**
     * Construtor da classe AtendimentoRepository.
     * Inicializa o repositório de atendimentos com o banco de dados em memória.
     */
    public AtendimentoRepository() {
        this.atendimentos = BancoDeDadosEmMemoria.atendimentos;
    }

    /**
     * Verifica se o repositório de atendimentos está vazio.
     * 
     * @return true se não houver atendimentos, false caso contrário
     */
    public boolean isEmpty() {
        return atendimentos.isEmpty();
    }

    /**
     * Obtém um atendimento pelo identificador (chave).
     * 
     * @param chave A chave do atendimento a ser buscado
     * @return O objeto Atendimento correspondente à chave, ou null se não encontrado
     */
    public Atendimento getByKey(String chave) {
        return atendimentos.get(chave);
    }

    /**
     * Adiciona ou atualiza um atendimento no repositório.
     * 
     * @param chave A chave do atendimento a ser adicionado ou atualizado
     * @param atendimento O objeto Atendimento a ser salvo
     */
    public void save(String chave, Atendimento atendimento) {
        atendimentos.put(chave, atendimento);
    }

    /**
     * Remove um atendimento do repositório.
     * 
     * @param chave A chave do atendimento a ser removido
     * @return true se o atendimento foi removido com sucesso, false se não foi encontrado
     */
    public boolean delete(String chave) {
        if (atendimentos.containsKey(chave)) {
            atendimentos.remove(chave);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se um atendimento existe no repositório.
     * 
     * @param chave A chave do atendimento a ser verificada
     * @return true se o atendimento existir, false caso contrário
     */
    public boolean exists(String chave) {
        return atendimentos.containsKey(chave);
    }

    /**
     * Obtém o número total de atendimentos no repositório.
     * 
     * @return O número de atendimentos armazenados
     */
    public int size() {
        return atendimentos.size();
    }

    /**
     * Obtém uma lista de todos os atendimentos armazenados no repositório.
     * 
     * @return Uma lista contendo todos os atendimentos
     */
    public List<Atendimento> getAll() {
        return new ArrayList<>(atendimentos.values());
    }
}
