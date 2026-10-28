package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static MainController instance;

    @FXML
    private StackPane contentArea;

    @FXML
    private Label screenTitle;

    @FXML
    private Label screenSubtitle;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnAlunos;

    @FXML
    private Button btnProfessores;

    @FXML
    private Button btnPlanos;

    @FXML
    private Button btnExercicios;

    @FXML
    private Button btnTreinos;

    public static MainController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        mostrarInicio();
    }

    @FXML
    public void navegar(ActionEvent event) {
        Button source = (Button) event.getSource();
        if (source == btnInicio) {
            mostrarInicio();
        } else if (source == btnAlunos) {
            mostrarAlunos();
        } else if (source == btnProfessores) {
            mostrarProfessores();
        } else if (source == btnPlanos) {
            mostrarPlanos();
        } else if (source == btnExercicios) {
            mostrarExercicios();
        } else if (source == btnTreinos) {
            mostrarTreinos();
        }
    }

    public void mostrarInicio() {
        carregarTela("dashboard-view.fxml", "Painel Principal", "Visão geral e resumo operacional da academia", btnInicio);
    }

    public void mostrarAlunos() {
        carregarTela("alunos-view.fxml", "Gestão de Alunos", "Cadastro, consulta de planos e acompanhamento de alunos", btnAlunos);
    }

    public void mostrarProfessores() {
        carregarTela("professores-view.fxml", "Gestão de Professores", "Controle do corpo docente, especialidades e turmas", btnProfessores);
    }

    public void mostrarPlanos() {
        carregarTela("planos-view.fxml", "Planos & Assinaturas", "Gerenciamento de mensalidades, modalidades e valores", btnPlanos);
    }

    public void mostrarExercicios() {
        carregarTela("exercicios-view.fxml", "Catálogo de Exercícios", "Biblioteca de movimentos por grupo muscular", btnExercicios);
    }

    public void mostrarTreinos() {
        carregarTela("treinos-view.fxml", "Montagem & Fichas de Treino", "Criação de rotinas personalizadas para os alunos", btnTreinos);
    }

    private void carregarTela(String fxmlFile, String title, String subtitle, Button activeButton) {
        try {
            URL resource = HelloApplication.class.getResource(fxmlFile);
            if (resource == null) {
                System.err.println("Arquivo FXML não encontrado: " + fxmlFile);
                return;
            }

            Node node = FXMLLoader.load(resource);
            contentArea.getChildren().setAll(node);

            screenTitle.setText(title);
            screenSubtitle.setText(subtitle);

            atualizarEstiloMenu(activeButton);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void atualizarEstiloMenu(Button activeButton) {
        Button[] buttons = {btnInicio, btnAlunos, btnProfessores, btnPlanos, btnExercicios, btnTreinos};
        for (Button btn : buttons) {
            if (btn != null) {
                btn.getStyleClass().remove("nav-button-active");
            }
        }
        if (activeButton != null && !activeButton.getStyleClass().contains("nav-button-active")) {
            activeButton.getStyleClass().add("nav-button-active");
        }
    }
}
