/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

/**
 *
 * @author guest-febcik
 */
public class Cliente {
    //Atributos
    private String nome;
    private String telefone;
    private String email;
    private String rg;
    private String cpf;
    
    //Construtores 
    public Cliente(String nome, String telefone, String email, String rg, String cpf) {
        this.nome = nome;
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
    
}
