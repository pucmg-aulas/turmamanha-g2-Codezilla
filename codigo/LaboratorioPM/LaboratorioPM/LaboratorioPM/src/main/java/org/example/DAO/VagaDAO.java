package org.example.DAO;

import org.example.model.Vaga;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VagaDAO {
    public void salvarVaga(Vaga vaga) throws SQLException {
        String sql = "INSERT INTO Vaga (numero, ocupada, cliente_id, placa, nome_parque, estacionamento_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vaga.getNumero());
            statement.setBoolean(2, vaga.isOcupada());
            statement.setObject(3, vaga.getCliente() != null ? vaga.getCliente().getId() : null);
            statement.setString(4, vaga.getCliente() != null ? vaga.getPlacaVeiculo() : null);
            statement.setString(5, vaga.getNomeParque()); // Set nome_parque
            statement.setInt(6, vaga.getEstacionamentoId());
            statement.executeUpdate();
        }
    }
}