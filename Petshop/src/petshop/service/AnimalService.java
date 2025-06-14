package petshop.service;

import java.util.Map;
import java.util.Scanner;
import petshop.modelo.Animal;

public class AnimalService implements Service {
    private Map<String, Animal> animais;
    public AnimalService(Map<String, Animal> animais) {
        this.animais = animais;
    }

    @Override
    public void cadastrar(Scanner leia) {
        System.out.println("Insira o nome do animal: ");
        String nome = leia.nextLine();
        System.out.println("Insira o peso do animal: ");
        float peso = Float.parseFloat(leia.nextLine());
        System.out.println("Insira a altura do animal: ");
        float altura = Float.parseFloat(leia.nextLine());
        System.out.println("Insira o CPF do dono: ");
        String cpfDono = leia.nextLine();

        String chave = nome + " — " + cpfDono; // Chave única para o animal
        animais.put(chave, new Animal(nome, peso, altura, cpfDono));
        animais.get(cpfDono).consultarAnimal();
    }

    @Override
    public void consultar(Scanner leia) {
        System.out.println("Insira o CPF do dono do animal a ser consultado: ");
        String cpfDono = leia.nextLine();
        System.out.println("Insira o nome do animal a ser consultado: ");
        String nome = leia.nextLine();
        String chave = nome + " — " + cpfDono;

        if (animais.containsKey(chave)) {
            animais.get(chave).consultarAnimal();
        } else {
            System.out.println("Animal não encontrado.");
        }
    }

    @Override
    public void alterar(Scanner leia) {
        // Lógica para alterar os dados de um animal
    }

    @Override
    public void remover(Scanner leia) {
        // Lógica para remover um animal
    }
}
