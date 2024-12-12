package org.example.model;

import java.awt.Color;

public class VagaVIP extends Vaga {
    public VagaVIP(int id, int row, int column, int estacionamentoId, String nomeParque) {
        super(id, Vaga.generateSpotNumber(row, column), estacionamentoId, "VIP", nomeParque);
    }

    @Override
    public Color getCor() {
        return Color.BLUE;
    }
}