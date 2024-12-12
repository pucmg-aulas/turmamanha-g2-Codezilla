package org.example.model;

import java.awt.Color;

public class VagaIdoso extends Vaga {
    public VagaIdoso(int id, int row, int column, int estacionamentoId, String nomeParque) {
        super(id, Vaga.generateSpotNumber(row, column), estacionamentoId, "Idoso", nomeParque);
    }

    @Override
    public Color getCor() {
        return Color.ORANGE;
    }
}