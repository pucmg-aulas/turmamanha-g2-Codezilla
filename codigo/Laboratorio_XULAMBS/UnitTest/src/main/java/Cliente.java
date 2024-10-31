import java.time.LocalTime;
import java.time.Duration;

public class Cliente {
    private String nome;
    private String cpf;
    private LocalTime horarioEntrada;
    private LocalTime horarioSaida;
    private TipoCliente tipoCliente;
    private String placa;
    private String modelo;

    // Construtor para definir o nome, CPF, tipo de cliente, placa e modelo do carro
    public Cliente(String nome, String cpf, TipoCliente tipoCliente, String placa, String modelo) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipoCliente = tipoCliente;
        this.placa = placa;
        this.modelo = modelo;
    }

    public void setHorarioEntrada(LocalTime entrada) {
        this.horarioEntrada = entrada;
    }

    public void setHorarioSaida(LocalTime saida) {
        this.horarioSaida = saida;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    // Calcula o tempo total estacionado em minutos
    public long calcularTempoEstacionado() {
        if (horarioEntrada != null && horarioSaida != null) {
            return Duration.between(horarioEntrada, horarioSaida).toMinutes();
        } else {
            throw new IllegalStateException("Horário de entrada ou saída não definidos.");
        }
    }
}
