package org.example.model;

public class VagaFactory {
    public static Vaga createVaga(int id, String numero, int estacionamentoId, String tipo, String nomeParque) {
        int row = Integer.parseInt(numero.substring(1)) - 1;
        int column = numero.charAt(0) - 'A';
        switch (tipo) {
            case "Regular":
                return new VagaRegular(id, row, column, estacionamentoId, nomeParque);
            case "Idoso":
                return new VagaIdoso(id, row, column, estacionamentoId, nomeParque);
            case "PCD":
                return new VagaPCD(id, row, column, estacionamentoId, nomeParque);
            case "VIP":
                return new VagaVIP(id, row, column, estacionamentoId, nomeParque);
            default:
                throw new IllegalArgumentException("Tipo de vaga desconhecido: " + tipo);
        }
    }
}