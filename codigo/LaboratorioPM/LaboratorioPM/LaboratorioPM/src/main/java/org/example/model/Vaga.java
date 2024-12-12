package org.example.model;

import org.example.DAO.VagaDAO;

import java.awt.*;
import java.sql.SQLException;

public abstract class Vaga {
    private int id;
    private String numero;
    private boolean ocupada;
    private Cliente cliente;
    private int estacionamentoId;
    private String tipo;
    private String nomeParque; // Add this field
    private VagaDAO vagaDAO;

    public Vaga(int id, String numero, int estacionamentoId, String tipo, String nomeParque) { // Update constructor
        this.id = id;
        this.numero = numero;
        this.ocupada = false;
        this.cliente = null;
        this.estacionamentoId = estacionamentoId;
        this.tipo = tipo;
        this.nomeParque = nomeParque; // Set this field
        this.vagaDAO = new VagaDAO();
    }

    public int getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar(Cliente cliente) {
        this.ocupada = true;
        this.cliente = cliente;
    }

    public void liberar() {
        this.ocupada = false;
        this.cliente = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getEstacionamentoId() {
        return estacionamentoId;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeParque() { // Add getter
        return nomeParque;
    }

    public void salvar() throws SQLException {
        vagaDAO.salvarVaga(this);
    }

    public abstract Color getCor();

    public static String generateSpotNumber(int row, int column) {
        return (char) ('A' + column) + String.valueOf(row + 1);
    }

    public String getPlacaVeiculo() {
        if (cliente != null && !cliente.getVeiculos().isEmpty()) {
            return cliente.getVeiculos().get(0).getPlaca(); // Assuming the first vehicle's plate is needed
        }
        return null;
    }
}