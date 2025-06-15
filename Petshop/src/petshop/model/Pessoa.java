package petshop.model;

/**
 * Classe abstrata que representa uma Pessoa no sistema de Petshop.
 * Contém informações comuns a todas as pessoas, como nome e ID.
 */
public abstract class Pessoa {
    // Atributos
    protected String nome;

    /**
     * Construtor da classe Pessoa.
     * 
     * @param nome Nome da pessoa.
     */
    public Pessoa(String nome) {
        this.nome = nome;
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
}