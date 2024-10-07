package src;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cadastro do cliente
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Digite o CPF do cliente: ");
        String cpfCliente = scanner.nextLine();

        System.out.println("Tipos de cliente: 1-Regular, 2-VIP, 3-Idoso, 4-PCD");
        System.out.print("Digite o tipo de cliente: ");
        int tipoClienteInput = scanner.nextInt();
        scanner.nextLine();  // Consome a quebra de linha

        TipoCliente tipoCliente;
        switch (tipoClienteInput) {
            case 2:
                tipoCliente = TipoCliente.VIP;
                break;
            case 3:
                tipoCliente = TipoCliente.IDOSO;
                break;
            case 4:
                tipoCliente = TipoCliente.PCD;
                break;
            default:
                tipoCliente = TipoCliente.REGULAR;
                break;
        }

        Cliente cliente = new Cliente("1", nomeCliente, cpfCliente, tipoCliente);

        // Cadastro do veiculo
        System.out.print("Digite a placa do veiculo: ");
        String placaVeiculo = scanner.nextLine();

        System.out.print("Digite o modelo do veiculo: ");
        String modeloVeiculo = scanner.nextLine();

        Veiculo veiculo = new Veiculo(placaVeiculo, modeloVeiculo);
        cliente.cadastrarVeiculo(veiculo);

        // Criacao do estacionamento com 5 vagas
        ParqueDeEstacionamento estacionamento = new ParqueDeEstacionamento("Xulambs", "Centro", 5);

        // Entrada de veiculo
        estacionamento.registrarEntradaVeiculo(veiculo, TipoVaga.REGULAR);

        // Gera tempo aleatorio de estacionamento entre 15 e 120 minutos
        Random random = new Random();
        int minutosAleatorios = 15 + random.nextInt(106); // Valor entre 15 e 120 minutos
        LocalDateTime tempoEntrada = LocalDateTime.now().minusMinutes(minutosAleatorios);

        // Saida do veiculo
        estacionamento.registrarSaidaVeiculo(veiculo, cliente, tempoEntrada);
    }
}