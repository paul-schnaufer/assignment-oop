package petshop.model;

/**
 * Classe que representa um Cliente no sistema de Petshop.
 * Herda da classe Pessoa e contém informações adicionais como telefone, email, RG e CPF.
 */
public class Cliente extends Pessoa {
    // Atributos
    private String telefone;
    private String email;
    private String rg;
    private String cpf;

    // Construtores
    /**
     * Construtor da classe Cliente.
     * 
     * @param nome Nome do cliente.
     * @param telefone Telefone do cliente.
     * @param email E-mail do cliente.
     * @param rg Registro Geral (RG) do cliente.
     * @param cpf Cadastro de Pessoa Física (CPF) do cliente.
     */
    public Cliente(String nome, String telefone, String email, String rg, String cpf) {
        super(nome, cpf); // Usando CPF como ID
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
    }
    
    // Métodos
    /**
     * Método que altera os dados do cliente.
     * @param nome Nome do cliente.
     * @param telefone Telefone do cliente.
     * @param email E-mail do cliente.
     * @param rg Registro Geral (RG) do cliente.
     * @param cpf Cadastro de Pessoa Física (CPF) do cliente.
     */
    public void alterarCadastro(String nome, String telefone, String email, String rg, String cpf){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
    }

    /**
    * Método que retorna uma representação detalhada do cliente.
    * @return String com detalhes do cliente.
    */
    public String toStringDetalhado() {
        return "Nome: " + nome + 
               "\nTelefone: " + telefone + 
               "\nE-mail: " + email + 
               "\nRG: " + rg +
               "\nCPF: " + cpf;
    }

    /**
     * Método que retorna uma representação em string do cliente.
     * @return String com o nome do cliente.
     */
    @Override
    public String getTipo() {
        return "Cliente";
    }

    // Getters e Setters
    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getRg() { return rg; }

    public void setRg(String rg) { this.rg = rg; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }
}
