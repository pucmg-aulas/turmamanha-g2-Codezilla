import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String id;
    private String nome;
    private String endereco;
    private List<Vaga> listaDeVagas;

    public Estacionamento(String id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.listaDeVagas = new ArrayList<>();
    }

    public void registrarEntradaVeiculo(Veiculo veiculo) {
        for (Vaga vaga : listaDeVagas) {
            if (!vaga.estaOcupada()) {
                vaga.ocuparVaga(veiculo);
                System.out.println("Veículo " + veiculo.getPlaca() + " estacionado na vaga " + vaga.getId());
                return;
            }
        }
        System.out.println("Não há vagas disponíveis.");
    }

    public void registrarSaidaVeiculo(Veiculo veiculo) {
        for (Vaga vaga : listaDeVagas) {
            if (vaga.getVeiculoAtual() == veiculo) {
                vaga.liberarVaga();
                System.out.println("Veículo " + veiculo.getPlaca() + " saiu da vaga " + vaga.getId());
                return;
            }
        }
        System.out.println("Veículo não encontrado em nenhuma vaga.");
    }

    public void adicionarVaga(Vaga vaga) {
        listaDeVagas.add(vaga);
    }

    public List<Vaga> getListaDeVagas() {
        return listaDeVagas;
    }
}
