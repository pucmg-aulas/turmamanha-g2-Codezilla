import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nome;
    private List<Veiculo> listaDeVeiculos;

    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.listaDeVeiculos = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        listaDeVeiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        listaDeVeiculos.remove(veiculo);
    }

    public List<Veiculo> getListaDeVeiculos() {
        return listaDeVeiculos;
    }
}
