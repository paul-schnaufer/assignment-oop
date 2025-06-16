package petshop.service;

import java.util.List;

import petshop.model.Animal;
import petshop.ui.AnimalConsoleUI;
import petshop.repository.AnimalRepository;
import petshop.repository.ClienteRepository;

/**
 * Classe responsável por gerenciar as operações relacionadas aos animais do petshop.
 * Esta classe implementa a interface Service e fornece métodos para cadastrar, consultar,
 * alterar, remover e listar animais.
 */
public class AnimalService implements Service {
    private AnimalConsoleUI ui;
    private AnimalRepository animalRepository;
    private ClienteRepository clienteRepository;

    /**
     * Construtor da classe AnimalService.
     * Inicializa a interface de usuário e o repositório de animais.
     * 
     * @param ui A interface de usuário para interações com o usuário
     * @param animalRepository O repositório onde os animais serão armazenados
     */
    public AnimalService(
        AnimalConsoleUI ui,
        AnimalRepository animalRepository, 
        ClienteRepository clienteRepository
        ) {
        this.ui = ui;
        this.animalRepository = animalRepository;
        this.clienteRepository = clienteRepository;
    }

    /**
     * Método para cadastrar um novo animal no sistema.
     * Solicita ao usuário os dados do animal,
     * verifica se já existe um animal com o mesmo nome e CPF do dono,
     * e salva o novo animal no repositório.
     * Se os dados estiverem corretos,
     * confirma o cadastro e pergunta se deseja cadastrar outro animal.
     */
    @Override
    public void cadastrar() {
        ui.mostrarCabecalho("Cadastro de Animal");
        String nome = ui.solicitarNomeAnimal();
        String cpfDono = ui.solicitarCpfDonoAnimal();

        if (!clienteRepository.exists(cpfDono)) {
            ui.mostrarMensagem("CPF do dono não cadastrado: " + cpfDono);
            return;
        }

        String chave = gerarChave(nome, cpfDono);

        if (animalRepository.exists(chave)) {
            ui.mostrarMensagem(
                "Animal já cadastrado com o nome: " + nome + " e CPF do dono: " + cpfDono
                );
            return;
        }

        float peso = ui.solicitarPesoAnimal();
        float altura = ui.solicitarAlturaAnimal();

        Animal novoAnimal = new Animal (nome, peso, altura, cpfDono);

        ui.mostrarMensagem("Dados do animal cadastrado:");
        ui.mostrarDetalhesAnimal(novoAnimal);

        if (!ui.receberConfirmacao("Os dados estão corretos? ")) {
            ui.mostrarMensagem("Cadastro cancelado.");
            return;
        } else {
            animalRepository.save(chave, novoAnimal);
        }

        ui.mostrarMensagem("Animal cadastrado com sucesso.");
        
        if (!ui.receberConfirmacao("Gostaria de cadastrar outro animal? ")) {
            ui.mostrarMensagem("Cadastro finalizado.");
            return;
        } else {
            cadastrar();
        }
    }

    /**
     * Método para consultar um animal no sistema.
     * Solicita ao usuário o nome do animal e exibe os detalhes do animal correspondente.
     * Se houver múltiplos animais com o mesmo nome,
     * permite que o usuário escolha qual animal deseja ver os detalhes.
     */
    @Override
    public void consultar() {
        String nome = ui.solicitarNomeAnimal();

        Animal animalSelecionado = selecionarAnimalPorNome(nome);

        if (animalSelecionado != null) {
            ui.mostrarMensagem("Dados do animal selecionado:");
            ui.mostrarDetalhesAnimal(animalSelecionado);
        } else {
            ui.mostrarMensagem("Nenhum animal encontrado com o nome: " + nome);
        }
    }

    /**
     * Método para alterar os dados de um animal no sistema.
     * Solicita ao usuário o nome do animal, seleciona o animal correspondente,
     * e permite que o usuário escolha quais dados deseja alterar.
     * Se os dados estiverem corretos, atualiza o animal no repositório.
     */
    @Override
    public void alterar() {
        String nome = ui.solicitarNomeAnimal();

        Animal animalSelecionado = selecionarAnimalPorNome(nome);

        if (animalSelecionado == null) {
            ui.mostrarMensagem("Nenhum animal encontrado com o nome: " + nome);
            return;
        }

        ui.mostrarMensagem("Dados atuais do animal:");
        ui.mostrarDetalhesAnimal(animalSelecionado);

        // Exibe o menu de opções de alteração e captura a opção escolhida pelo usuário
        ui.menuAlterarAnimal();
        int opcao = ui.capturarInteiro(1, 6);
        ui.mostrarMensagem("Você escolheu a opção: " + opcao);

        switch (opcao) {
            case 1 -> {
                float novoPeso = ui.solicitarPesoAnimal();
                animalSelecionado.setPeso(novoPeso);
                ui.mostrarMensagem("Peso do animal alterado com sucesso!");
            }
            case 2 -> {
                float novaAltura = ui.solicitarAlturaAnimal();
                animalSelecionado.setAltura(novaAltura);
                ui.mostrarMensagem("Altura do animal alterada com sucesso!");
            }
            case 3 -> {
                String novoNome = ui.solicitarNomeAnimal();
                String cpfDonoAtual = animalSelecionado.getCpfDono();
                String chaveAntiga = gerarChave(animalSelecionado.getNome(), cpfDonoAtual);
                String novaChave = gerarChave(novoNome, cpfDonoAtual);
                
                if (!chaveAntiga.equals(novaChave) && animalRepository.exists(novaChave)) {
                    ui.mostrarMensagem(
                        "Já existe um animal cadastrado com o nome: " + novoNome
                        + " e CPF do dono: " + cpfDonoAtual
                        );
                    return;
                }   
                
                animalRepository.delete(chaveAntiga);
                animalSelecionado.setNome(novoNome);
                animalRepository.save(gerarChave(
                    novoNome, animalSelecionado.getCpfDono()), animalSelecionado
                    );
                ui.mostrarMensagem("Nome do animal alterado com sucesso!");
            }
            case 4 -> {
                String novoCpfDono = ui.solicitarCpfDonoAnimal();
                String nomeAnimal = animalSelecionado.getNome();
                String chaveAntiga = gerarChave(nomeAnimal, animalSelecionado.getCpfDono());
                String novaChave = gerarChave(nomeAnimal, novoCpfDono);

                if (!chaveAntiga.equals(novaChave) && animalRepository.exists(novaChave)) {
                    ui.mostrarMensagem(
                        "Já existe um animal cadastrado com o nome: " + nomeAnimal
                        + " e CPF do dono: " + novoCpfDono
                        );
                    return;
                }

                animalRepository.delete(chaveAntiga);
                animalSelecionado.setCpfDono(novoCpfDono);
                animalRepository.save(gerarChave(nomeAnimal, novoCpfDono), animalSelecionado);
                ui.mostrarMensagem("CPF do dono do animal alterado com sucesso!");
            }
            case 5 -> {
                String nomeAlterado = ui.solicitarNomeAnimal();
                String cpfAlterado = ui.solicitarCpfDonoAnimal();
                String chaveAntiga = gerarChave(
                    animalSelecionado.getNome(), animalSelecionado.getCpfDono()
                    );
                String novaChave = gerarChave(nomeAlterado, cpfAlterado);

                if (!chaveAntiga.equals(novaChave) && animalRepository.exists(novaChave)) {
                    ui.mostrarMensagem(
                        "Já existe um animal cadastrado com o nome: " + nomeAlterado
                        + " e CPF do dono: " + cpfAlterado
                        );
                    return;
                }

                animalRepository.delete(chaveAntiga);
                animalSelecionado.setNome(nomeAlterado);
                animalSelecionado.setCpfDono(cpfAlterado);
                animalRepository.save(gerarChave(nomeAlterado, cpfAlterado), animalSelecionado);
                ui.mostrarMensagem("Nome do animal e Dono (CPF) alterados com sucesso!");
            }
            case 6 -> {
                String chaveAntiga = gerarChave(
                    animalSelecionado.getNome(), animalSelecionado.getCpfDono()
                    );

                ui.mostrarMensagem("Você escolheu alterar todos os dados do animal.");
                animalRepository.delete(chaveAntiga);
                ui.mostrarMensagem("Por favor, insira os novos dados do animal.");


                String nomeNovo = ui.solicitarNomeAnimal();
                Float peso = ui.solicitarPesoAnimal();
                Float altura = ui.solicitarAlturaAnimal();
                String cpfDono = ui.solicitarCpfDonoAnimal();

                String chave = gerarChave(nomeNovo, cpfDono);

                if (animalRepository.exists(chave)) {
                    ui.mostrarMensagem(
                        "Já existe um animal cadastrado com o nome: " + nomeNovo +
                        " e CPF do dono: " + cpfDono
                        );
                    return;
                }

                Animal dadosAnimal = new Animal(nomeNovo, peso, altura, cpfDono);
                animalRepository.save(chave, dadosAnimal);
                ui.mostrarMensagem("Dados do animal alterados com sucesso.");
                return;
            }
        }
    }

    /**
     * Método para remover um animal do sistema.
     * Solicita ao usuário o nome do animal, seleciona o animal correspondente,
     * e confirma a remoção antes de excluir o animal do repositório.
     */
    @Override
    public void remover() {
        // Método para remover um animal do sistema
        String nome = ui.solicitarNomeAnimal();
        Animal animalSelecionado = selecionarAnimalPorNome(nome);

        if (animalSelecionado == null) {
            ui.mostrarMensagem("Nenhum animal encontrado com o nome: " + nome);
            return;
        }

        String chave = gerarChave(nome, animalSelecionado.getCpfDono());

        boolean confirmacao = ui.receberConfirmacao(
            "Tem certeza que deseja remover o animal: " +
            nome + "?"
            );

        if (confirmacao) {
            animalRepository.delete(chave);
            ui.mostrarMensagem("Animal removido com sucesso.");
        } else {
            ui.mostrarMensagem("Remoção cancelada.");
        }
    }

    /**
     * Método para listar todos os animais cadastrados no sistema.
     * Exibe o total de animais cadastrados e os detalhes de cada animal.
     * Se não houver animais cadastrados, informa ao usuário.
     */
    @Override
    public void listar() {
        if (animalRepository.isEmpty()) {
            ui.mostrarMensagem("Nenhum animal cadastrado.");
            return;
        } else {
            ui.mostrarCabecalho(("Relatório de Animais"));
            ui.mostrarMensagem("Total de animais cadastrados: " + animalRepository.size());
            int contador = 1;

            for (Animal animal : animalRepository.getAll()) {
                ui.mostrarMensagem("Animal " + contador + ":");
                ui.mostrarDetalhesAnimal(animal);
                ui.mostrarMensagem("-----------------------------------");
                contador++;
            }
        }
    }

    /**
     * Método auxiliar para selecionar um animal pelo nome.
     * Se houver múltiplos animais com o mesmo nome,
     * permite que o usuário escolha qual animal deseja ver os detalhes.
     * 
     * @param nome O nome do animal a ser selecionado
     * @return O objeto Animal correspondente ao nome, ou null se não encontrado
     */
    private Animal selecionarAnimalPorNome(String nome) {
        List<String> chavesAnimaisEncontrados = animalRepository.acharChavesPeloNome(nome);

        if (chavesAnimaisEncontrados.isEmpty()) {
            ui.mostrarMensagem("Nenhum animal encontrado com o nome: " + nome);
            return null;
        } else if (chavesAnimaisEncontrados.size() == 1) {
            return animalRepository.getByKey(chavesAnimaisEncontrados.get(0));
        }

        ui.mostrarMensagem("Foram encontrados múltiplos animais com esse nome: " + nome);

        // Exibe os animais encontrados para o usuário
        int numeroAnimalEscolhido = ui.escolherAnimal(chavesAnimaisEncontrados);

        // Obtém a chave do animal escolhido
        String animalEscolhido = chavesAnimaisEncontrados.get(numeroAnimalEscolhido - 1);

        return animalRepository.getByKey(animalEscolhido);
    }

    /**
     * Método auxiliar para gerar uma chave única para o animal com base no nome e no CPF do dono.
     * 
     * @param nome O nome do animal
     * @param cpfDono O CPF do dono do animal
     * @return A chave gerada no formato "nome — cpfDono"
     */
    private String gerarChave(String nome, String cpfDono) {
        return nome + " — " + cpfDono;
    }
}