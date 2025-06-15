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
    private float cargaHorariaSemanal;

    //Construtores
    public Funcionario(
        String nome,
        String matricula,
        String qualificacao,
        String descricaoFuncao,
        float cargaHorariaSemanal) {
      super(nome, matricula); // Usando matrícula como ID
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

    /**
     * Método que retorna uma representação detalhada do funcionário.
     * @return String com detalhes do funcionário.
     */
    public String toStringDetalhado() {
        return "Nome: " + nome + 
               "\nMatrícula: " + matricula + 
               "\nQualificação: " + qualificacao + 
               "\nDescrição da Função: " + descricaoFuncao + 
               "\nCarga Horária Semanal: " + cargaHorariaSemanal + " horas";
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

    public float getCargaHorariaSemanal() { return cargaHorariaSemanal; }

    public void setCargaHorariaSemanal(float cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }
}