package com.senai.sistemaacademia;

import com.senai.sistemaacademia.model.Professor;
import com.senai.sistemaacademia.service.BancoDeDados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CadastroProfessorTest {

    private BancoDeDados banco;

    @BeforeEach
    void setUp() {
        banco = BancoDeDados.getInstance();
        banco.getProfessores().clear();
    }

    @Test
    void testCadastrarProfessorComSucesso() {
        Professor professor = new Professor("Carlos Silva", "123.456.789-00", "(11) 98765-4321");
        professor.setId(banco.getProfessores().size() + 1);
        professor.setAlunos(new ArrayList<>());

        banco.cadastrarProfessor(professor);

        assertEquals(1, banco.getProfessores().size());
        Professor cadastrado = banco.getProfessores().get(0);
        assertEquals(1, cadastrado.getId());
        assertEquals("Carlos Silva", cadastrado.getNome());
        assertEquals("123.456.789-00", cadastrado.getCpf());
        assertEquals("(11) 98765-4321", cadastrado.getTelefone());
        assertNotNull(cadastrado.getAlunos());
    }

    @Test
    void testCadastrarMultiplosProfessores() {
        Professor p1 = new Professor("Ana Souza", "111.111.111-11", "(11) 91111-1111");
        p1.setId(banco.getProfessores().size() + 1);
        banco.cadastrarProfessor(p1);

        Professor p2 = new Professor("Bruno Lima", "222.222.222-22", "(11) 92222-2222");
        p2.setId(banco.getProfessores().size() + 1);
        banco.cadastrarProfessor(p2);

        assertEquals(2, banco.getProfessores().size());
        assertEquals(1, banco.getProfessores().get(0).getId());
        assertEquals(2, banco.getProfessores().get(1).getId());
    }
}
