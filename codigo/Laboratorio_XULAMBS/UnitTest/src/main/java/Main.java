import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private static int contadorAnonimos = 1; // Contador para clientes anônimos

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializa o parque de estacionamento com as vagas predefinidas
        ParqueDeEstacionamento estacionamento = new ParqueDeEstacionamento();

        boolean adicionarOutroVeiculo;
        String nome;
        String cpf;
        TipoCliente tipoCliente;

        // Pergunta se o cliente deseja ser anônimo
        System.out.print("Deseja permanecer anônimo? (sim/não): ");
        boolean isAnonimo = scanner.nextLine().equalsIgnoreCase("sim");

        if (isAnonimo) {
            // Cliente é anônimo, gera identificação alfanumérica
            nome = "Anonimo_" + String.format("%02d", contadorAnonimos);
            cpf = "N/A"; // Não necessário para clientes anônimos
            contadorAnonimos++; // Incrementa o contador para o próximo cliente anônimo
        } else {
            // Solicita o nome do cliente
            System.out.print("Digite o nome do cliente: ");
            nome = scanner.nextLine();

            // Solicita o CPF do cliente
            System.out.print("Digite o CPF do cliente: ");
            cpf = scanner.nextLine();
        }

        // Solicita o tipo de cliente
        System.out.print("Digite o tipo de cliente (REGULAR, VIP, PCD, IDOSO): ");
        tipoCliente = TipoCliente.valueOf(scanner.nextLine().toUpperCase());

        do {
            // Solicita a placa do veículo
            System.out.print("Digite a placa do veículo: ");
            String placa = scanner.nextLine();

            // Solicita o modelo do veículo
            System.out.print("Digite o modelo do veículo: ");
            String modelo = scanner.nextLine();

            // Cria o cliente com as informações fornecidas
            Cliente cliente = new Cliente(nome, cpf, tipoCliente, placa, modelo);

            // Exibe as vagas disponíveis para o tipo selecionado
            System.out.println("Vagas disponíveis para o tipo " + tipoCliente + ":");
            for (String vagaId : estacionamento.getVagasDisponiveis(tipoCliente.name())) {
                System.out.print(vagaId + " ");
            }
            System.out.println();

            // Solicita que o cliente escolha uma vaga específica
            System.out.print("Escolha uma vaga da lista acima: ");
            String vagaEscolhida = scanner.nextLine();

            try {
                // Tenta alocar a vaga específica escolhida pelo cliente
                Vaga vaga = estacionamento.alocarVagaEspecifica(vagaEscolhida);
                System.out.println("Vaga alocada: " + vaga.getIdentificacao() + " (" + tipoCliente + ")");

                // Define o horário de entrada automaticamente
                LocalDateTime entrada = LocalDateTime.now();
                cliente.setHorarioEntrada(entrada.toLocalTime());
                System.out.printf("Horário de entrada registrado: %s%n", entrada.toLocalTime());

                // Simula a permanência no estacionamento
                System.out.print("Pressione Enter para registrar a saída...");
                scanner.nextLine();

                // Define o horário de saída automaticamente
                LocalDateTime saida = LocalDateTime.now();
                cliente.setHorarioSaida(saida.toLocalTime());
                System.out.printf("Horário de saída registrado: %s%n", saida.toLocalTime());

                // Calcula o valor da cobrança
                estacionamento.registrarSaida(cliente, vaga, saida);
                long tempoEstacionado = cliente.calcularTempoEstacionado();
                double valor = estacionamento.calcularCobrancaPorTempo(cliente);

                // Exibe as informações detalhadas
                System.out.println("\n--- Informações da Cobrança ---");
                System.out.printf("Nome do Cliente: %s%n", cliente.getNome());
                System.out.printf("CPF do Cliente: %s%n", cliente.getCpf());
                System.out.printf("Tipo de Cliente: %s%n", cliente.getTipoCliente());
                System.out.printf("Placa do Veículo: %s%n", cliente.getPlaca());
                System.out.printf("Modelo do Veículo: %s%n", cliente.getModelo());
                System.out.printf("Tempo Estacionado: %d minutos%n", tempoEstacionado);
                System.out.printf("Vaga Utilizada: %s (%s)%n", vaga.getIdentificacao(), tipoCliente);
                System.out.printf("Valor a Pagar: R$ %.2f%n", valor);

            } catch (IllegalStateException ex) {
                System.out.println("Erro: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Erro: A vaga escolhida não existe ou não está disponível.");
            }

            // Pergunta se deseja adicionar outro veículo
            System.out.print("Deseja adicionar outro veículo ao estacionamento? (sim/não): ");
            adicionarOutroVeiculo = scanner.nextLine().equalsIgnoreCase("sim");

        } while (adicionarOutroVeiculo);

        scanner.close();
    }
}
