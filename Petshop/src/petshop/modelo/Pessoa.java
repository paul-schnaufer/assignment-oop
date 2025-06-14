package petshop.modelo;

/**
 * Classe abstrata Pessoa que representa uma entidade genérica com um nome.
 * Esta classe pode ser estendida por outras classes como Cliente e Funcionario.
 */
public abstract class Pessoa {
    protected String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}