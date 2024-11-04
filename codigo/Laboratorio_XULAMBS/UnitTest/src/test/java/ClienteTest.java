import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testSetHorarioEntrada() {
        // Cria um cliente com nome, CPF, tipo de cliente, placa e modelo
        Cliente cliente = new Cliente("João", "123456789", TipoCliente.REGULAR, "ABC1234", "Sedan");
        
        // Define o horário de entrada do cliente às 9:00
        LocalTime entrada = LocalTime.of(9, 0);
        cliente.setHorarioEntrada(entrada);

        // Verifica se o horário de entrada foi definido corretamente
        // Verifica se o horário de entrada não é nulo
        assertNotNull(cliente.getHorarioEntrada(), "Horário de entrada não deve ser nulo");
        
        // Verifica se o horário de entrada corresponde ao horário definido (9:00)
        assertEquals(entrada, cliente.getHorarioEntrada().toLocalTime(), "Horário de entrada deve ser igual ao definido");
    }

    @Test
    void testCalcularTempoEstacionado() {
        // Cria um cliente com nome, CPF, tipo de cliente, placa e modelo
        Cliente cliente = new Cliente("Maria", "987654321", TipoCliente.VIP, "XYZ5678", "SUV");
        
        // Define o horário de entrada do cliente às 10:00
        LocalTime entrada = LocalTime.of(10, 0);
        cliente.setHorarioEntrada(entrada);

        // Define o horário de saída do cliente às 12:00
        LocalTime saida = LocalTime.of(12, 0);
        cliente.setHorarioSaida(saida);

        // Calcula o tempo estacionado em minutos (esperado 120 minutos)
        long tempoEstacionado = cliente.calcularTempoEstacionado();

        // Verifica se o tempo estacionado é igual a 120 minutos
        assertEquals(120, tempoEstacionado, "Tempo estacionado deve ser 120 minutos");
    }
}
