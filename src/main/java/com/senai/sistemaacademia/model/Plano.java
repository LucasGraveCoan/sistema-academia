package com.senai.sistemaacademia.model;

public class Plano {
    private int id;
    private String nome;
    private String modalidade;
    private int duracaoMeses;
    private double valor;

    public Plano() {
    }

    public Plano(int id, String nome, String modalidade, int duracaoMeses, double valor) {
        this.id = id;
        this.nome = nome;
        this.modalidade = modalidade;
        this.duracaoMeses = duracaoMeses;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome != null ? nome : "Plano #" + id;
    }
}
