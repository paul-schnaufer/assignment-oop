package petshop.service;

public class AtendimentoService {
    private Map<String, Atendimento> atendimentos;

    public AtendimentoService(Map<String, Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    // Métodos para manipulação de atendimentos, como cadastrar, consultar, alterar e remover atendimentos
}
