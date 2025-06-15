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

    public void animalRepository() {
        this.animais = BancoDeDadosEmMemoria.animais;
    }

    public Animal getByKey(String chave) {
        return animais.get(chave);
    }

    public void save(String chave, Animal animal) {
        animais.put(chave, animal);
    }

    public void delete(String key) {
        if (animais.containsKey(key)) {
            animais.remove(key);
        } else {
            System.out.println("Animal com a chave " + key + " não encontrado.");
        }
    }

    public boolean exists(String key) {
        return animais.containsKey(key);
    }

    /**
     * Método para buscar um animal pelo nome.
     * Retorna uma lista de animais que possuem o nome especificado.
     *
     * @param nome O nome do animal a ser buscado
     * @return Lista de animais com o nome especificado
     */
    public List<Animal> acharPeloNome(String nome) {
        List<Animal> resultado = new ArrayList<>();
        for (Animal animal : animais.values()) {
            if (animal.getNome().equalsIgnoreCase(nome)) {
                resultado.add(animal);
            }
        }
        return resultado;
    }
}
