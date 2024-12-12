package org.example.view;

import org.example.controller.EstacionamentoController;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame {
    private EstacionamentoController controller;

    public AdminView(EstacionamentoController controller) {
        this.controller = controller;
        setTitle("Admin View");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Top, left, bottom, right padding

        JLabel titleLabel = new JLabel("Admin View", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        JButton manageUsersButton = new JButton("Total arrecadado:");
        manageUsersButton.setPreferredSize(new Dimension(200, 50));
        manageUsersButton.setFont(new Font("Arial", Font.BOLD, 16));
        manageUsersButton.setBackground(new Color(75, 110, 175));
        manageUsersButton.setForeground(Color.WHITE);
        manageUsersButton.setFocusPainted(false);
        manageUsersButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridy = 1;
        panel.add(manageUsersButton, gbc);

        JButton manageParkingButton = new JButton("Total arrecadado por mês");
        manageParkingButton.setPreferredSize(new Dimension(200, 50));
        manageParkingButton.setFont(new Font("Arial", Font.BOLD, 16));
        manageParkingButton.setBackground(new Color(75, 110, 175));
        manageParkingButton.setForeground(Color.WHITE);
        manageParkingButton.setFocusPainted(false);
        manageParkingButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridy = 2;
        panel.add(manageParkingButton, gbc);

        JButton visualizarTicketMedioButton = new JButton("Visualizar Ticket Médio");
        visualizarTicketMedioButton.setPreferredSize(new Dimension(200, 50));
        visualizarTicketMedioButton.setFont(new Font("Arial", Font.BOLD, 16));
        visualizarTicketMedioButton.setBackground(new Color(75, 110, 175));
        visualizarTicketMedioButton.setForeground(Color.WHITE);
        visualizarTicketMedioButton.setFocusPainted(false);
        visualizarTicketMedioButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gbc.gridy = 3;
        panel.add(visualizarTicketMedioButton, gbc);

        JButton rankingClientesButton = new JButton("Ranking de Clientes");
        rankingClientesButton.setPreferredSize(new Dimension(200, 50));
        rankingClientesButton.setFont(new Font("Arial", Font.BOLD, 16));
        rankingClientesButton.setBackground(new Color(75, 110, 175));
        rankingClientesButton.setForeground(Color.WHITE);
        rankingClientesButton.setFocusPainted(false);
        rankingClientesButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gbc.gridy = 4;
        panel.add(rankingClientesButton, gbc);

        add(panel, BorderLayout.CENTER);
    }
}