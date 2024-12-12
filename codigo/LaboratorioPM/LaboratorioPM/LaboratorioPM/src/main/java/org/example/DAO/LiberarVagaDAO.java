package org.example.DAO;

import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LiberarVagaDAO {
    public void liberarVaga(int numeroVaga) throws SQLException {
        String sql = "UPDATE Vaga SET ocupada = FALSE, cliente_id = NULL, placa = NULL WHERE numero = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(numeroVaga));
            statement.executeUpdate();
        }
    }
}