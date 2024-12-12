package org.example.DAO;

import org.example.model.Cliente;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public boolean clienteExiste(String identificador) throws SQLException {
        String query = "SELECT COUNT(*) FROM Cliente WHERE identificador = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, identificador);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void salvarCliente(String identificador, String nome) throws SQLException {
        String query = "INSERT INTO Cliente (identificador, nome) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, identificador);
            statement.setString(2, nome);
            statement.executeUpdate();
        }
    }

    public Cliente getClienteByIdentificador(String identificador) throws SQLException {
        String query = "SELECT id, nome, identificador FROM Cliente WHERE identificador = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, identificador);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Cliente(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("identificador"));
                }
            }
        }
        return null;
    }
}