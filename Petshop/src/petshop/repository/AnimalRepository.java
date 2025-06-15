package petshop.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import petshop.model.Animal;

/**
 * Classe responsável por gerenciar o repositório de animais do petshop.
 * Esta classe fornece métodos para adicionar, atualizar, buscar e remover animais,
 * além de verificar a existência de animais e obter informações sobre o repositório.
 */
public class AnimalRepository {
    private Map<String, Animal> animais;

    public AnimalRepository() {
        this.animais = BancoDeDadosEmMemoria.animais;
    }

    public boolean isEmpty() {
        return animais.isEmpty();
    }

    /**
     * Obtém um animal pelo identificador (chave).
     * 
     * @param chave A chave do animal a ser buscado
     * @return O objeto Animal correspondente à chave, ou null se não encontrado
     */
    public Animal getByKey(String chave) {
        return animais.get(chave);
    }

    /**
     * Adiciona ou atualiza um animal no repositório.
     * 
     * @param chave A chave do animal a ser adicionado ou atualizado
     * @param animal O objeto Animal a ser salvo
     */
    public void save(String chave, Animal animal) {
        animais.put(chave, animal);
    }

    /**
     * Remove um animal do repositório.
     * 
     * @param chave A chave do animal a ser removido
     * @return true se o animal foi removido com sucesso, false se não foi encontrado
     */
    public boolean delete(String chave) {
        if (animais.containsKey(chave)) {
            animais.remove(chave);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se um animal existe no repositório.
     * 
     * @param chave A chave do animal a ser verificada
     * @return true se o animal existir, false caso contrário
     */
    public boolean exists(String chave) {
        return animais.containsKey(chave);
    }

    /**
     * Obtém o número total de animais no repositório.
     * 
     * @return O número de animais armazenados
     */
    public int size() {
        return animais.size();
    }

    /**
     * Obtém uma lista de todos os animais no repositório.
     * 
     * @return Lista de todos os animais
     */
    public List<Animal> getAll() {
        return new ArrayList<>(animais.values());
    }

    /**
     * Busca animais pelo nome e retorna uma lista de chaves e CPF dos donos.
     * 
     * @param nome O nome do animal a ser buscado
     * @return Lista de strings contendo o nome do animal e o CPF do dono
     */
    public List<String> acharChavesPeloNome(String nome) {
        List<String> resultado = new ArrayList<>();
        for (Animal animal : animais.values()) {
            if (animal.getNome().equalsIgnoreCase(nome)) {
                resultado.add(animal.getNome() + " — " + animal.getCpfDono());
            }
        }

        return resultado;
    }
}