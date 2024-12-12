package org.example.controller;

import org.example.DAO.EstacionamentoDAO;
import org.example.DAO.LiberarVagaDAO;
import org.example.DAO.OcuparVagaDAO;
import org.example.DAO.VeiculoDAO;
import org.example.exceptions.PlacaExistenteException;
import org.example.model.Cliente;
import org.example.model.Estacionamento;
import org.example.model.Vaga;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstacionamentoController {
    private static final Logger logger = Logger.getLogger(EstacionamentoController.class.getName());
    private List<Estacionamento> estacionamentos;
    private OcuparVagaDAO ocuparVagaDAO;
    private LiberarVagaDAO liberarVagaDAO;
    private EstacionamentoDAO estacionamentoDAO;
    private VeiculoDAO veiculoDAO;

    public EstacionamentoController() {
        estacionamentoDAO = new EstacionamentoDAO();
        ocuparVagaDAO = new OcuparVagaDAO();
        liberarVagaDAO = new LiberarVagaDAO();
        veiculoDAO = new VeiculoDAO();
        loadEstacionamentos();
    }

    private void loadEstacionamentos() {
        try {
            estacionamentos = estacionamentoDAO.getAllEstacionamentos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar estacionamentos do banco de dados.", e);
        }
    }

    public List<Estacionamento> getEstacionamentos() {
        return estacionamentos;
    }

    public Vaga ocuparVaga(int estacionamentoIndex, int vagaIndex, Cliente cliente, String placa) throws PlacaExistenteException {
        Vaga vaga = estacionamentos.get(estacionamentoIndex).getVagas().get(vagaIndex);
        if (!vaga.isOcupada()) {
            try {
                if (veiculoDAO.veiculoExiste(placa)) {
                    throw new PlacaExistenteException("A placa " + placa + " já está cadastrada.");
                }
                vaga.ocupar(cliente);
                int clienteId = cliente.getId();
                int numeroVaga = Integer.parseInt(vaga.getNumero().substring(1)); // Converte a parte numérica da vaga para int
                logger.log(Level.INFO, "Ocupando vaga: estacionamentoIndex={0}, vagaIndex={1}, clienteId={2}, placa={3}",
                        new Object[]{estacionamentoIndex, vagaIndex, clienteId, placa});
                ocuparVagaDAO.ocuparVaga(numeroVaga, clienteId, placa, estacionamentos.get(estacionamentoIndex).getNome());
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao ocupar vaga no banco de dados.", e);
                throw new RuntimeException("Erro ao ocupar vaga no banco de dados.", e);
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Identificador do cliente deve ser um número.", e);
                throw new IllegalArgumentException("Identificador do cliente deve ser um número.", e);
            }
        }
        return vaga;
    }

    public void liberarVaga(int estacionamentoIndex, int vagaIndex) {
        Vaga vaga = estacionamentos.get(estacionamentoIndex).getVagas().get(vagaIndex);
        if (vaga.isOcupada()) {
            vaga.liberar();
            try {
                int numeroVaga = Integer.parseInt(vaga.getNumero().substring(1)); // Converte a parte numérica da vaga para int
                liberarVagaDAO.liberarVaga(numeroVaga);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao liberar vaga no banco de dados.", e);
                throw new RuntimeException("Erro ao liberar vaga no banco de dados.", e);
            }
        }
    }
}