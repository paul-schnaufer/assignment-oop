package petshop.model;

/**
 * Classe abstrata que representa uma Pessoa no sistema de Petshop.
 * Contém informações comuns a todas as pessoas, como nome e ID.
 */
public abstract class Pessoa {
    // Atributos
    protected String nome;
    protected String id;

    /**
     * Construtor da classe Pessoa.
     * 
     * @param nome Nome da pessoa.
     * @param id Identificador único da pessoa (pode ser CPF, matrícula, etc.).
     */
    public Pessoa(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    // Métodos
    /**
     * Método abstrato que deve ser implementado pelas subclasses para retornar o tipo de pessoa.
     * 
     * @return String representando o tipo de pessoa (por exemplo, "Cliente", "Funcionário").
     */
    public abstract String getTipo();

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}