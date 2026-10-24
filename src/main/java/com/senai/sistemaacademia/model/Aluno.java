package com.senai.sistemaacademia.model;

import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;

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


    public int getId() {
        return id;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public void consultarTreinos() {
        if (treinos.isEmpty()) {
            System.out.println("aluno sem treinos");
        } else {
            for (Treino treino : treinos) {
                System.out.println("Treino:" + treino.getNome());

                for (Itemtreino item: treino.getItens()){
                    System.out.println(item.getExercicio().getNome()+ "-"+ item.getSeries()+ "series- "+item.getRepeticoes()+ "repeticoes"+
                    item.getCarga()+ "kg");
                }
            }
        }
    }
}


