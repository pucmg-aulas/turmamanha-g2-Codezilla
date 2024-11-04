import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    @Test
    void testGetPlaca() {
        // Cria um objeto Veiculo com uma placa específica
        Veiculo veiculo = new Veiculo("ABC1234", "Sedan");

        // Verifica se o método getPlaca retorna a placa correta
        assertEquals("ABC1234", veiculo.getPlaca(), "A placa do veículo deve ser igual a 'ABC1234'");
    }

    @Test
    void testGetModelo() {
        // Cria um objeto Veiculo com um modelo específico
        Veiculo veiculo = new Veiculo("XYZ5678", "SUV");

        // Verifica se o método getModelo retorna o modelo correto
        assertEquals("SUV", veiculo.getModelo(), "O modelo do veículo deve ser igual a 'SUV'");
    }
}
