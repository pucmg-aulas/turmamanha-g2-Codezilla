import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Classe Cliente
class Cliente {
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

    public List<Veiculo> getListaDeVeiculos() {
        return listaDeVeiculos;
    }

    public String getNome() {
        return nome;
    }
}

// Classe Veiculo
class Veiculo {
    private String placa;
    private String modelo;

    public Veiculo(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }
}

// Enum para Tipo de Vaga
enum TipoVaga {
    REGULAR, VIP, IDOSO, PCD
}

// Classe Vaga
class Vaga {
    private String identificador;
    private TipoVaga tipoVaga;
    private Veiculo veiculo;
    private boolean ocupada = false;

    public Vaga(String identificador, TipoVaga tipoVaga) {
        this.identificador = identificador;
        this.tipoVaga = tipoVaga;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void bloquearVaga(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.ocupada = true;
    }

    public void desocuparVaga() {
        this.veiculo = null;
        this.ocupada = false;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
}

// Classe Cobrança
class Cobranca {
    private double descontoVaga;
    private LocalDateTime tempoEntrada;
    private LocalDateTime tempoSaida;
    private int valorLimite = 50;

    public Cobranca(LocalDateTime tempoEntrada) {
        this.tempoEntrada = tempoEntrada;
    }

    public void registrarSaida(LocalDateTime tempoSaida) {
        this.tempoSaida = tempoSaida;
    }

    public double calcularCobrancaPorTempo(Cliente cliente, Vaga vaga) {
        long minutos = java.time.Duration.between(tempoEntrada, tempoSaida).toMinutes();
        double valorBase = (minutos / 15) * 4.0; // R$4 por 15 minutos

        // Aplicar limite de R$50
        if (valorBase > valorLimite) {
            valorBase = valorLimite;
        }

        // Aplicar desconto com base no tipo de vaga
        descontoVaga = calcularDesconto(vaga.getTipoVaga());
        return valorBase * (1 - descontoVaga);
    }

    public double calcularDesconto(TipoVaga tipoVaga) {
        switch (tipoVaga) {
            case IDOSO:
                return 0.15;
            case PCD:
                return 0.13;
            case VIP:
                return -0.2; // VIP é 20% mais caro
            default:
                return 0.0;
        }
    }
}

// Classe Parque de Estacionamento
class ParqueDeEstacionamento {
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
                Cobrança cobranca = new Cobrança(tempoEntrada);
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

// Classe principal com exemplo de uso
public class Main {
    public static void main(String[] args) {
        // Criação de um cliente e veículos
        Cliente cliente = new Cliente("1", "João");
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Sedan");
        cliente.cadastrarVeiculo(veiculo1);

        // Criação do estacionamento com 5 vagas
        ParqueDeEstacionamento estacionamento = new ParqueDeEstacionamento("Xulambs", "Centro", 5);

        // Entrada de veículo
        estacionamento.registrarEntradaVeiculo(veiculo1, TipoVaga.REGULAR);

        // Simula o tempo de estacionamento
        LocalDateTime tempoEntrada = LocalDateTime.now().minusMinutes(60); // 60 minutos

        // Saída do veículo
        estacionamento.registrarSaidaVeiculo(veiculo1, cliente, tempoEntrada);
    }
}
