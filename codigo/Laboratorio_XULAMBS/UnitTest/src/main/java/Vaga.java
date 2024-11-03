public class Vaga {
    private String identificacao;
    private String tipo;
    private boolean disponivel;

    public Vaga(String identificacao, String tipo) {
        this.identificacao = identificacao;
        this.tipo = tipo;
        this.disponivel = true; // Vaga começa como disponível
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
