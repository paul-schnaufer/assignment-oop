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
        consultar(leia);

        System.out.println("Quais dados do animal você deseja alterar?");
        System.out.println("1 — Peso");
        System.out.println("2 — Altura");
        System.out.println("3 — Nome");
        System.out.println("4 — CPF do dono");
        System.out.println("5 — Nome do animal e CPF do dono");
        System.out.println("6 — Todos os dados");

        int opcao = ValidadorEntrada.lerInteiroValido(leia, 1, 6);
        System.out.println("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1:
                float novoPeso = ValidadorEntrada.lerFloatPositivo(leia, "Insira o novo peso do animal: ");
                setPeso(novoPeso);
                break;
            case 2:
                alterarAltura(leia);
                break;
            case 3:
                alterarNome(leia);
                break;
            case 4:
                alterarCpfDono(leia);
                break;
            case 5:
                alterarNomeECPF(leia);
                break;
            case 6:
                alterarTodosDados(leia);
                break;
            default:
                System.out.println("Opção inválida.");
        }

        System.out.println("Insira o nome do animal a ser alterado: ");
        String nome = leia.nextLine();
        System.out.println("Insira o CPF do dono: ");
        String cpfDono = leia.nextLine();
        String chave = nome + " — " + cpfDono;

        if (animais.containsKey(chave)) {
            Animal animal = animais.get(chave);
            System.out.println("Dados atuais do animal:");
            System.out.println(animal.toStringDetalhado());

            System.out.println("Insira o novo nome do animal: ");
            String novoNome = leia.nextLine();
            float novoPeso = ValidadorEntrada.lerFloatPositivo(leia, "Insira o novo peso do animal: ");
            float novaAltura = ValidadorEntrada.lerFloatPositivo(leia, "Insira a nova altura do animal: ");

            // Remove o animal antigo e cadastra um novo com a chave atualizada
            animais.remove(chave);
            String novaChave = novoNome + " — " + cpfDono;
            animais.put(novaChave, new Animal(novoNome, novoPeso, novaAltura, cpfDono));

            System.out.println("Animal alterado com sucesso!");
        } else {
            System.out.println("Animal não encontrado.");
        }
    }

    @Override
    public void remover(Scanner leia) {
        // Lógica para remover um animal
    }

    @Override
    public void listar(Scanner leia) {
        // Método para listar todos os animais cadastrados
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            System.out.println("Animais cadastrados:");
            for (Animal animal : animais.values()) {
                System.out.println(animal.toStringDetalhado());
            }
        }
    }

    public void capturaInformacoes() {
        // Método para capturar informações de um animal
        return;
    }


}   

