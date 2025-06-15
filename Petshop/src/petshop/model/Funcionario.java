package petshop.model;

/**
 * Classe que representa um Funcionário no sistema de Petshop.
 * Contém informações sobre o funcionário, como nome, matrícula, qualificação,
 * descrição da função e carga horária semanal de trabalho.
 */
public class Funcionario extends Pessoa {
    // Atributos
    private String matricula;
    private String qualificacao;
    private String descricaoFuncao;
    private float cargaHoraria;
    
    // Construtores
    /**
     * Construtor da classe Funcionario.
     * @param nome Nome do funcionário.
     * @param matricula Matrícula do funcionário.
     * @param qualificacao Qualificação do funcionário.
     * @param descricaoFuncao Descrição da função executada pelo funcionário.
     * @param cargaHoraria Carga horária semanal de trabalho do funcionário.
     */
    public Funcionario(
            String nome,
            String matricula,
            String qualificacao,
            String descricaoFuncao,
            float cargaHoraria) {
        super(nome);
        this.matricula = matricula;
        this.qualificacao = qualificacao;
        this.descricaoFuncao = descricaoFuncao;
        this.cargaHoraria = cargaHoraria;
  }

    // Métodos
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

    /**
     * Método que retorna o tipo de pessoa.
     * @return String indicando o tipo de pessoa.
     */
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