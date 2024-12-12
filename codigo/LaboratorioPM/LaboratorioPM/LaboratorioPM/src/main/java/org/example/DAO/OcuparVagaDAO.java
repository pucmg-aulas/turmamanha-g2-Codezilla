package org.example.DAO;

import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OcuparVagaDAO {
    private static final Logger logger = Logger.getLogger(OcuparVagaDAO.class.getName());

    public void ocuparVaga(int numeroVaga, int clienteId, String placa, String nomeParque) throws SQLException {
        String query = "UPDATE Vaga SET ocupada = TRUE, cliente_id = ?, placa = ? WHERE numero = ? AND nome_parque = ?";
        logger.log(Level.INFO, "Ocupando vaga: numeroVaga={0}, clienteId={1}, placa={2}, nomeParque={3}",
                new Object[]{numeroVaga, clienteId, placa, nomeParque});
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setString(2, placa);
            statement.setString(3, "V" + numeroVaga); // Assuming the format of the spot number is "V" followed by the number
            statement.setString(4, nomeParque);
            int rowsUpdated = statement.executeUpdate();
            logger.log(Level.INFO, "Rows updated: {0}", rowsUpdated);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao ocupar vaga", e);
            throw e;
        }
    }
}