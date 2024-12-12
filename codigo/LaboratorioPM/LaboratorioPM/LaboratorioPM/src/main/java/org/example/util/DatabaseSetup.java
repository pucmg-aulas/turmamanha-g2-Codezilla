package org.example.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void createTables() {
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            String createClienteTable = "CREATE TABLE IF NOT EXISTS Cliente (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "identificador VARCHAR(100) NOT NULL UNIQUE)";

            String createVeiculoTable = "CREATE TABLE IF NOT EXISTS Veiculo (" +
                    "id SERIAL PRIMARY KEY, " +
                    "placa VARCHAR(20) NOT NULL UNIQUE, " +
                    "cliente_identificador VARCHAR(100) REFERENCES Cliente(identificador))";

            String createEstacionamentoTable = "CREATE TABLE IF NOT EXISTS Estacionamento (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "quantidade_vagas INTEGER NOT NULL)";

            String createVagaTable = "CREATE TABLE IF NOT EXISTS Vaga (" +
                    "id SERIAL PRIMARY KEY, " +
                    "numero VARCHAR(10) NOT NULL, " +
                    "ocupada BOOLEAN NOT NULL, " +
                    "cliente_id INTEGER REFERENCES Cliente(id), " +
                    "placa VARCHAR(20), " +
                    "nome_parque VARCHAR(100) NOT NULL, " +
                    "tipo VARCHAR(20) NOT NULL, " + // Add this line
                    "estacionamento_id INTEGER REFERENCES Estacionamento(id))";

            statement.execute(createClienteTable);
            statement.execute(createVeiculoTable);
            statement.execute(createEstacionamentoTable);
            statement.execute(createVagaTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}