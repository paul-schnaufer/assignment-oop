package petshop.services;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import petshop.model.Animal;
import petshop.model.Cliente;
import petshop.util.ValidadorEntrada;
import petshop.ui.AnimalConsoleUI;
import petshop.repository.AnimalRepository;

/**
 * Classe responsável por gerenciar as operações relacionadas aos animais do petshop.
 * Implementa a interface Service para fornecer funcionalidades de cadastro, consulta,
 * alteração, remoção e listagem de animais.
 */
public class AnimalService implements Service {
    private AnimalConsoleUI ui;
    private AnimalRepository animalRepository;

    public AnimalService(AnimalConsoleUI ui, AnimalRepository animalRepository) {
        this.ui = ui;
        this.animalRepository = animalRepository;
    }

    /**
     * Método para cadastrar um animal no sistema.
     * Solicita ao usuário as informações necessárias e cria um novo objeto Animal.
     * Caso o animal já exista, informa ao usuário.
     * A chave do animal é composta pelo nome e CPF do dono.
     *
     * @param leia Scanner para ler a entrada do usuário
     */
    @Override
    public void cadastrar() {
        ui.mostrarCabecalho("Cadastro de Animal");
        String nome = ui.solicitarNomeAnimal();
        float peso = ui.solicitarPesoAnimal();
        float altura = ui.solicitarAlturaAnimal();
        String cpfDono = ui.solicitarCpfDonoAnimal();

        String chave = gerarChave(nome, cpfDono);

        if (animalRepository.exists(chave)) {
            System.out.println("Animal já cadastrado com o nome: " + nome + " e CPF do dono: " + cpfDono);
            return;
        }

        Animal animal = new Animal (nome, peso, altura, cpfDono);
        animalRepository.save(chave, animal);

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
            case 1 -> {
                float novoPeso = ValidadorEntrada.lerFloatPositivo(leia, "Insira o novo peso do animal: ");
                animalSelecionado.setPeso(novoPeso);
            }
            case 2 -> {
                float novaAltura = ValidadorEntrada.lerFloatPositivo(leia, "Insira a nova altura do animal: ");
                animalSelecionado.setAltura(novaAltura);
            }
            case 3 -> {
                System.out.println("Insira o novo nome do animal: ");
                String novoNome = leia.nextLine();

                String chaveAntiga = gerarChave(animalSelecionado.getNome(), animalSelecionado.getCpfDono());
                animais.remove(chaveAntiga);

                animalSelecionado.setNome(novoNome);
                animais.put(animalSelecionado.getNome() + " — " + animalSelecionado.getCpfDono(), animalSelecionado);
            }
            case 4 -> {
                System.out.println("Insira o CPF do novo dono: ");
                String novoCpfDono = leia.nextLine();
                String nomeAnimal = animalSelecionado.getNome();
                animais.remove(nomeAnimal + " — " + animalSelecionado.getCpfDono());
                animalSelecionado.setCpfDono(novoCpfDono);
                animais.put(nomeAnimal + " — " + novoCpfDono, animalSelecionado);
            }
            case 5 -> {
                System.out.println("Insira o novo nome do animal: ");
                String nomeAlterado = leia.nextLine();
                System.out.println("Insira o CPF do novo dono: ");
                String cpfAlterado = leia.nextLine();
                animais.remove(animalSelecionado.getNome() + " — " + animalSelecionado.getCpfDono());
                animalSelecionado.setNome(nomeAlterado);
                animalSelecionado.setCpfDono(cpfAlterado);
                animais.put(nomeAlterado + " — " + cpfAlterado, animalSelecionado);
            }
            case 6 -> {
                String chave = gerarChave(animalSelecionado.getNome(), animalSelecionado.getCpfDono());
                System.out.println("Você escolheu alterar todos os dados do animal.");

                animais.remove(chave);

                System.out.println("Por favor, insira os novos dados do animal.");

                ArrayList<String> dadosAnimal = coletaDados(leia);
                insereDados(dadosAnimal, chave);

                System.out.println("Todos os dados do animal foram alterados com sucesso!");

                return;
            }
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

        String chave = gerarChave(nome, animalSelecionado.getCpfDono());
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
            System.out.println(SEPARADOR + " LISTA DE ANIMAIS " + SEPARADOR);
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
     * Se houver múltiplos animais com o mesmo nome,
     * solicita ao usuário que escolha qual animal deseja ver os detalhes.
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

    /**
     * Método auxiliar para coletar os dados do animal a ser cadastrado.
     * Solicita ao usuário o nome, peso, altura e CPF do dono do animal.
     * 
     * @return Uma lista contendo os dados do animal,
     * na ordem: nome, peso, altura e CPF do dono.
     * */
    public ArrayList<String> coletaDados(Scanner leia) {
        System.out.println("Nome do animal: ");
        String nome = leia.nextLine();
        float peso = ValidadorEntrada.lerFloatPositivo(leia, "Peso do animal: ");
        float altura = ValidadorEntrada.lerFloatPositivo(leia, "Altura do animal: ");
        String cpfDono = ValidadorEntrada.lerCpfValido(leia);

        return new ArrayList<String>() {{
            add(nome);
            add(String.valueOf(peso));
            add(String.valueOf(altura));
            add(cpfDono);
        }};
    }

    /**
     * Método para inserir dados de um animal diretamente no mapa de animais.
     * Este método é utilizado para popular o banco de dados em memória com dados iniciais.
     * @param dadosAnimal Lista contendo os dados do animal,
     * na ordem: nome, peso, altura e CPF do dono.
     */
    public void insereDados(ArrayList<String> dadosAnimal, String chave) {
        animais.put(chave, new Animal(
            dadosAnimal.get(0),
            Float.parseFloat(dadosAnimal.get(1)),
            Float.parseFloat(dadosAnimal.get(2)),
            dadosAnimal.get(3)));
    }

    private String gerarChave(String nome, String cpfDono) {
        return nome + " — " + cpfDono;
    }
}