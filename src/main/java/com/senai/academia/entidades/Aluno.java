package com.senai.academia.entidades;

import java.time.LocalDate;
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
    private List<Treino> treinos;
}

