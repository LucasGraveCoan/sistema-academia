package com.senai.sistemaacademia.model;

public class Itemtreino {
    private Exercicio exercicio;
    private int series;
    private int repeticoes;
    private double carga;
    private String observacao;

    public Itemtreino() {
    }

    public Itemtreino(Exercicio exercicio, int series, int repeticoes, double carga, String observacao) {
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.observacao = observacao;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public String getExercicioNome() {
        return exercicio != null ? exercicio.getNome() : "";
    }

    public String getGrupoMuscular() {
        return exercicio != null ? exercicio.getGrupoMuscular() : "";
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
