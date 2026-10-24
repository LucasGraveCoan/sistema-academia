
package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.Professor;
import com.senai.sistemaacademia.service.BancoDeDados;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {

    @FXML
    private TextField nomeProfessor;

    @FXML
    private TextField cpfProfessor;

    @FXML
    private TextField telefoneProfessor;

    @FXML
    protected void cadastrarProfessor() {

        String nome = nomeProfessor.getText().trim();
        String cpf = cpfProfessor.getText().trim();
        String telefone = telefoneProfessor.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos obrigatórios");
            alerta.setHeaderText(null);
            alerta.setContentText("Preencha todos os campos.");
            alerta.showAndWait();
            return;
        }

        Professor professor = new Professor(nome, cpf, telefone);

        professor.setId(
                BancoDeDados.getInstance().getProfessores().size() + 1
        );
        professor.setNome(nome);
        professor.setCpf(cpf);
        professor.setTelefone(telefone);
        professor.setAlunos(new ArrayList<>());

        BancoDeDados.getInstance().cadastrarProfessor(professor);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Cadastro realizado");
        alerta.setHeaderText(null);
        alerta.setContentText("Professor cadastrado com sucesso!");
        alerta.showAndWait();

        nomeProfessor.clear();
        cpfProfessor.clear();
        telefoneProfessor.clear();
    }
}
