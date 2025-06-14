package petshop.services;

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import petshop.model.Animal;
import petshop.util.ValidadorEntrada;

/**
 * Classe responsável por gerenciar as operações relacionadas aos animais do petshop.
 * Implementa a interface Service para fornecer funcionalidades de cadastro, consulta,
 * alteração, remoção e listagem de animais.
 */
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
        System.out.println("Gostaria de cadastrar outro animal? (S/N)");
        resposta = leia.nextLine().trim().toUpperCase();

        if (resposta.equals("S")) {
            cadastrar(leia);
        } else {
            System.out.println("Cadastro finalizado.");
        }
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

        Animal animalSelecionado = selecionarAnimalPorNome(leia, nome);

        if (animalSelecionado != null) {
            System.out.println("Dados do animal selecionado:");
            System.out.println(animalSelecionado.toStringDetalhado());
        } else {
            System.out.println("Nenhum animal encontrado com o nome: " + nome);
        } 
    }

    /**
     * Método para alterar os dados de um animal no sistema.
     * Solicita ao usuário o nome do animal e permite que ele escolha quais dados deseja alterar.
     * As opções incluem peso, altura, nome, CPF do dono, ou todos os dados.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void alterar(Scanner leia) {
        System.out.println("Nome do animal a ser alterado: ");
        String nome = leia.nextLine();

        Animal animalSelecionado = selecionarAnimalPorNome(leia, nome);

        if (animalSelecionado == null) {
            System.out.println("Nenhum animal encontrado com o nome: " + nome);
            return;
        }

        System.out.println("Dados atuais do animal:");
        System.out.println(animalSelecionado.toStringDetalhado());

        System.out.println("Quais dados do animal você deseja alterar?");
        System.out.println("1 — Peso");
        System.out.println("2 — Altura");
        System.out.println("3 — Nome");
        System.out.println("4 — Dono (CPF)");
        System.out.println("5 — Nome do animal e dono (CPF)");
        System.out.println("6 — Todos os dados");

        int opcao = ValidadorEntrada.lerInteiroValido(leia, 1, 6);
        System.out.println("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1:
                float novoPeso = ValidadorEntrada.lerFloatPositivo(leia, "Insira o novo peso do animal: ");
                animalSelecionado.setPeso(novoPeso);
                break;
            case 2:
                float novaAltura = ValidadorEntrada.lerFloatPositivo(leia, "Insira a nova altura do animal: ");
                animalSelecionado.setAltura(novaAltura);
                break;
            case 3:
                System.out.println("Insira o novo nome do animal: ");
                String novoNome = leia.nextLine();
                String cpfDono = animalSelecionado.getCpfDono();
                animais.remove(animalSelecionado.getNome() + " — " + cpfDono);
                animalSelecionado.setNome(novoNome);
                animais.put(novoNome + " — " + cpfDono, animalSelecionado);
                break;
            case 4:
                System.out.println("Insira o CPF do novo dono: ");
                String novoCpfDono = leia.nextLine();
                String nomeAnimal = animalSelecionado.getNome();
                animais.remove(nomeAnimal + " — " + animalSelecionado.getCpfDono());
                animalSelecionado.setCpfDono(novoCpfDono);
                animais.put(nomeAnimal + " — " + novoCpfDono, animalSelecionado);
                break;
            case 5:
                System.out.println("Insira o novo nome do animal: ");
                String nomeAlterado = leia.nextLine();
                System.out.println("Insira o CPF do novo dono: ");
                String cpfAlterado = leia.nextLine();
                animais.remove(animalSelecionado.getNome() + " — " + animalSelecionado.getCpfDono());
                animalSelecionado.setNome(nomeAlterado);
                animalSelecionado.setCpfDono(cpfAlterado);
                animais.put(nomeAlterado + " — " + cpfAlterado, animalSelecionado);
                break;
            case 6:
                cadastrar(leia);
                return;
        }
    }

    /**
     * Método para remover um animal do sistema.
     * Solicita ao usuário o nome do animal e remove o animal correspondente.
     * Se não encontrar o animal, informa ao usuário.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void remover(Scanner leia) {
        // Método para remover um animal do sistema
        System.out.println("Nome do animal a ser removido: ");
        String nome = leia.nextLine();

        Animal animalSelecionado = selecionarAnimalPorNome(leia, nome);

        if (animalSelecionado == null) {
            System.out.println("Nenhum animal encontrado com o nome: " + nome);
            return;
        }

        String chave = animalSelecionado.getNome() + " — " + animalSelecionado.getCpfDono();
        animais.remove(chave);
        System.out.println("Animal removido com sucesso!");
    }

    @Override
    public void listar(Scanner leia) {
        // Método para listar todos os animais cadastrados
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
            return;
        } else {
            System.out.println("\n=== RELATÓRIO DE ANIMAIS ===");
            System.out.println("Total de animais cadastrados: " + animais.size());
            int contador = 1;
            for (Animal animal : animais.values()) {
                System.out.println(contador + ". Animal: ");
                System.out.println(animal.toStringDetalhado());
                System.out.println("--------------------------------");
                contador++;
            }
        }
    }

    /**
     * Método auxiliar para selecionar um animal pelo nome.
     * Se houver múltiplos animais com o mesmo nome, solicita ao usuário que escolha qual animal deseja ver os detalhes.
     *
     * @param leia Scanner para ler a entrada do usuário
     * @param nome Nome do animal a ser selecionado
     * @return O animal selecionado ou null se não encontrado
     */
    private Animal selecionarAnimalPorNome(Scanner leia, String nome) {
        List<Animal> animaisEncontrados = new ArrayList<>();
    
        for (Map.Entry<String, Animal> entry : animais.entrySet()) {
            if (entry.getValue().getNome().equalsIgnoreCase(nome)) {
                animaisEncontrados.add(entry.getValue());
            }
        }

        if (animaisEncontrados.isEmpty()) {
            System.out.println("Nenhum animal encontrado com o nome: " + nome);
            return null;
        }
        
        if (animaisEncontrados.size() == 1) {
            return animaisEncontrados.get(0);
        }

        System.out.println("Foram encontrados múltiplos animais com esse nome: " + nome);

        for (int i = 0; i < animaisEncontrados.size(); i++) {
            System.out.println((i + 1) + " — Dono CPF: " + animaisEncontrados.get(i).getCpfDono());
        }

        System.out.println("Escolha o número correspondente: ");
        int escolhaUsuario = ValidadorEntrada.lerInteiroValido(leia, 1, animaisEncontrados.size());

        return animaisEncontrados.get(escolhaUsuario - 1);
    }
}