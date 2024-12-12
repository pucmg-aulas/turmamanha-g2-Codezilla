package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String identificador;
    private List<Veiculo> veiculos;

    public Cliente(int id, String nome, String identificador) {
        this.id = id;
        this.nome = nome;
        this.identificador = identificador;
        this.veiculos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public String getPlacaVeiculo() {
        if (!veiculos.isEmpty()) {
            return veiculos.get(0).getPlaca(); // Assuming the first vehicle's plate is needed
        }
        return null;
    }
}