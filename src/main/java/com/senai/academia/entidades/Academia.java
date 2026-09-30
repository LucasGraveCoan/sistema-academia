package com.senai.academia.entidades;

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

    private List<Aluno> getAlunos() { return alunos; }
    private List<Professor> getProfessores() { return professores; }
    private List<Plano> getPlanos() { return planos; }
    private List<Exercicio> getExercicios() { return exercicios; }
    private List<Treino> getTreinos() { return treinos; }
}