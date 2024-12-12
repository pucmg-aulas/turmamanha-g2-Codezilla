package org.example.controller;

import org.example.DAO.CadastrarVeiculoDAO;
import org.example.DAO.OcuparVagaDAO;
import org.example.model.Veiculo;

import java.sql.SQLException;

public class CadastrarVeiculoController {
    private CadastrarVeiculoDAO veiculoDAO;
    private OcuparVagaDAO ocuparVagaDAO;

    public CadastrarVeiculoController() {
        this.veiculoDAO = new CadastrarVeiculoDAO();
        this.ocuparVagaDAO = new OcuparVagaDAO();
    }

    public Veiculo cadastrarVeiculo(String placa, String clienteIdentificador, int vagaId, String nomeParque) {
        if (placa != null && !placa.trim().isEmpty()) {
            Veiculo veiculo = new Veiculo(placa);
            try {
                veiculoDAO.salvarVeiculo(veiculo, clienteIdentificador);
                int clienteId = Integer.parseInt(clienteIdentificador); // Converte para inteiro
                ocuparVagaDAO.ocuparVaga(vagaId, clienteId, placa, nomeParque);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao salvar veículo no banco de dados.", e);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Identificador do cliente deve ser um número.", e);
            }
            return veiculo;
        } else {
            throw new IllegalArgumentException("Placa não pode ser vazia.");
        }
    }
}