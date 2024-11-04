import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstacionamentoGUI extends JFrame {
    private ParqueDeEstacionamento estacionamento;
    private static int contadorAnonimos = 1;
    private JTextField placaField;
    private JTextField modeloField;
    private JTextField nomeField;
    private JTextField cpfField;
    private JComboBox<String> tipoClienteComboBox;
    private JCheckBox anonimoCheckBox;
    private JComboBox<String> vagaComboBox;
    private JTextArea outputArea;
    private List<Cliente> clientes = new ArrayList<>();
    private Map<String, List<Cliente>> historicoClientes = new HashMap<>();

    public EstacionamentoGUI() {
        estacionamento = new ParqueDeEstacionamento();

        setTitle("XULAMBS PARKING");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("XULAMBS PARKING", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(15, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        anonimoCheckBox = new JCheckBox("Permanecer Anônimo");
        inputPanel.add(new JLabel("Deseja permanecer anônimo?"));
        inputPanel.add(anonimoCheckBox);

        tipoClienteComboBox = new JComboBox<>(new String[]{"REGULAR", "VIP", "PCD", "IDOSO"});
        inputPanel.add(new JLabel("Tipo de Cliente:"));
        inputPanel.add(tipoClienteComboBox);

        nomeField = new JTextField();
        inputPanel.add(new JLabel("Nome do Cliente:"));
        inputPanel.add(nomeField);

        cpfField = new JTextField();
        inputPanel.add(new JLabel("CPF do Cliente:"));
        inputPanel.add(cpfField);

        placaField = new JTextField();
        inputPanel.add(new JLabel("Placa do Veículo:"));
        inputPanel.add(placaField);

        modeloField = new JTextField();
        inputPanel.add(new JLabel("Modelo do Veículo:"));
        inputPanel.add(modeloField);

        vagaComboBox = new JComboBox<>();
        inputPanel.add(new JLabel("Escolha uma Vaga:"));
        inputPanel.add(vagaComboBox);

        JButton registrarEntradaButton = new JButton("Registrar Entrada");
        registrarEntradaButton.setBackground(new Color(34, 139, 34));
        registrarEntradaButton.setForeground(Color.WHITE);
        inputPanel.add(registrarEntradaButton);

        JButton registrarSaidaButton = new JButton("Registrar Saída");
        registrarSaidaButton.setBackground(new Color(220, 20, 60));
        registrarSaidaButton.setForeground(Color.WHITE);
        inputPanel.add(registrarSaidaButton);

        JButton consultarHistoricoButton = new JButton("Consultar Histórico por CPF");
        consultarHistoricoButton.setBackground(new Color(30, 144, 255));
        consultarHistoricoButton.setForeground(Color.WHITE);
        inputPanel.add(consultarHistoricoButton);

        JButton mostrarTotalButton = new JButton("Mostrar Total Arrecadado");
        mostrarTotalButton.setBackground(new Color(255, 165, 0));
        mostrarTotalButton.setForeground(Color.WHITE);
        inputPanel.add(mostrarTotalButton);

        JButton consultarArrecadacaoMensalButton = new JButton("Consultar Arrecadação Mensal");
        consultarArrecadacaoMensalButton.setBackground(new Color(255, 140, 0));
        consultarArrecadacaoMensalButton.setForeground(Color.WHITE);
        inputPanel.add(consultarArrecadacaoMensalButton);

        JButton valorMedioButton = new JButton("Mostrar Valor Médio por Utilização");
        valorMedioButton.setBackground(new Color(75, 0, 130));
        valorMedioButton.setForeground(Color.WHITE);
        inputPanel.add(valorMedioButton);

        JButton rankingButton = new JButton("Consultar Ranking de Arrecadação");
        rankingButton.setBackground(new Color(128, 0, 0));
        rankingButton.setForeground(Color.WHITE);
        inputPanel.add(rankingButton);

        add(inputPanel, BorderLayout.CENTER);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        registrarEntradaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarEntrada();
            }
        });

        registrarSaidaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarSaida();
            }
        });

        consultarHistoricoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirJanelaConsultaHistorico();
            }
        });

        mostrarTotalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double totalArrecadado = estacionamento.getTotalArrecadado();
                JOptionPane.showMessageDialog(EstacionamentoGUI.this,
                        "Valor Total Arrecadado: R$ " + String.format("%.2f", totalArrecadado),
                        "Total Arrecadado", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        consultarArrecadacaoMensalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirJanelaConsultaArrecadacaoMensal();
            }
        });

        valorMedioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double valorMedio = estacionamento.getValorMedioUtilizacao();
                JOptionPane.showMessageDialog(EstacionamentoGUI.this,
                        "Valor Médio por Utilização: R$ " + String.format("%.2f", valorMedio),
                        "Valor Médio por Utilização", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirJanelaRankingClientes();
            }
        });

        tipoClienteComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarVagasDisponiveis();
            }
        });

        anonimoCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isAnonimo = anonimoCheckBox.isSelected();
                nomeField.setEnabled(!isAnonimo);
                cpfField.setEnabled(!isAnonimo);
                if (isAnonimo) {
                    nomeField.setText("");
                    cpfField.setText("");
                }
            }
        });

        nomeField.setEnabled(!anonimoCheckBox.isSelected());
        cpfField.setEnabled(!anonimoCheckBox.isSelected());

        atualizarVagasDisponiveis();
    }

    private void atualizarVagasDisponiveis() {
        String tipoCliente = (String) tipoClienteComboBox.getSelectedItem();
        List<String> vagasDisponiveis = estacionamento.getVagasDisponiveis(tipoCliente);
        vagaComboBox.removeAllItems();
        for (String vaga : vagasDisponiveis) {
            vagaComboBox.addItem(vaga);
        }
    }

    private void registrarEntrada() {
        if (!anonimoCheckBox.isSelected()) {
            if (nomeField.getText().trim().isEmpty() || cpfField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (placaField.getText().trim().isEmpty() || modeloField.getText().trim().isEmpty() || vagaComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nome;
        String cpf;
        boolean isAnonimo = anonimoCheckBox.isSelected();
        String tipoCliente = (String) tipoClienteComboBox.getSelectedItem();
        String placa = placaField.getText();
        String modelo = modeloField.getText();
        String vagaSelecionada = (String) vagaComboBox.getSelectedItem();

        if (isAnonimo) {
            nome = "Anonimo_" + String.format("%02d", contadorAnonimos);
            cpf = "N/A";
            contadorAnonimos++;
        } else {
            nome = nomeField.getText();
            cpf = cpfField.getText();
        }

        Cliente cliente = new Cliente(nome, cpf, TipoCliente.valueOf(tipoCliente), placa, modelo);
        cliente.setHorarioEntrada(java.time.LocalTime.now());
        cliente.setVagaOcupada(vagaSelecionada);

        try {
            Vaga vaga = estacionamento.getVagaPorIdentificacao(vagaSelecionada);
            if (vaga == null || !vaga.isDisponivel()) {
                JOptionPane.showMessageDialog(this, "A vaga " + vagaSelecionada + " já está ocupada ou não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            vaga.setDisponivel(false);
            clientes.add(cliente);

            historicoClientes.putIfAbsent(cpf, new ArrayList<>());
            historicoClientes.get(cpf).add(cliente);

            outputArea.append("Vaga " + vagaSelecionada + " alocada para " + cliente.getNome() + " (" + tipoCliente + ")\n");
            outputArea.append("Horário de entrada registrado: " + cliente.getHorarioEntrada() + "\n\n");
        } catch (IllegalStateException ex) {
            outputArea.append("Erro ao registrar entrada: " + ex.getMessage() + "\n");
        }
    }

    private void registrarSaida() {
        String placaSaida = JOptionPane.showInputDialog(this, "Digite a placa do veículo para registrar a saída:");
        if (placaSaida == null || placaSaida.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Placa não informada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getPlaca().equalsIgnoreCase(placaSaida)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Veículo com a placa " + placaSaida + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cliente.setHorarioSaida(java.time.LocalTime.now());
        Vaga vaga = estacionamento.getVagaPorIdentificacao(cliente.getVagaOcupada());

        if (vaga == null) {
            JOptionPane.showMessageDialog(this, "Erro: Vaga não encontrada ou não atribuída corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        estacionamento.registrarSaida(cliente, vaga, LocalDateTime.now());
        double valor = estacionamento.calcularCobrancaPorTempo(cliente);

        outputArea.append("--- Informações da Cobrança ---\n");
        outputArea.append("Nome do Cliente: " + cliente.getNome() + "\n");
        outputArea.append("CPF do Cliente: " + cliente.getCpf() + "\n");
        outputArea.append("Placa do Veículo: " + cliente.getPlaca() + "\n");
        outputArea.append("Tempo Estacionado: " + cliente.calcularTempoEstacionado() + " minutos\n");
        outputArea.append("Valor a Pagar: R$ " + String.format("%.2f", valor) + "\n\n");

        vaga.setDisponivel(true);
        clientes.remove(cliente);

        String conteudo = String.format(
                "Nome do Cliente: %s%nCPF do Cliente: %s%nPlaca do Veículo: %s%nTempo Estacionado: %d minutos%nValor a Pagar: R$ %.2f%n---",
                cliente.getNome(), cliente.getCpf(), cliente.getPlaca(), cliente.calcularTempoEstacionado(), valor
        );
        salvarEmArquivo(conteudo);
    }

    private void salvarEmArquivo(String conteudo) {
        String nomeArquivo = "C:/Users/vinic/OneDrive/Documentos/informacoes_estacionamento.txt";
        System.out.println("Tentando salvar em: " + nomeArquivo); // Exibe o caminho para confirmar

        try (FileWriter writer = new FileWriter(nomeArquivo, true)) {
            writer.write(conteudo + System.lineSeparator());
            JOptionPane.showMessageDialog(this, "Informações salvas com sucesso na pasta Documentos!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar informações: " + e.getMessage());
        }
    }



    private void abrirJanelaConsultaHistorico() {
        String cpf = JOptionPane.showInputDialog(this, "Digite o CPF para consulta do histórico:");
        if (cpf != null && !cpf.isEmpty()) {
            List<Cliente> historico = historicoClientes.get(cpf);
            if (historico != null) {
                StringBuilder historicoTexto = new StringBuilder("Histórico do cliente " + cpf + ":\n");
                for (Cliente cliente : historico) {
                    historicoTexto.append("Veículo: ").append(cliente.getPlaca())
                            .append(" | Entrada: ").append(cliente.getHorarioEntrada())
                            .append(" | Saída: ").append(cliente.getHorarioSaida()).append("\n");
                }
                JOptionPane.showMessageDialog(this, historicoTexto.toString(), "Consulta de Histórico", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum histórico encontrado para o CPF informado.", "Consulta de Histórico", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void abrirJanelaConsultaArrecadacaoMensal() {
        String mesStr = JOptionPane.showInputDialog(this, "Digite o mês (1-12) para consulta de arrecadação:");
        String anoStr = JOptionPane.showInputDialog(this, "Digite o ano para consulta de arrecadação:");
        try {
            int mes = Integer.parseInt(mesStr);
            int ano = Integer.parseInt(anoStr);
            double totalArrecadado = estacionamento.getTotalArrecadadoMes(mes, ano);
            JOptionPane.showMessageDialog(this, "Arrecadação para " + mes + "/" + ano + ": R$ " + String.format("%.2f", totalArrecadado),
                    "Consulta de Arrecadação Mensal", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, digite números válidos para o mês e ano.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirJanelaRankingClientes() {
        String mesStr = JOptionPane.showInputDialog(this, "Digite o mês (1-12) para consulta do ranking:");
        String anoStr = JOptionPane.showInputDialog(this, "Digite o ano para consulta do ranking:");
        try {
            int mes = Integer.parseInt(mesStr);
            int ano = Integer.parseInt(anoStr);
            List<Map.Entry<String, Double>> ranking = estacionamento.obterRankingClientes(mes, ano);

            if (ranking.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado para o período selecionado.", "Ranking de Arrecadação", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder rankingTexto = new StringBuilder("Ranking de Clientes - " + mes + "/" + ano + ":\n");
                for (Map.Entry<String, Double> entry : ranking) {
                    rankingTexto.append("CPF: ").append(entry.getKey())
                            .append(" - Arrecadação: R$ ")
                            .append(String.format("%.2f", entry.getValue()))
                            .append("\n");
                }
                JOptionPane.showMessageDialog(this, rankingTexto.toString(), "Ranking de Arrecadação", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, digite números válidos para o mês e ano.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstacionamentoGUI().setVisible(true));
    }
}
