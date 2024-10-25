import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    /* testGetPlaca
Descrição: Este teste verifica se o metodo getPlaca retorna corretamente a placa do veículo. Um veículo é criado e o
teste compara a placa retornada com a placa esperada.

testGetModelo
Descrição: Este teste verifica se o metodo getModelo retorna corretamente o modelo do veículo. Um veículo é criado e o
teste compara o modelo retornado com o modelo esperado.
*/

    @Test
    void testGetPlaca() {
        Veiculo veiculo = new Veiculo("ABC-1234", "Fusca");
        assertEquals("ABC-1234", veiculo.getPlaca());
    }

    @Test
    void testGetModelo() {
        Veiculo veiculo = new Veiculo("ABC-1234", "Fusca");
        assertEquals("Fusca", veiculo.getModelo());
    }
}
