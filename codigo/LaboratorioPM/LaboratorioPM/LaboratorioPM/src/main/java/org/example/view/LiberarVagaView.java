package org.example.view;

import org.example.controller.EstacionamentoController;
import org.example.model.Estacionamento;
import org.example.model.Vaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LiberarVagaView extends JFrame {
    private EstacionamentoController controller;
    private Estacionamento estacionamento;
    private Vaga vaga;

    public LiberarVagaView(EstacionamentoController controller, Estacionamento estacionamento, Vaga vaga) {
        this.controller = controller;
        this.estacionamento = estacionamento;
        this.vaga = vaga;
        setTitle("Liberar Vaga " + vaga.getNumero());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JLabel confirmLabel = new JLabel("Deseja liberar a vaga " + vaga.getNumero() + "?");
        JButton liberarButton = new JButton("Liberar");

        liberarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.liberarVaga(controller.getEstacionamentos().indexOf(estacionamento), Integer.parseInt(vaga.getNumero().substring(1)) - 1);
                dispose();
            }
        });

        add(confirmLabel);
        add(liberarButton);
    }
}