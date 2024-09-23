public class Vaga {
    private String id;
    private boolean estaOcupada;
    private Veiculo veiculoAtual;

    public Vaga(String id) {
        this.id = id;
        this.estaOcupada = false;
        this.veiculoAtual = null;
    }

    public void ocuparVaga(Veiculo veiculo) {
        if (!estaOcupada) {
            this.estaOcupada = true;
            this.veiculoAtual = veiculo;
        }
    }

    public void liberarVaga() {
        this.estaOcupada = false;
        this.veiculoAtual = null;
    }

    public boolean estaOcupada() {
        return estaOcupada;
    }

    public Veiculo getVeiculoAtual() {
        return veiculoAtual;
    }

    public String getId() {
        return id;
    }
}
