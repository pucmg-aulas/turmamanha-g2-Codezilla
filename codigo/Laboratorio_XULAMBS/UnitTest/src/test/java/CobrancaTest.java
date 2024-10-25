import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CobrancaTest {

    /* testCalcularCobrancaPorTempo
    Descrição: Este teste verifica se o cálculo da cobrança por tempo de estacionamento está correto. Um objeto Cobranca
    é criado com um horário de entrada, e a saída é registrada. O teste então calcula o valor da cobrança e verifica se
    o valor cobrado corresponde ao esperado para um estacionamento de 30 minutos.

    testCalcularDesconto
    Descrição: Este teste verifica se a aplicação dos descontos para diferentes tipos de vagas funciona corretamente. O
    metodo calcularDesconto é chamado para cada tipo de vaga (idoso, PCD e VIP), e os valores retornados são comparados
    com os valores esperados.
    */

    @Test
    void testCalcularCobrancaPorTempo() {
        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();
        Cliente cliente = new Cliente("1", "Carlos", "12345678900", TipoCliente.REGULAR);
        Vaga vaga = new Vaga("V1", TipoVaga.REGULAR);

        Cobranca cobranca = new Cobranca(entrada);
        cobranca.registrarSaida(saida);
        double valorCobrado = cobranca.calcularCobrancaPorTempo(cliente, vaga);

        assertEquals(8.0, valorCobrado); // 30 minutos = 8 reais
    }

    @Test
    void testCalcularDesconto() {
        Cobranca cobranca = new Cobranca(LocalDateTime.now());
        double descontoIdoso = cobranca.calcularDesconto(TipoVaga.IDOSO);
        double descontoPCD = cobranca.calcularDesconto(TipoVaga.PCD);
        double descontoVIP = cobranca.calcularDesconto(TipoVaga.VIP);

        assertEquals(0.15, descontoIdoso);
        assertEquals(0.13, descontoPCD);
        assertEquals(-0.2, descontoVIP); // VIP deve ter um aumento
    }
}
