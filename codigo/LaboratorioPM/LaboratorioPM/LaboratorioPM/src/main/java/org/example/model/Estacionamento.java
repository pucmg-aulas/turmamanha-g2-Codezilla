package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private int id;
    private String nome;
    private List<Vaga> vagas;

    public Estacionamento(String nome) {
        this.nome = nome;
        this.vagas = new ArrayList<>();
    }

    public Estacionamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.vagas = new ArrayList<>();
    }

    private void addVaga(Vaga vaga) {
        vagas.add(vaga);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }
}