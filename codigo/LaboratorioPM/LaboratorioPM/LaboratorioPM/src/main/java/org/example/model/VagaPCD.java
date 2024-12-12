package org.example.model;

import java.awt.Color;

public class VagaPCD extends Vaga {
    public VagaPCD(int id, int row, int column, int estacionamentoId, String nomeParque) {
        super(id, Vaga.generateSpotNumber(row, column), estacionamentoId, "PCD", nomeParque);
    }

    @Override
    public Color getCor() {
        return Color.YELLOW;
    }
}