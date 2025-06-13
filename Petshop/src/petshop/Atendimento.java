/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

/**
 *
 * @author guest-febcik
 */
public class Atendimento {
    //Atributos
    private int codigo;
    private String data;
    private Cliente cliente;
    private Animal animal;
    private Funcionario funcionario;

    public Atendimento() {
    }

    public Atendimento(int codigo, String data, Cliente cliente, Animal animal, Funcionario funcionario) {
        this.codigo = codigo;
        this.data = data;
        this.cliente = cliente;
        this.animal = animal;
        this.funcionario = funcionario;
    }
    

    
    
    
}
