import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

        /* testCadastrarVeiculo
    Descrição: Este teste verifica se a funcionalidade de cadastrar um veículo em um cliente está funcionando corretamente.
    Um cliente é criado e um veículo é cadastrado. O teste então verifica se a lista de veículos do cliente contém o veículo
    cadastrado e se seu tamanho é 1.

    testGetNome
    Descrição: Este teste verifica se o metodo getNome retorna corretamente o nome do cliente. Um cliente é criado e o
    teste compara o nome retornado com o valor esperado.
    */

    @Test
    void testCadastrarVeiculo() {
        Cliente cliente = new Cliente("1", "Carlos", "12345678900", TipoCliente.REGULAR);
        Veiculo veiculo = new Veiculo("ABC-1234", "Fusca");
        cliente.cadastrarVeiculo(veiculo);

        assertEquals(1, cliente.getListaDeVeiculos().size());
        assertEquals(veiculo, cliente.getListaDeVeiculos().get(0));
    }

    @Test
    void testGetNome() {
        Cliente cliente = new Cliente("1", "Carlos", "12345678900", TipoCliente.REGULAR);
        assertEquals("Carlos", cliente.getNome());
    }
}
