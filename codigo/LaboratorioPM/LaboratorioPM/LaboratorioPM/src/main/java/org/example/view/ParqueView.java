package org.example.view;

import org.example.controller.EstacionamentoController;
import org.example.model.Estacionamento;
import org.example.model.Vaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ParqueView extends JFrame {
    private EstacionamentoController controller;
    private Estacionamento estacionamento;

    public ParqueView(EstacionamentoController controller, Estacionamento estacionamento) {
        this.controller = controller;
        this.estacionamento = estacionamento;
        setTitle(estacionamento.getNome());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel legendPanel = createLegendPanel();
        add(legendPanel, BorderLayout.NORTH);

        JPanel vagasPanel = new JPanel();
        vagasPanel.setLayout(new GridLayout(4, 5));

        // Sort the spots by their number
        List<Vaga> sortedVagas = estacionamento.getVagas().stream()
                .sorted((v1, v2) -> v1.getNumero().compareTo(v2.getNumero()))
                .collect(Collectors.toList());

        for (Vaga vaga : sortedVagas) {
            JButton vagaButton = new JButton(vaga.getNumero());
            vagaButton.setBackground(vaga.isOcupada() ? Color.RED : vaga.getCor());
            vagaButton.setEnabled(true);
            vagaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (vaga.isOcupada()) {
                        JOptionPane.showMessageDialog(null, "Vaga já ocupada.");
                    } else {
                        OcuparVagaView ocuparVagaView = new OcuparVagaView(controller, estacionamento, vaga);
                        ocuparVagaView.setVisible(true);
                        ocuparVagaView.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                if (vaga.isOcupada()) {
                                    vagaButton.setBackground(Color.RED);
                                }
                            }
                        });
                    }
                }
            });
            vagasPanel.add(vagaButton);
        }

        add(vagasPanel, BorderLayout.CENTER);
    }

    private JPanel createLegendPanel() {
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new GridLayout(1, 4, 10, 10));
        legendPanel.setBackground(new Color(60, 63, 65));
        legendPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        legendPanel.add(createLegendLabel("REGULAR", Color.GREEN));
        legendPanel.add(createLegendLabel("PCD", Color.YELLOW));
        legendPanel.add(createLegendLabel("IDOSO", Color.ORANGE));
        legendPanel.add(createLegendLabel("VIP", Color.BLUE));

        return legendPanel;
    }

    private JLabel createLegendLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return label;
    }
}