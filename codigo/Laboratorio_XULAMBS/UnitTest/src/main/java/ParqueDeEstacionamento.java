import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ParqueDeEstacionamento {
    private double totalArrecadado = 0.0;
    private int totalUtilizacoes = 0;
    private Map<String, Vaga> vagas = new HashMap<>();
    private List<RegistroSaida> registrosSaida = new ArrayList<>();

    public ParqueDeEstacionamento() {
        // Inicializar 100 vagas divididas entre tipos
        for (int i = 1; i <= 70; i++) {
            vagas.put("REG" + String.format("%02d", i), new Vaga("REG" + String.format("%02d", i), "REGULAR"));
        }
        for (int i = 1; i <= 10; i++) {
            vagas.put("VIP" + String.format("%02d", i), new Vaga("VIP" + String.format("%02d", i), "VIP"));
            vagas.put("PCD" + String.format("%02d", i), new Vaga("PCD" + String.format("%02d", i), "PCD"));
            vagas.put("IDO" + String.format("%02d", i), new Vaga("IDO" + String.format("%02d", i), "IDOSO"));
        }
    }

    // Retorna o total arrecadado no estacionamento
    public double getTotalArrecadado() {
        return totalArrecadado;
    }

    // Calcula o valor médio por utilização do estacionamento
    public double getValorMedioUtilizacao() {
        if (totalUtilizacoes == 0) return 0.0;
        return totalArrecadado / totalUtilizacoes;
    }

    // Retorna o total arrecadado em um determinado mês e ano
    public double getTotalArrecadadoMes(int mes, int ano) {
        double total = 0.0;
        for (RegistroSaida registro : registrosSaida) {
            if (registro.getDataSaida().getMonthValue() == mes && registro.getDataSaida().getYear() == ano) {
                total += registro.getValor();
            }
        }
        return total;
    }

    // Retorna uma lista de identificadores de vagas disponíveis para o tipo especificado
    public List<String> getVagasDisponiveis(String tipoCliente) {
        List<String> vagasDisponiveis = new ArrayList<>();
        for (Map.Entry<String, Vaga> entry : vagas.entrySet()) {
            Vaga vaga = entry.getValue();
            if (vaga.getTipo().equalsIgnoreCase(tipoCliente) && vaga.isDisponivel()) {
                vagasDisponiveis.add(entry.getKey());
            }
        }
        return vagasDisponiveis;
    }

    // Aloca uma vaga específica, se estiver disponível
    public Vaga alocarVagaEspecifica(String identificadorVaga) {
        Vaga vaga = vagas.get(identificadorVaga);
        if (vaga != null && vaga.isDisponivel()) {
            vaga.setDisponivel(false);
            return vaga;
        } else {
            throw new IllegalStateException("A vaga " + identificadorVaga + " já está ocupada ou não existe.");
        }
    }

    // Método para obter o ranking dos clientes que mais geraram arrecadação em um determinado mês e ano
    public List<Map.Entry<String, Double>> obterRankingClientes(int mes, int ano) {
        Map<String, Double> arrecadacaoMes = new HashMap<>();

        // Filtra registros de saída do mês e ano especificados e acumula o valor por CPF
        for (RegistroSaida registro : registrosSaida) {
            if (registro.getDataSaida().getMonthValue() == mes && registro.getDataSaida().getYear() == ano) {
                String cpfCliente = registro.getCpfCliente();
                arrecadacaoMes.put(cpfCliente, arrecadacaoMes.getOrDefault(cpfCliente, 0.0) + registro.getValor());
            }
        }

        // Ordena o mapa por valor arrecadado em ordem decrescente
        return arrecadacaoMes.entrySet()
                .stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toList());
    }

    public void registrarSaida(Cliente cliente, Vaga vaga, LocalDateTime horarioSaida) {
        if (vaga == null) {
            throw new IllegalStateException("Erro: A vaga não foi encontrada ou não foi atribuída corretamente.");
        }

        double valor = calcularCobrancaPorTempo(cliente);
        totalArrecadado += valor;
        totalUtilizacoes++;

        // Adiciona um registro de saída com a data e valor
        registrosSaida.add(new RegistroSaida(horarioSaida, valor, cliente.getCpf()));

        // Libera a vaga
        vaga.setDisponivel(true);
    }

    public double calcularCobrancaPorTempo(Cliente cliente) {
        long minutosEstacionado = cliente.calcularTempoEstacionado();
        double valor = (minutosEstacionado / 15) * 4.0;
        if (valor > 50.0) valor = 50.0;
        return valor;
    }

    // Método para buscar uma vaga por sua identificação
    public Vaga getVagaPorIdentificacao(String identificacao) {
        return vagas.get(identificacao);
    }

    // Classe interna para registrar as saídas
    private static class RegistroSaida {
        private LocalDateTime dataSaida;
        private double valor;
        private String cpfCliente;

        public RegistroSaida(LocalDateTime dataSaida, double valor, String cpfCliente) {
            this.dataSaida = dataSaida;
            this.valor = valor;
            this.cpfCliente = cpfCliente;
        }

        public LocalDateTime getDataSaida() {
            return dataSaida;
        }

        public double getValor() {
            return valor;
        }

        public String getCpfCliente() {
            return cpfCliente;
        }
    }
}
