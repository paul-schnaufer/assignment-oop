/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop.modelo;

/**
 * Classe que representa um Cliente no sistema de Petshop.
 * Herda da classe Pessoa e contém informações específicas do cliente, como telefone, email, RG e CPF.
 */
public class Cliente extends Pessoa {
    //Atributos
    private String telefone;
    private String email;
    private String rg;
    private String cpf;
    
    //Construtores 
    public Cliente(String nome, String telefone, String email, String rg, String cpf) {
        super(nome);
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
    }
    
    
    //Métodos
    public void consultarCliente(){
        System.out.println("\n----------Cliente----------\nNome: " + this.nome + "\nTelefone: " + this.telefone + "\nE-mail: " + this.email + "\nRG: " + this.rg + "\nCPF: " + this.cpf);
        
        
    }
    
    public void alterarCadastro(String nome, String telefone, String email, String rg, String cpf){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
