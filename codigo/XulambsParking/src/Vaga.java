public class Vaga {
    private String identificador;
    private TipoVaga tipoVaga;
    private Veiculo veiculo;
    private boolean ocupada = false;

    public Vaga(String identificador, TipoVaga tipoVaga) {
        this.identificador = identificador;
        this.tipoVaga = tipoVaga;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void bloquearVaga(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.ocupada = true;
    }

    public void desocuparVaga() {
        this.veiculo = null;
        this.ocupada = false;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
}
