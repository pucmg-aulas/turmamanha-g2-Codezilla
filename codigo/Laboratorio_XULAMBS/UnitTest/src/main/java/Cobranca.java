public class Cobranca {
    private static final double TAXA_FRACAO = 4.00; // R$4 por cada 15 minutos
    private static final int TEMPO_FRACAO = 15; // Fração de 15 minutos
    private static final double VALOR_MAXIMO = 50.00; // Valor máximo de R$50

    private static final double DESCONTO_IDOSOS = 0.15; // 15% de desconto para idosos
    private static final double DESCONTO_PCD = 0.13; // 13% de desconto para PCD
    private static final double AUMENTO_VIP = 0.20; // 20% a mais para clientes VIP

    // Construtor padrão
    public Cobranca() {
    }

    public double calcularValor(long tempoEstacionado, Cliente cliente) {
        // Calcula o número de frações de 15 minutos
        long numeroDeFracoes = (tempoEstacionado + TEMPO_FRACAO - 1) / TEMPO_FRACAO;

        // Calcula o valor base antes de aplicar descontos ou acréscimos
        double valorBase = numeroDeFracoes * TAXA_FRACAO;

        // Ajuste de taxa baseado no tipo de cliente
        switch (cliente.getTipoCliente()) {
            case IDOSO:
                valorBase *= (1 - DESCONTO_IDOSOS);
                break;
            case PCD:
                valorBase *= (1 - DESCONTO_PCD);
                break;
            case VIP:
                valorBase *= (1 + AUMENTO_VIP);
                break;
            case REGULAR:
            default:
                // taxa regular sem alterações
                break;
        }

        // Aplica o limite máximo de R$50
        return Math.min(valorBase, VALOR_MAXIMO);
    }
}
