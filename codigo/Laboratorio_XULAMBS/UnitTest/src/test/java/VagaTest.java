import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VagaTest {

    /* testBloquearVaga
    Descrição: Este teste verifica se uma vaga pode ser bloqueada (ocupada) corretamente por um veículo. Uma vaga é
    criada e um veículo é registrado nela. O teste confirma que a vaga está ocupada e que o veículo associado à vaga é o
    esperado.

    testDesocuparVaga
    Descrição: Este teste verifica se uma vaga pode ser desocupada corretamente. Após bloquear a vaga com um veículo, a
    vaga é desocupada e o teste verifica se a vaga não está mais ocupada e se o veículo associado à vaga é null.
    */

    @Test
    void testBloquearVaga() {
        Vaga vaga = new Vaga("V1", TipoVaga.REGULAR);
        Veiculo veiculo = new Veiculo("ABC-1234", "Fusca");

        vaga.bloquearVaga(veiculo);
        assertTrue(vaga.isOcupada());
        assertEquals(veiculo, vaga.getVeiculo());
    }

    @Test
    void testDesocuparVaga() {
        Vaga vaga = new Vaga("V1", TipoVaga.REGULAR);
        Veiculo veiculo = new Veiculo("ABC-1234", "Fusca");

        vaga.bloquearVaga(veiculo);
        vaga.desocuparVaga();
        assertFalse(vaga.isOcupada());
        assertNull(vaga.getVeiculo());
    }
}
