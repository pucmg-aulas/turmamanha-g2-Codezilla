import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Criação de um cliente e veículos
        Cliente cliente = new Cliente("1", "João");
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Sedan");
        cliente.cadastrarVeiculo(veiculo1);

        // Criação do estacionamento com 5 vagas
        ParqueDeEstacionamento estacionamento = new ParqueDeEstacionamento("Xulambs", "Centro", 5);

        // Entrada de veículo
        estacionamento.registrarEntradaVeiculo(veiculo1, TipoVaga.REGULAR);

        // Simula o tempo de estacionamento
        LocalDateTime tempoEntrada = LocalDateTime.now().minusMinutes(60); // 60 minutos

        // Saída do veículo
        estacionamento.registrarSaidaVeiculo(veiculo1, cliente, tempoEntrada);
    }
}
