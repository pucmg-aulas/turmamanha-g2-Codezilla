package org.example.view;

import org.example.DAO.ClienteDAO;
import org.example.DAO.VeiculoDAO;
import org.example.controller.EstacionamentoController;
import org.example.exceptions.PlacaExistenteException;
import org.example.model.Cliente;
import org.example.model.Estacionamento;
import org.example.model.Vaga;
import org.example.model.Veiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class OcuparVagaView extends JFrame {
    private EstacionamentoController controller;
    private Estacionamento estacionamento;
    private Vaga vaga;
    private VeiculoDAO veiculoDAO;
    private ClienteDAO clienteDAO;

    public OcuparVagaView(EstacionamentoController controller, Estacionamento estacionamento, Vaga vaga) {
        this.controller = controller;
        this.estacionamento = estacionamento;
        this.vaga = vaga;
        this.veiculoDAO = new VeiculoDAO();
        this.clienteDAO = new ClienteDAO();
        setTitle("Ocupar Vaga " + vaga.getNumero());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel placaLabel = new JLabel("Placa:");
        JTextField placaField = new JTextField();
        JButton ocuparButton = new JButton("Ocupar");

        ocuparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText();
                if (!placa.isEmpty()) {
                    try {
                        if (!veiculoDAO.veiculoExiste(placa)) {
                            // Open the client registration view
                            CadastrarClienteView cadastrarClienteView = new CadastrarClienteView(placa);
                            cadastrarClienteView.setVisible(true);
                        } else {
                            // Retrieve the client associated with the vehicle
                            String clienteIdentificador = veiculoDAO.getClienteIdentificadorByPlaca(placa);
                            if (!clienteDAO.clienteExiste(clienteIdentificador)) {
                                // Open the client registration view
                                CadastrarClienteView cadastrarClienteView = new CadastrarClienteView(placa);
                                cadastrarClienteView.setVisible(true);
                            } else {
                                // Retrieve the client and save the vehicle and client details in the database
                                Cliente cliente = clienteDAO.getClienteByIdentificador(clienteIdentificador);
                                Veiculo veiculo = new Veiculo(placa);
                                cliente.adicionarVeiculo(veiculo);
                                controller.ocuparVaga(controller.getEstacionamentos().indexOf(estacionamento), Integer.parseInt(vaga.getNumero().substring(1)) - 1, cliente, placa);
                                dispose();
                            }
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao verificar ou salvar o veículo: " + ex.getMessage());
                    } catch (PlacaExistenteException ex) {
                        JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "O campo de placa deve ser preenchido.");
                }
            }
        });

        add(placaLabel);
        add(placaField);
        add(new JLabel()); // Empty cell
        add(ocuparButton);
    }
}