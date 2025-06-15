package petshop.model;

/**
 * Classe que representa um Animal no sistema de Petshop.
 * Contém informações sobre o animal, como nome, peso, altura e CPF do dono.
 */
public class Animal {
    //Atributos
    private String nome;
    private float peso; // em quilogramas
    private float altura; // em centímetros
    private String cpfDono;

    //Construtores
    /**
     * Construtor da classe Animal.
     * 
     * @param nome Nome do animal.
     * @param peso Peso do animal em quilogramas.
     * @param altura Altura do animal em centímetros.
     * @param cpfDono CPF do dono do animal.
     */
    public Animal(String nome, float peso, float altura, String cpfDono) {
        this.nome = nome;
        this.peso = peso; 
        this.altura = altura; 
        this.cpfDono = cpfDono;
    }

    //Métodos
    /**
     * Retorna uma representação em string detalhada do animal.
     * 
     * @return String com detalhes do animal
     */
    public String toStringDetalhado() {
        return "Nome: " + nome + 
               "\nPeso: " + peso + " kg" + 
               "\nAltura: " + altura + " cm" + 
               "\nCPF do Dono: " + cpfDono;
    }

    // Getters e Setters
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public float getPeso() { return peso; }

    public void setPeso(float peso) { this.peso = peso; }

    public float getAltura() { return altura; }

    public void setAltura(float altura) { this.altura = altura; }

    public String getCpfDono() { return cpfDono; }

    public void setCpfDono(String cpfDono) { this.cpfDono = cpfDono; }
}