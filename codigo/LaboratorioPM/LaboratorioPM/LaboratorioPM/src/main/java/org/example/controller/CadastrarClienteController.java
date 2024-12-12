package org.example.controller;

import org.example.DAO.CadastrarClienteDAO;
import org.example.DAO.CadastrarVeiculoDAO;
import org.example.exceptions.IdentificadorExistenteException;
import org.example.model.Cliente;
import org.example.model.Veiculo;

import java.sql.SQLException;

public class CadastrarClienteController {
    private CadastrarClienteDAO clienteDAO;
    private CadastrarVeiculoDAO veiculoDAO;

    public CadastrarClienteController() {
        this.clienteDAO = new CadastrarClienteDAO();
        this.veiculoDAO = new CadastrarVeiculoDAO();
    }

    public Cliente cadastrarCliente(String nome, String identificador, String placaVeiculo) throws IdentificadorExistenteException {
        if (nome != null && !nome.trim().isEmpty() && identificador != null && !identificador.trim().isEmpty() && placaVeiculo != null && !placaVeiculo.trim().isEmpty()) {
            Cliente cliente = new Cliente(0, nome, identificador); // Use the correct constructor
            Veiculo veiculo = new Veiculo(placaVeiculo);
            cliente.adicionarVeiculo(veiculo);
            try {
                if (clienteDAO.buscarClientePorIdentificador(identificador) == null) {
                    clienteDAO.salvarCliente(cliente);
                    veiculoDAO.salvarVeiculo(veiculo, cliente.getIdentificador());
                } else {
                    throw new IdentificadorExistenteException("O identificador " + identificador + " já está cadastrado.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao salvar cliente ou veículo no banco de dados.", e);
            }
            return cliente;
        } else {
            throw new IllegalArgumentException("Nome, Identificador e Placa do Veículo não podem ser vazios.");
        }
    }
}