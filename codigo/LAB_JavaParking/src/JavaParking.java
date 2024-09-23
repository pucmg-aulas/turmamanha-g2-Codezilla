package src;

public class JavaParking {
    public static void main(String[] args) {
        // Criando um estacionamento
        Estacionamento estacionamento = new Estacionamento("001", "Estacionamento Central", "Rua Principal, 123");

        // Criando clientes
        Cliente cliente1 = new Cliente("001", "João Silva");
        Cliente cliente2 = new Cliente("002", "Maria Oliveira");

        // Criando veículos para os clientes
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Honda Civic", cliente1);
        Veiculo veiculo2 = new Veiculo("XYZ-9876", "Toyota Corolla", cliente2);

        // Associando os veículos aos clientes
        cliente1.cadastrarVeiculo(veiculo1);
        cliente2.cadastrarVeiculo(veiculo2);

        // Adicionando vagas ao estacionamento
        estacionamento.adicionarVaga(new Vaga("A01"));
        estacionamento.adicionarVaga(new Vaga("A02"));
        estacionamento.adicionarVaga(new Vaga("B01"));

        // Simulação de ocupação e liberação de vagas
        estacionamento.registrarEntradaVeiculo(veiculo1); // João estaciona
        estacionamento.registrarEntradaVeiculo(veiculo2); // Maria estaciona
        estacionamento.registrarSaidaVeiculo(veiculo1); // João sai
        estacionamento.registrarSaidaVeiculo(veiculo2); // Maria sai

        // Exemplo de cálculo de cobrança
        Cobranca cobranca = new Cobranca();
        int tempoOcupacao = 45; // 45 minutos
        double valor = cobranca.calcularValor(tempoOcupacao);
        System.out.println("Valor da cobrança para " + tempoOcupacao + " minutos: R$ " + valor);
    }
}
