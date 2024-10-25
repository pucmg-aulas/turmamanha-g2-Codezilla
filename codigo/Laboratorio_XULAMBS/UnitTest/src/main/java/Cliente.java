import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nome;
    private String cpf;
    private TipoCliente tipoCliente;
    private List<Veiculo> listaDeVeiculos;

    public Cliente(String id, String nome, String cpf, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoCliente = tipoCliente;
        this.listaDeVeiculos = new ArrayList<>();
    }

    public Cliente() {

    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        listaDeVeiculos.add(veiculo);
    }

    public List<Veiculo> getListaDeVeiculos() {
        return listaDeVeiculos;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
}