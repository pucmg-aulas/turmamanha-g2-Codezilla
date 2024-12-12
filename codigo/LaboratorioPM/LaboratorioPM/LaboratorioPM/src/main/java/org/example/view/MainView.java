package org.example.view;

import org.example.controller.EstacionamentoController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private EstacionamentoController controller;

    public MainView(EstacionamentoController controller) {
        this.controller = controller;
        setTitle("Xulambs Parking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Top, left, bottom, right padding

        JButton selecionarParqueButton = new JButton("Selecionar Parque");
        selecionarParqueButton.setPreferredSize(new Dimension(200, 50));
        selecionarParqueButton.setFont(new Font("Arial", Font.BOLD, 16));
        selecionarParqueButton.setBackground(new Color(75, 110, 175));
        selecionarParqueButton.setForeground(Color.WHITE);
        selecionarParqueButton.setFocusPainted(false);
        selecionarParqueButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        selecionarParqueButton.addActionListener(e -> {
            SelecionarParqueView selecionarParqueView = new SelecionarParqueView(controller);
            selecionarParqueView.setVisible(true);
        });

        JButton visualizarHistoricoButton = new JButton("Visualizar Histórico");
        visualizarHistoricoButton.setPreferredSize(new Dimension(200, 50));
        visualizarHistoricoButton.setFont(new Font("Arial", Font.BOLD, 16));
        visualizarHistoricoButton.setBackground(new Color(75, 110, 175));
        visualizarHistoricoButton.setForeground(Color.WHITE);
        visualizarHistoricoButton.setFocusPainted(false);
        visualizarHistoricoButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        visualizarHistoricoButton.addActionListener(e -> {
            HistoricoView historicoView = new HistoricoView(controller);
            historicoView.setVisible(true);
        });

        JButton acessarAdminButton = new JButton("Acessar como Admin");
        acessarAdminButton.setPreferredSize(new Dimension(200, 50));
        acessarAdminButton.setFont(new Font("Arial", Font.BOLD, 16));
        acessarAdminButton.setBackground(new Color(75, 110, 175));
        acessarAdminButton.setForeground(Color.WHITE);
        acessarAdminButton.setFocusPainted(false);
        acessarAdminButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        acessarAdminButton.addActionListener(e -> {
            String password = JOptionPane.showInputDialog(this, "Digite a senha de admin:");
            if ("12345".equals(password)) {
                JOptionPane.showMessageDialog(this, "Acesso concedido!");
                AdminView adminView = new AdminView(controller);
                adminView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(selecionarParqueButton, gbc);

        gbc.gridy = 1;
        panel.add(visualizarHistoricoButton, gbc);

        gbc.gridy = 2;
        panel.add(acessarAdminButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EstacionamentoController controller = new EstacionamentoController();
        MainView mainView = new MainView(controller);
        mainView.setVisible(true);
    }
}