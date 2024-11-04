import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CobrancaTest {

    @Test
    public void testCalcularValorClienteRegular() {
        Cobranca cobranca = new Cobranca();
        Cliente clienteRegular = new Cliente("Cliente1", TipoCliente.REGULAR);

        double valorCobrado = cobranca.calcularValor(30, clienteRegular); // 30 minutos (2 frações de 15 minutos)

        // Espera-se: 2 frações * R$4 = R$8
        assertEquals(8.0, valorCobrado, 0.01);
    }

    @Test
    public void testCalcularValorClienteIdoso() {
        Cobranca cobranca = new Cobranca();
        Cliente clienteIdoso = new Cliente("Cliente2", TipoCliente.IDOSO);

        double valorCobrado = cobranca.calcularValor(30, clienteIdoso); // 30 minutos (2 frações de 15 minutos)

        // Espera-se: 2 frações * R$4 = R$8 - 15% de desconto = R$6.80
        assertEquals(6.80, valorCobrado, 0.01);
    }
}
