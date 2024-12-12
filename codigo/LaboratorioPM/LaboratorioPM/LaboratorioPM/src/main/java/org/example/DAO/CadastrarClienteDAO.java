package org.example.DAO;

import org.example.model.Cliente;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastrarClienteDAO {
    public void salvarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, identificador) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getIdentificador());
            statement.executeUpdate();
        }
    }

    public Cliente buscarClientePorIdentificador(String identificador) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE identificador = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, identificador);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    return new Cliente(id, nome, identificador);
                }
            }
        }
        return null;
    }
}