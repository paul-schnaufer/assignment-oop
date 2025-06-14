package petshop.service;

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import petshop.modelo.Animal;
import petshop.util.VerificaEntrada;

public class AnimalService implements Service {
    private Map<String, Animal> animais;
    public AnimalService(Map<String, Animal> animais) {
        this.animais = animais;
    }

    @Override
    public void cadastrar(Scanner leia) {
        System.out.println("Insira o nome do animal: ");
        String nome = leia.nextLine();
        float peso = VerificaEntrada.lerFloatPositivo(leia, "Insira o peso do animal: ");
        float altura = VerificaEntrada.lerFloatPositivo(leia, "Insira a altura do animal: ");
        System.out.println("Insira o CPF do dono: ");
        String cpfDono = leia.nextLine();

        String chave = nome + " — " + cpfDono; // Chave única para o animal
        animais.put(chave, new Animal(nome, peso, altura, cpfDono));
        System.out.println("Animal cadastrado com sucesso!");
    }

    @Override
    public void consultar(Scanner leia) {
        System.out.println("Nome do animal a ser consultado: ");
        String nome = leia.nextLine();

        List<Animal> encontrados = new ArrayList<>();

        // Verifica se o nome do animal começa com o nome fornecido
        for (Map.Entry<String, Animal> entry : animais.entrySet()) {
            if (entry.getKey().startsWith(nome)) {
                encontrados.add(entry.getValue());
            }
        }

        // Se nenhum animal for encontrado, exibe mensagem apropriada
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum animal encontrado com o nome: " + nome);
        } else if (encontrados.size() == 1) {
            Animal animalEncontrado = encontrados.get(0);
            System.out.println(animalEncontrado.toStringDetalhado());
        } else {
            System.out.println("Foram encontrados múltiplos animais com esse nome: " + nome);
            for (int i = 0; i < encontrados.size(); i++) {
                System.out.println((i + 1) + " — Dono CPF: " + encontrados.get(i).getCpfDono());
            }

            // Solicita ao usuário que escolha um animal da lista
            int escolha;
            System.out.println("Escolha o número correspondente: ");
            escolha = VerificaEntrada.lerInteiroValido(leia, 1, encontrados.size());

            // Exibe os detalhes do animal escolhido
            Animal animalEscolhido = encontrados.get(escolha - 1);
            System.out.println(animalEscolhido.toStringDetalhado());
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

    public void capturaInformacoes() {
        // Método para capturar informações de um animal
        return null
    }
}   

