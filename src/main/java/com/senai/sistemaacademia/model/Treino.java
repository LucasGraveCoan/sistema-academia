package com.senai.sistemaacademia.model;
import java.util.*;
import java.time.LocalDateTime;



public class Treino {
    private int id;
    private String objetivo;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private Professor professor;
    private String nome;
    private List<Itemtreino> itens = new ArrayList<>();


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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo)  {
        this.objetivo = objetivo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Itemtreino> getItens() {
        return itens;
    }

    public void setItens(List<Itemtreino> itens) {
        this.itens = itens;


    }

    public void adicionarItem(Itemtreino item) {
        itens.add(item);
    }
}

