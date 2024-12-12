package org.example.DAO;

import org.example.model.Estacionamento;
import org.example.model.Vaga;
import org.example.model.VagaFactory;
import org.example.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoDAO {
    public List<Estacionamento> getAllEstacionamentos() throws SQLException {
        List<Estacionamento> estacionamentos = new ArrayList<>();
        String query = "SELECT id, nome FROM Estacionamento";

        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                Estacionamento estacionamento = new Estacionamento(id, nome);
                estacionamento.setVagas(getVagasByEstacionamentoId(id));
                estacionamentos.add(estacionamento);
            }
        }
        return estacionamentos;
    }

    private List<Vaga> getVagasByEstacionamentoId(int estacionamentoId) throws SQLException {
        List<Vaga> vagas = new ArrayList<>();
        String query = "SELECT id, numero, estacionamento_id, tipo, nome_parque FROM Vaga WHERE estacionamento_id = ?";

        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, estacionamentoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String numero = resultSet.getString("numero");
                    String tipo = resultSet.getString("tipo");
                    String nomeParque = resultSet.getString("nome_parque");
                    Vaga vaga = VagaFactory.createVaga(id, numero, estacionamentoId, tipo, nomeParque);
                    vagas.add(vaga);
                }
            }
        }
        return vagas;
    }
}