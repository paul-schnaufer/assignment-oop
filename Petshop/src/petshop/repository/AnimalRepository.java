package petshop.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import petshop.model.Animal;

/**
 * Classe que representa o repositório de animais no sistema de Petshop.
 * Esta classe é responsável por gerenciar as operações relacionadas aos animais,
 * como adição, remoção e busca de animais.
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
     * @return O animal correspondente à chave, ou null se não encontrado
     */
    public Animal getByKey(String chave) {
        return animais.get(chave);
    }

    /**
     * Adiciona um novo animal ao repositório.
     * 
     * @param chave A chave do animal a ser adicionado
     * @param animal O objeto Animal a ser adicionado
     */
    public void save(String chave, Animal animal) {
        animais.put(chave, animal);
    }

    /**
     * Atualiza um animal existente no repositório.
     * 
     * @param chave A chave do animal a ser atualizado
     * @param animal O objeto Animal atualizado
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
     * @param chave A chave do animal a ser verificado
     * @return true se o animal existir, false caso contrário
     */
    public boolean exists(String chave) {
        return animais.containsKey(chave);
    }

    /**
     * Obtém o número total de animais no repositório.
     * 
     * @return O número de animais no repositório
     */
    public int size() {
        return animais.size();
    }

    /**
     * Obtém todos os animais do repositório.
     * 
     * @return Lista de todos os animais
     */
    public List<Animal> getAll() {
        return new ArrayList<>(animais.values());
    }

    /**
     * Busca animais pelo nome.
     * Este método percorre o repositório de animais e retorna uma lista de animais
     * que possuem o nome especificado.
     * 
     * @param nome O nome do animal a ser buscado
     * @return Lista de chaves com o nome especificado
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