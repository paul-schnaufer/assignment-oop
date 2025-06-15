package petshop.model;

/**
 * Classe que representa um Atendimento no sistema de Petshop.
 * Contém informações sobre o atendimento, como código, data, cliente, animal e funcionário responsável.
 */
public class Atendimento {
    //Atributos
    private String codigo;
    private String data;
    private Cliente cliente;
    private Animal animal;
    private Funcionario funcionario;

    //Construtores
    /**
     * Construtor da classe Atendimento.
     * 
     * @param codigo Código do atendimento.
     * @param data Data do atendimento.
     * @param cliente Cliente associado ao atendimento.
     * @param animal Animal associado ao atendimento.
     * @param funcionario Funcionário responsável pelo atendimento.
     */
    public Atendimento(
        String codigo, String data, Cliente cliente, Animal animal, Funcionario funcionario
        ) {
        this.codigo = codigo;
        this.data = data;
        this.cliente = cliente;
        this.animal = animal;
        this.funcionario = funcionario;
    }

    //Métodos
    /**
     * Retorna uma representação em string detalhada do atendimento.
     * 
     * @return String com detalhes do atendimento
     */
    public String toStringDetalhado() {
        String detalhesCliente = cliente.toStringDetalhado();
        String detalhesAnimal = animal.toStringDetalhado();
        String detalhesFuncionario = funcionario.toStringDetalhado();
    
        return "Codigo: " + codigo + 
               "\nData: " + data +
               "\nCliente: " + detalhesCliente + 
               "\nAnimal: " + detalhesAnimal +
               "\nFuncionario: " + detalhesFuncionario;
    }

    // Getters e Setters
    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Animal getAnimal() { return animal; }

    public void setAnimal(Animal animal) { this.animal = animal; }

    public Funcionario getFuncionario() { return funcionario; }

    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
}
