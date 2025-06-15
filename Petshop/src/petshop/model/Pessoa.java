package petshop.model;

/**
 * Classe abstrata Pessoa que representa uma entidade genérica com um nome.
 * Esta classe pode ser estendida por outras classes como Cliente e Funcionario.
 */
public abstract class Pessoa {
    protected String nome;
    protected String id;

    public Pessoa(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public abstract String getTipo();
}