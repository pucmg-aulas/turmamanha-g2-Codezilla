import java.util.ArrayList;
import java.util.List;

// Classe Cliente
class Cliente {
    private String id;
    private String nome;
    private List<Veiculo> listaDeVeiculos;
    private TipoCliente tipoCliente;

    public Cliente(String id, String nome, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.tipoCliente = tipoCliente;
        this.listaDeVeiculos = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        listaDeVeiculos.add(veiculo);
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
}

// Enum TipoCliente
enum TipoCliente {
    REGULAR, IDOSO, PCD, VIP
}

// Classe Veiculo
class Veiculo {
    private String placa;

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }
}

// Classe Vaga
class Vaga {
    private String identificador;
    private TipoVaga tipoVaga;
    private boolean ocupada = false;

    public Vaga(String identificador, TipoVaga tipoVaga) {
        this.identificador = identificador;
        this.tipoVaga = tipoVaga;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar() {
        ocupada = true;
    }

    public void liberar() {
        ocupada = false;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public String getIdentificador() {
        return identificador;
    }
}

// Enum TipoVaga
enum TipoVaga {
    REGULAR, VIP
}

// Classe Cobrança
class Cobranca {
    private static final double PRECO_FRAÇÃO = 4.0; // R$4 a cada 15 minutos
    private static final double LIMITE_COBRANÇA = 50.0;

    public static double calcularCobranca(long minutos, Cliente cliente, Vaga vaga) {
        double valorBase = (minutos / 15) * PRECO_FRAÇÃO;

        // Aplica o limite de R$50
        if (valorBase > LIMITE_COBRANÇA) {
            valorBase = LIMITE_COBRANÇA;
        }

        // Calcula o desconto com base no tipo de cliente
        double desconto = 0.0;
        if (cliente.getTipoCliente() == TipoCliente.IDOSO) {
            desconto = 0.15; // 15% desconto
        } else if (cliente.getTipoCliente() == TipoCliente.PCD) {
            desconto = 0.13; // 13% desconto
        }

        // Ajuste para vaga VIP
        if (vaga.getTipoVaga() == TipoVaga.VIP) {
            valorBase *= 1.2; // Vaga VIP é 20% mais cara
        }

        // Aplicação do desconto (se houver)
        return valorBase * (1 - desconto);
    }
}

// Classe ParqueDeEstacionamento
class ParqueDeEstacionamento {
    private List<Vaga> vagas = new ArrayList<>();

    public ParqueDeEstacionamento(int numeroDeVagas, int numeroDeVagasVIP) {
        for (int i = 1; i <= numeroDeVagas; i++) {
            TipoVaga tipo = i <= numeroDeVagasVIP ? TipoVaga.VIP : TipoVaga.REGULAR;
            vagas.add(new Vaga("V" + i, tipo));
        }
    }

    public Vaga registrarEntradaVeiculo(Veiculo veiculo) {
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                vaga.ocupar();
                System.out.println("Veículo " + veiculo.getPlaca() + " estacionado na vaga " + vaga.getIdentificador());
                return vaga;
            }
        }
        System.out.println("Estacionamento cheio!");
        return null;
    }

    public void registrarSaidaVeiculo(Veiculo veiculo, Vaga vaga, long minutos, Cliente cliente) {
        if (vaga != null && vaga.isOcupada()) {
            double valor = Cobranca.calcularCobranca(minutos, cliente, vaga);
            System.out.println("Veículo " + veiculo.getPlaca() + " deixou a vaga " + vaga.getIdentificador() +
                    ". Valor da cobrança: R$" + valor);
            vaga.liberar();
        } else {
            System.out.println("Vaga não está ocupada!");
        }
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        // Criação de clientes
        Cliente clienteRegular = new Cliente("1", "João", TipoCliente.REGULAR);
        Cliente clienteIdoso = new Cliente("2", "Maria", TipoCliente.IDOSO);
        Cliente clientePCD = new Cliente("3", "José", TipoCliente.PCD);
        Cliente clienteVIP = new Cliente("4", "Ana", TipoCliente.VIP);

        // Criação de veículos
        Veiculo carroJoao = new Veiculo("ABC-1234");
        Veiculo carroMaria = new Veiculo("XYZ-5678");
        Veiculo carroJose = new Veiculo("DEF-9012");
        Veiculo carroAna = new Veiculo("GHI-3456");

        // Cadastro dos veículos aos clientes
        clienteRegular.cadastrarVeiculo(carroJoao);
        clienteIdoso.cadastrarVeiculo(carroMaria);
        clientePCD.cadastrarVeiculo(carroJose);
        clienteVIP.cadastrarVeiculo(carroAna);

        // Criação do estacionamento (com 5 vagas, 2 delas VIP)
        ParqueDeEstacionamento estacionamento = new ParqueDeEstacionamento(5, 2);

        // Simulação de entrada e saída de veículos
        Vaga vagaJoao = estacionamento.registrarEntradaVeiculo(carroJoao);
        estacionamento.registrarSaidaVeiculo(carroJoao, vagaJoao, 60, clienteRegular); // 60 minutos

        Vaga vagaMaria = estacionamento.registrarEntradaVeiculo(carroMaria);
        estacionamento.registrarSaidaVeiculo(carroMaria, vagaMaria, 45, clienteIdoso); // 45 minutos

        Vaga vagaJose = estacionamento.registrarEntradaVeiculo(carroJose);
        estacionamento.registrarSaidaVeiculo(carroJose, vagaJose, 30, clientePCD); // 30 minutos

        Vaga vagaAna = estacionamento.registrarEntradaVeiculo(carroAna);
        estacionamento.registrarSaidaVeiculo(carroAna, vagaAna, 75, clienteVIP); // 75 minutos
    }
}
