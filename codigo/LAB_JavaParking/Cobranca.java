public class Cobranca {
    private double valorPorMinuto = 0.266; // R$4,00 por 15 minutos
    private double valorMaximo = 50.00;

    public double calcularValor(int tempoOcupacao) {
        double valor = tempoOcupacao * valorPorMinuto;
        return Math.min(valor, valorMaximo);
    }
}
