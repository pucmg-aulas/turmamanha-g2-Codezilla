// SelecionarParqueView.java
package org.example.view;

import org.example.controller.EstacionamentoController;
import org.example.model.Estacionamento;

import javax.swing.*;
import java.awt.*;

public class SelecionarParqueView extends JFrame {
    private EstacionamentoController controller;

    public SelecionarParqueView(EstacionamentoController controller) {
        this.controller = controller;
        setTitle("Selecionar Parque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(controller.getEstacionamentos().size(), 1, 10, 10));
        panel.setBackground(new Color(60, 63, 65));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Estacionamento estacionamento : controller.getEstacionamentos()) {
            JButton parqueButton = new JButton(estacionamento.getNome());
            parqueButton.setPreferredSize(new Dimension(200, 50));
            parqueButton.setFont(new Font("Arial", Font.BOLD, 16));
            parqueButton.setBackground(new Color(75, 110, 175));
            parqueButton.setForeground(Color.WHITE);
            parqueButton.setFocusPainted(false);
            parqueButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            parqueButton.addActionListener(e -> {
                ParqueView parqueView = new ParqueView(controller, estacionamento);
                parqueView.setVisible(true);
                dispose();
            });
            panel.add(parqueButton);
        }

        add(panel, BorderLayout.CENTER);
    }
}