import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ParqueDeEstacionamentoTest {
    private ParqueDeEstacionamento estacionamento;
    private Cliente cliente;
    private Vaga vaga;

    @BeforeEach
    void setUp() {
        estacionamento = new ParqueDeEstacionamento();
        cliente = new Cliente("João", "12345678900", TipoCliente.REGULAR, "XYZ1234", "Sedan");
        vaga = estacionamento.getVagaPorIdentificacao("REG01");
        cliente.setHorarioEntrada(LocalDateTime.now().minusHours(1).toLocalTime()); // Define horário de entrada 1 hora atrás
    }

    @Test
    void testCalcularCobrancaPorTempo() {
        // Define horário de saída para calcular tempo estacionado
        cliente.setHorarioSaida(LocalDateTime.now().toLocalTime());

        // Calcula a cobrança pelo tempo estacionado (1 hora)
        double valorCobrado = estacionamento.calcularCobrancaPorTempo(cliente);

        // Como o cliente ficou 1 hora (60 minutos), a cobrança esperada é de R$16 (4 reais a cada 15 minutos)
        assertEquals(16.0, valorCobrado, 0.01, "A cobrança deve ser de R$16 para 1 hora de estacionamento");
    }

    @Test
    void testGetTotalArrecadadoMes() {
        // Registra a saída do cliente e calcula a cobrança
        cliente.setHorarioSaida(LocalDateTime.now().toLocalTime());
        estacionamento.registrarSaida(cliente, vaga, LocalDateTime.now());

        // Obtém o total arrecadado para o mês e ano atual
        int mesAtual = LocalDateTime.now().getMonthValue();
        int anoAtual = LocalDateTime.now().getYear();
        double totalArrecadadoMes = estacionamento.getTotalArrecadadoMes(mesAtual, anoAtual);

        // Como o cliente pagou R$16, o total arrecadado no mês atual deve ser igual a R$16
        assertEquals(16.0, totalArrecadadoMes, 0.01, "O total arrecadado no mês deve ser R$16 para uma única utilização de 1 hora");
    }
}
