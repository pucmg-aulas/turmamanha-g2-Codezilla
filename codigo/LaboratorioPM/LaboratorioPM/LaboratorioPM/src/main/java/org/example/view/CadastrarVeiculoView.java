package org.example.view;

import org.example.controller.CadastrarVeiculoController;
import org.example.model.Veiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarVeiculoView extends JFrame {
    private Veiculo veiculo;
    private CadastrarVeiculoController controller;
    private String clienteId;
    private int vagaId;
    private String nomeParque;

    public CadastrarVeiculoView(String clienteId, int vagaId, String nomeParque) {
        this.controller = new CadastrarVeiculoController();
        this.clienteId = clienteId;
        this.vagaId = vagaId;
        this.nomeParque = nomeParque;
        setTitle("Cadastrar Veículo");
        setSize(300, 200);
        setLayout(new GridLayout(2, 2));

        JLabel placaLabel = new JLabel("Placa:");
        JTextField placaField = new JTextField();
        JButton cadastrarButton = new JButton("Cadastrar");

        add(placaLabel);
        add(placaField);
        add(new JLabel());
        add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    veiculo = controller.cadastrarVeiculo(placaField.getText(), clienteId, vagaId, nomeParque);
                    JOptionPane.showMessageDialog(null, "Veículo cadastrado: " + veiculo.getPlaca());
                    dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
}