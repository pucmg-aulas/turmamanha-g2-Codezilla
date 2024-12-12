package org.example.DAO;

import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoDAO {
    public boolean veiculoExiste(String placa) throws SQLException {
        String query = "SELECT COUNT(*) FROM Veiculo WHERE placa = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, placa);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void salvarVeiculo(String placa, String clienteIdentificador) throws SQLException {
        String query = "INSERT INTO Veiculo (placa, cliente_identificador) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, placa);
            statement.setString(2, clienteIdentificador);
            statement.executeUpdate();
        }
    }

    public String getClienteIdentificadorByPlaca(String placa) throws SQLException {
        String query = "SELECT cliente_identificador FROM Veiculo WHERE placa = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, placa);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("cliente_identificador");
                }
            }
        }
        return null;
    }
}