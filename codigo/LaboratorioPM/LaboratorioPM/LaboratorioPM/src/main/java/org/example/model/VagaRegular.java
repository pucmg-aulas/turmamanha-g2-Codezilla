package org.example.model;

import java.awt.Color;

public class VagaRegular extends Vaga {
    public VagaRegular(int id, int row, int column, int estacionamentoId, String nomeParque) {
        super(id, Vaga.generateSpotNumber(row, column), estacionamentoId, "Regular", nomeParque);
    }

    @Override
    public Color getCor() {
        return Color.GREEN;
    }
}