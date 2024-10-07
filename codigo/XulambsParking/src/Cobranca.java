import java.time.LocalDateTime;

public class Cobranca {
    private double descontoVaga;
    private LocalDateTime tempoEntrada;
    private LocalDateTime tempoSaida;
    private int valorLimite = 50;

    public Cobranca(LocalDateTime tempoEntrada) {
        this.tempoEntrada = tempoEntrada;
    }

    public void registrarSaida(LocalDateTime tempoSaida) {
        this.tempoSaida = tempoSaida;
    }

    public double calcularCobrancaPorTempo(Cliente cliente, Vaga vaga) {
        long minutos = java.time.Duration.between(tempoEntrada, tempoSaida).toMinutes();
        double valorBase = (minutos / 15) * 4.0; // R$4 por 15 minutos

        // Aplicar limite de R$50
        if (valorBase > valorLimite) {
            valorBase = valorLimite;
        }

        // Aplicar desconto com base no tipo de vaga
        descontoVaga = calcularDesconto(vaga.getTipoVaga());
        return valorBase * (1 - descontoVaga);
    }

    public double calcularDesconto(TipoVaga tipoVaga) {
        switch (tipoVaga) {
            case IDOSO:
                return 0.15;
            case PCD:
                return 0.13;
            case VIP:
                return -0.2; // VIP é 20% mais caro
            default:
                return 0.0;
        }
    }
}
