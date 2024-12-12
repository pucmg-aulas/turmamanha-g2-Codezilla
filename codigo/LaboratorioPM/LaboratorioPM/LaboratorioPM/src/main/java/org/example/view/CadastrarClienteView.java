package org.example.view;

import org.example.controller.CadastrarClienteController;
import org.example.exceptions.IdentificadorExistenteException;
import org.example.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarClienteView extends JFrame {
    private Cliente cliente;
    private CadastrarClienteController controller;
    private String placaVeiculo;

    public CadastrarClienteView(String placaVeiculo) {
        this.controller = new CadastrarClienteController();
        this.placaVeiculo = placaVeiculo;
        setTitle("Cadastrar Cliente");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel idLabel = new JLabel("Identificador:");
        JTextField idField = new JTextField();
        JButton cadastrarButton = new JButton("Cadastrar");

        add(nomeLabel);
        add(nomeField);
        add(idLabel);
        add(idField);
        add(new JLabel());
        add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente = controller.cadastrarCliente(nomeField.getText(), idField.getText(), placaVeiculo);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado: " + cliente.getNome());
                    dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IdentificadorExistenteException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });
    }

    public Cliente getCliente() {
        return cliente;
    }
}