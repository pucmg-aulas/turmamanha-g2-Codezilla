package org.example.view;

import org.example.controller.EstacionamentoController;

import javax.swing.*;
import java.awt.*;

public class HistoricoView extends JFrame {
    private EstacionamentoController controller;

    public HistoricoView(EstacionamentoController controller) {
        this.controller = controller;
        setTitle("Histórico de Uso");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(60, 63, 65));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Histórico de Uso", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea historicoTextArea = new JTextArea();
        historicoTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        historicoTextArea.setEditable(false);
        historicoTextArea.setBackground(new Color(43, 43, 43));
        historicoTextArea.setForeground(Color.WHITE);


        JScrollPane scrollPane = new JScrollPane(historicoTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }
}