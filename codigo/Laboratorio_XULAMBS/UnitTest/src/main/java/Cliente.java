import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cliente {
    private String nome;
    private String cpf;
    private TipoCliente tipoCliente;
    private String placa;
    private String modelo;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private String vagaOcupada; // Identificação da vaga ocupada pelo cliente

    public Cliente(String nome, String cpf, TipoCliente tipoCliente, String placa, String modelo) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipoCliente = tipoCliente;
        this.placa = placa;
        this.modelo = modelo;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
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

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public String getVagaOcupada() {
        return vagaOcupada;
    }

    public void setVagaOcupada(String vagaOcupada) {
        this.vagaOcupada = vagaOcupada;
    }

    // Define o horário de entrada do cliente
    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = LocalDateTime.of(LocalDateTime.now().toLocalDate(), horarioEntrada);
    }

    // Define o horário de saída do cliente
    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = LocalDateTime.of(LocalDateTime.now().toLocalDate(), horarioSaida);
    }

    // Calcula o tempo estacionado em minutos
    public long calcularTempoEstacionado() {
        if (horarioEntrada != null && horarioSaida != null) {
            return java.time.Duration.between(horarioEntrada, horarioSaida).toMinutes();
        }
        return 0;
    }
}
