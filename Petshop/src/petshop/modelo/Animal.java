package petshop.modelo;

/**
 * Classe que representa um Animal no sistema de Petshop.
 * Contém atributos como nome, peso e altura, além de métodos para acessar e modificar esses atributos.
 */
public class Animal {
    //Atributos
    private String nome;
    private float peso;
    private float altura;
    private String cpfDono;

    public Animal(String nome, float peso, float altura, String cpfDono) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.cpfDono = cpfDono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getCpfDono() {
        return cpfDono;
    }

    public void setCpfDono(String cpfDono) {
        this.cpfDono = cpfDono;
    }
}
