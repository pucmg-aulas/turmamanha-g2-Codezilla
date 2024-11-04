import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class VagaTest {

    @Test
    public void testVagaInicializacao() {
        Vaga vaga = new Vaga("A01", "VIP");
        
        // Verifica se a identificação e o tipo estão corretos
        assertEquals("A01", vaga.getIdentificacao(), "Identificação incorreta");
        assertEquals("VIP", vaga.getTipo(), "Tipo de vaga incorreto");
        
        // Verifica se a vaga está disponível ao ser criada
        assertTrue(vaga.isDisponivel(), "A vaga deveria estar disponível ao ser criada");
    }

    @Test
    public void testAlterarDisponibilidadeVaga() {
        Vaga vaga = new Vaga("B02", "Regular");
        
        // Define a vaga como indisponível e verifica
        vaga.setDisponivel(false);
        assertFalse(vaga.isDisponivel(), "A vaga deveria estar indisponível após a alteração");
        
        // Define a vaga como disponível e verifica
        vaga.setDisponivel(true);
        assertTrue(vaga.isDisponivel(), "A vaga deveria estar disponível após a alteração");
    }
}
