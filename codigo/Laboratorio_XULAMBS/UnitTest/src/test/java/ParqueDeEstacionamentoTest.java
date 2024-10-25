import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ParqueDeEstacionamentoTest {
    /* testRegistrarEntradaVeiculo
Descrição: Este teste verifica se um veículo pode ser registrado corretamente na entrada do estacionamento. Um veículo é
criado e a entrada é registrada. O teste confirma se ainda há vagas desocupadas no estacionamento após registrar a
entrada do veículo.

testRegistrarSaidaVeiculo
Descrição: Este teste verifica se um veículo pode sair do estacionamento corretamente e se a cobrança é calculada.
Um veículo é registrado como entrando no estacionamento e, em seguida, a saída é registrada. O teste verifica se não há
mais vagas desocupadas após o veículo sair.
*/
    @Test
    void testRegistrarEntradaVeiculo() {
        ParqueDeEstacionamento estacionamento = new ParqueDeEstacionamento("Xulambs", "Centro", 5);
        Veiculo veiculo = new Veiculo("ABC-1234", "Fusca");

        estacionamento.registrarEntradaVeiculo(veiculo, TipoVaga.REGULAR);
        assertTrue(estacionamento.vagaDesocupada().isPresent());
    }

    @Test
    void testRegistrarSaidaVeiculo() {
        ParqueDeEstacionamento estacionamento = new ParqueDeEstacionamento("Xulambs", "Centro", 5);
        Veiculo veiculo = new Veiculo("ABC-1234", "Fusca");

        estacionamento.registrarEntradaVeiculo(veiculo, TipoVaga.REGULAR);
        LocalDateTime tempoEntrada = LocalDateTime.now().minusMinutes(30);
        estacionamento.registrarSaidaVeiculo(veiculo, new Cliente("1", "Carlos", "12345678900", TipoCliente.REGULAR), tempoEntrada);

        assertFalse(estacionamento.vagaDesocupada().isPresent());
    }
}
