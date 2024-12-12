package org.example.DAO;

import org.example.model.Veiculo;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarVeiculoDAO {
    public void salvarVeiculo(Veiculo veiculo, String clienteIdentificador) throws SQLException {
        String sql = "INSERT INTO Veiculo (placa, cliente_identificador) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, clienteIdentificador);
            statement.executeUpdate();
        }
    }
}