package com.senai.sistemaacademia.entidades;

import java.util.ArrayList;
import java.util.List;

public class Academia {
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Plano> planos;
    private List<Exercicio> exercicios;
    private List<Treino> treinos;

    private Academia() {
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.planos = new ArrayList<>();
        this.exercicios = new ArrayList<>();
        this.treinos = new ArrayList<>();
    }

    public List<Aluno> getAlunos() { return alunos; }

    public List<Professor> getProfessores() { return professores; }

    public List<Plano> getPlanos() { return planos; }

    public List<Exercicio> getExercicios() { return exercicios; }

    public List<Treino> getTreinos() { return treinos; }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }
}