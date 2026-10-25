package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.Professor;
import com.senai.sistemaacademia.service.BancoDeDados;
import com.senai.sistemaacademia.service.Navegacao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField nomeProfessor;

    @FXML
    private TextField cpfProfessor;

    @FXML
    private TextField telefoneProfessor;

    @FXML
    protected void abrirCadastroProfessor() {
        try {
            Navegacao.abrirTela("cadastrar-professor.fxml", "Cadastro de Professor");
        } catch (IOException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Navegação");
            alerta.setHeaderText(null);
            alerta.setContentText("Não foi possível abrir a tela de cadastro: " + e.getMessage());
            alerta.showAndWait();
        }
    }

    @FXML
    protected void cadastrarProfessor() {
        if (nomeProfessor == null || cpfProfessor == null || telefoneProfessor == null) {
            return;
        }

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
        professor.setId(BancoDeDados.getInstance().getProfessores().size() + 1);
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
