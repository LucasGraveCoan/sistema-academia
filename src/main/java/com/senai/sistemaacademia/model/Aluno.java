package com.senai.sistemaacademia.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private Plano plano;
    private Professor professor;
    private List<Treino> treinos = new ArrayList<>();

    public Aluno() {
    }

    public Aluno(int id, String nome, String cpf, String telefone, String email, LocalDate dataNascimento, Plano plano, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.plano = plano;
        this.professor = professor;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public String getPlanoNome() {
        return plano != null ? plano.getNome() : "Sem plano";
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getProfessorNome() {
        return professor != null ? professor.getNome() : "Sem professor";
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    @Override
    public String toString() {
        return nome != null ? nome : "Aluno #" + id;
    }

    public void consultarTreinos() {
        if (treinos == null || treinos.isEmpty()) {
            System.out.println("Aluno sem treinos");
        } else {
            for (Treino treino : treinos) {
                System.out.println("Treino: " + treino.getNome());
                if (treino.getItens() != null) {
                    for (Itemtreino item : treino.getItens()) {
                        System.out.println(item.getExercicio().getNome() + " - " + item.getSeries() + " séries - " + item.getRepeticoes() + " repetições - " + item.getCarga() + "kg");
                    }
                }
            }
        }
    }
}
