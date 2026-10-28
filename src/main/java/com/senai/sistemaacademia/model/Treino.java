package com.senai.sistemaacademia.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Treino {
    private int id;
    private String nome;
    private String objetivo;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private Aluno aluno;
    private Professor professor;
    public List<Itemtreino> itens = new ArrayList<>();

    public Treino() {
    }

    public Treino(int id, String nome, String objetivo, Aluno aluno, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.objetivo = objetivo;
        this.aluno = aluno;
        this.professor = professor;
        this.dataCriacao = LocalDateTime.now();
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getAlunoNome() {
        return aluno != null ? aluno.getNome() : "Não informado";
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getProfessorNome() {
        return professor != null ? professor.getNome() : "Não informado";
    }

    public List<Itemtreino> getItens() {
        return itens;
    }

    public void setItens(List<Itemtreino> itens) {
        this.itens = itens;
    }

    public void adicionarItem(Itemtreino item) {
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }
        this.itens.add(item);
    }

    public int getQuantidadeExercicios() {
        return itens != null ? itens.size() : 0;
    }

    @Override
    public String toString() {
        return nome != null ? nome : "Treino #" + id;
    }
}
