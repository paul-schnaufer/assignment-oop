/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

/**
 *
 * @author guest-febcik
 */
public class Funcionario {
  //Atributos
  private String nome;
  private String matricula;
  private String qualificacao;
  private String descricaoFuncao;
  private float cargaHorariaSemanal;

  //Construtores
  public Funcionario(String nome, String matricula, String qualificacao, String descricaoFuncao, float cargaHorariaSemanal) {
    this.nome = nome;
    this.matricula = matricula;
    this.qualificacao = qualificacao;
    this.descricaoFuncao = descricaoFuncao;
    this.cargaHorariaSemanal = cargaHorariaSemanal;
  }
    
    //Métodos
  public void consultarFuncionario(){
    System.out.println("\n--------Funcionário--------\nNome: " 
      + this.nome + "\nMatrícula: " 
      + this.matricula + "\nQualificação: " 
      + this.qualificacao + "\nDescrição da Função Executada: " 
      + this.descricaoFuncao + "\nCarga Horária Semanal de Trabalho: " 
      + this.cargaHorariaSemanal + " horas");
    }
    
  public void alterarCadastro(String nome, String matricula, String qualificacao,
      String descricaoFuncao,float cargaHorariaSemanal){
    this.nome = nome;
    this.matricula = matricula;
    this.qualificacao = qualificacao;
    this.descricaoFuncao = descricaoFuncao;
    this.cargaHorariaSemanal = cargaHorariaSemanal;
    }
}