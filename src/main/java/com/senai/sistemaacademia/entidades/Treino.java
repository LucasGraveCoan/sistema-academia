package com.senai.sistemaacademia.entidades;

import java.time.LocalDate;
import java.util.List;

public class Treino {
    private int id;
    private String nome;
    private String objetivo;
    private LocalDate dataCriacao;
    private Professor professor;
    private List<Itemtreino> itens;
}
