public class Veiculo {
    private String placa;
    private String modelo;
    private Cliente cliente;

    public Veiculo(String placa, String modelo, Cliente cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }
}
