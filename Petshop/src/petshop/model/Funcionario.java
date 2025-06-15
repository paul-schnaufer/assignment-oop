package petshop.model;

/**
 * Classe Funcionario que herda da classe Pessoa.
 * Representa um funcionário do petshop, contendo informações como matrícula,
 */
public class Funcionario extends Pessoa {
    //Atributos
    private String matricula;
    private String qualificacao;
    private String descricaoFuncao;
    private float cargaHoraria;

    // TODO: Implementar uma id única para cada funcionário,
    // TODO: possivelmente usando UUID ou outro método de geração de ID.
    
    //Construtores
    public Funcionario(
        String nome,
        String matricula,
        String qualificacao,
        String descricaoFuncao,
        float cargaHoraria) {
      super(nome, matricula); // Usando matrícula como ID
      this.qualificacao = qualificacao;
      this.descricaoFuncao = descricaoFuncao;
      this.cargaHoraria = cargaHoraria;
  }

    //Métodos
    public void consultarFuncionario(){
      System.out.println("\n--------Funcionário--------\nNome: " 
        + this.nome + "\nMatrícula: " 
        + this.matricula + "\nQualificação: " 
        + this.qualificacao + "\nDescrição da Função Executada: " 
        + this.descricaoFuncao + "\nCarga Horária Semanal de Trabalho: " 
        + this.cargaHoraria + " horas");
      }

    /**
     * Método que retorna uma representação detalhada do funcionário.
     * @return String com detalhes do funcionário.
     */
    public String toStringDetalhado() {
        return "Nome: " + nome + 
               "\nMatrícula: " + matricula + 
               "\nQualificação: " + qualificacao + 
               "\nDescrição da Função: " + descricaoFuncao + 
               "\nCarga Horária Semanal: " + cargaHoraria + " horas";
    }

    @Override
    public String getTipo() {
        return "Funcionário";
    }

    // Getters e Setters
    public String getMatricula() { return matricula; }

    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getQualificacao() { return qualificacao; }

    public void setQualificacao(String qualificacao) { this.qualificacao = qualificacao; }

    public String getDescricaoFuncao() { return descricaoFuncao; }

    public void setDescricaoFuncao(String descricaoFuncao) {
        this.descricaoFuncao = descricaoFuncao; 
    }

    public float getCargaHoraria() { return cargaHoraria; }

    public void setCargaHoraria(float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}