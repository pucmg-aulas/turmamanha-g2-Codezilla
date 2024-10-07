import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParqueDeEstacionamento {
    private String nome;
    private String local;
    private int NdeVagas;
    private int VagasBloqueadas = 0;
    private List<Vaga> vagas;

    public ParqueDeEstacionamento(String nome, String local, int NdeVagas) {
        this.nome = nome;
        this.local = local;
        this.NdeVagas = NdeVagas;
        this.vagas = new ArrayList<>();
        for (int i = 1; i <= NdeVagas; i++) {
            // Adiciona vagas regulares por padrão
            vagas.add(new Vaga("V" + i, TipoVaga.REGULAR));
        }
    }

    public boolean encontrarVagasLivres() {
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                return true;
            }
        }
        return false;
    }

    public Optional<Vaga> vagaDesocupada() {
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                return Optional.of(vaga);
            }
        }
        return Optional.empty();
    }

    public void registrarEntradaVeiculo(Veiculo veiculo, TipoVaga tipoVaga) {
        Optional<Vaga> vagaLivre = vagaDesocupada();

        if (vagaLivre.isPresent()) {
            Vaga vaga = vagaLivre.get();
            vaga.bloquearVaga(veiculo);
            System.out.println("Veículo " + veiculo.getPlaca() + " estacionado na vaga " + vaga.getIdentificador());
        } else {
            System.out.println("Estacionamento cheio.");
        }
    }

    public void registrarSaidaVeiculo(Veiculo veiculo, Cliente cliente, LocalDateTime tempoEntrada) {
        for (Vaga vaga : vagas) {
            if (vaga.getVeiculo() != null && vaga.getVeiculo().equals(veiculo)) {
                Cobranca cobranca = new Cobranca(tempoEntrada);
                cobranca.registrarSaida(LocalDateTime.now());
                double valor = cobranca.calcularCobrancaPorTempo(cliente, vaga);
                System.out.println("Veículo " + veiculo.getPlaca() + " deixou a vaga " + vaga.getIdentificador() +
                        ". Valor da cobrança: R$" + valor);
                vaga.desocuparVaga();
                break;
            }
        }
    }

    public void SearchVeiculo(Veiculo veiculo) {
        for (Vaga vaga : vagas) {
            if (vaga.getVeiculo() != null && vaga.getVeiculo().equals(veiculo)) {
                System.out.println("Veículo " + veiculo.getPlaca() + " está estacionado na vaga " + vaga.getIdentificador());
                return;
            }
        }
        System.out.println("Veículo " + veiculo.getPlaca() + " não encontrado.");
    }
}
