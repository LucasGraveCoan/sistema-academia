package com.senai.sistemaacademia.service;

import com.senai.sistemaacademia.model.Professor;

import java.util.List;

public class ProfessorService {

    public void salvar(Professor professor) {
        BancoDeDados.getInstance().cadastrarProfessor(professor);
    }

    public List<Professor> listar() {
        return BancoDeDados.getInstance().getProfessores();
    }


}
