package petshop.service;

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import petshop.modelo.Animal;
import petshop.util.ValidadorEntrada;

public class AnimalService implements Service {
    private Map<String, Animal> animais;
    public AnimalService(Map<String, Animal> animais) {
        this.animais = animais;
    }

    /**
     * Método para cadastrar um animal no sistema.
     * Solicita ao usuário as informações necessárias e cria um novo objeto Animal.
     * A chave do animal é composta pelo nome e CPF do dono.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void cadastrar(Scanner leia) {
        System.out.println("Insira o nome do animal: ");
        String nome = leia.nextLine();
        float peso = ValidadorEntrada.lerFloatPositivo(leia, "Insira o peso do animal: ");
        float altura = ValidadorEntrada.lerFloatPositivo(leia, "Insira a altura do animal: ");
        System.out.println("Insira o CPF do dono: ");
        String cpfDono = leia.nextLine();

        String chave = nome + " — " + cpfDono; // Chave única para o animal
        animais.put(chave, new Animal(nome, peso, altura, cpfDono));

        System.out.println("Dados do animal cadastrado:");
        System.out.println(animais.get(chave).toStringDetalhado());

        System.out.println("Os dados estão corretos? (S/N)");
        String resposta = leia.nextLine().trim().toUpperCase();

        if (!resposta.equals("S")) {
            System.out.println("Cadastro cancelado.");
            animais.remove(chave);
            return;
        }

        System.out.println("Animal cadastrado com sucesso!");
    }

    /**
     * Método para consultar um animal no sistema.
     * Solicita ao usuário o nome do animal e busca por animais que começam com esse nome.
     * Se encontrar múltiplos animais, solicita ao usuário que escolha qual animal deseja ver os detalhes.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void consultar(Scanner leia) {
        System.out.println("Nome do animal a ser consultado: ");
        String nome = leia.nextLine();

        List<Animal> animaisEncontrados = new ArrayList<>();

        // Verifica se o nome do animal começa com o nome fornecido
        for (Map.Entry<String, Animal> entry : animais.entrySet()) {
            if (entry.getKey().startsWith(nome)) {
                animaisEncontrados.add(entry.getValue());
            }
        }

        if (animaisEncontrados.isEmpty()) {
            System.out.println("Nenhum animal encontrado com o nome: " + nome);
        } else if (animaisEncontrados.size() == 1) {
            Animal animalEncontrado = animaisEncontrados.get(0);
            System.out.println(animalEncontrado.toStringDetalhado());
        } else {
            System.out.println("Foram encontrados múltiplos animais com esse nome: " + nome);

            for (int i = 0; i < animaisEncontrados.size(); i++) {
                System.out.println((i + 1) + " — Dono CPF: " + animaisEncontrados.get(i).getCpfDono());
            }

            System.out.println("Escolha o número correspondente: ");
            int escolhaUsuario = ValidadorEntrada.lerInteiroValido(leia, 1, animaisEncontrados.size());

            // Exibe o animal escolhido
            System.out.println(animaisEncontrados.get(escolhaUsuario - 1).toStringDetalhado());
        }
}

    @Override
    public void alterar(Scanner leia) {
        // Lógica para alterar os dados de um animal
        // Quando alterar os dados referentes à chave,
        // deve-se remover o animal antigo e cadastrar um novo com a chave atualizada
    }

    @Override
    public void remover(Scanner leia) {
        // Lógica para remover um animal
    }

    public void capturaInformacoes() {
        // Método para capturar informações de um animal
        return;
    }
}   

