package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.Exercicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ExerciciosController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private ComboBox<String> grupoMuscularCombo;

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField buscaField;

    @FXML
    private TableView<Exercicio> tabelaExercicios;

    @FXML
    private TableColumn<Exercicio, Integer> colId;

    @FXML
    private TableColumn<Exercicio, String> colNome;

    @FXML
    private TableColumn<Exercicio, String> colGrupoMuscular;

    @FXML
    private TableColumn<Exercicio, String> colDescricao;

    private ObservableList<Exercicio> listaExercicios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
        carregarCombosEDados();
        configurarSelecao();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGrupoMuscular.setCellValueFactory(new PropertyValueFactory<>("grupoMuscular"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }

    private void carregarCombosEDados() {
        grupoMuscularCombo.setItems(FXCollections.observableArrayList(
                "Peito (Peitorais)",
                "Costas (Dorsais)",
                "Pernas (Quadríceps e Posteriores)",
                "Ombros (Deltoides)",
                "Bíceps",
                "Tríceps",
                "Abdômen & Core",
                "Glúteos & Panturrilhas"
        ));

        listaExercicios = FXCollections.observableArrayList(
                new Exercicio(1, "Supino Reto com Barra", "Peito (Peitorais)", "Movimento de empurrar na barra guiada ou livre para peitoral maior"),
                new Exercicio(2, "Agachamento Livre", "Pernas (Quadríceps e Posteriores)", "Exercício multiarticular de membros inferiores com foco em quadríceps e glúteos"),
                new Exercicio(3, "Puxada Alta na Frente", "Costas (Dorsais)", "Puxada pronada no pulley focada em latíssimo do dorso"),
                new Exercicio(4, "Desenvolvimento com Halteres", "Ombros (Deltoides)", "Elevação vertical com halteres sentado para deltoides"),
                new Exercicio(5, "Rosca Direta com Barra W", "Bíceps", "Flexão de cotovelos em pé com barra W para bíceps braquial"),
                new Exercicio(6, "Tríceps Corda no Pulley", "Tríceps", "Extensão de cotovelos na polia alta com corda e abertura no final"),
                new Exercicio(7, "Leg Press 45°", "Pernas (Quadríceps e Posteriores)", "Extensão de pernas no aparelho inclinado a 45 graus"),
                new Exercicio(8, "Prancha Abdominal Isométrica", "Abdômen & Core", "Sustentação estática do corpo com antebraços apoiados no chão")
        );
        tabelaExercicios.setItems(listaExercicios);
    }

    private void configurarSelecao() {
        tabelaExercicios.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                idField.setText(String.valueOf(novo.getId()));
                nomeField.setText(novo.getNome());
                grupoMuscularCombo.setValue(novo.getGrupoMuscular());
                descricaoField.setText(novo.getDescricao());
            }
        });
    }

    @FXML
    public void novo() {
        limpar();
        nomeField.requestFocus();
    }

    @FXML
    public void salvar() {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Exercício validado visualmente! Persistência será integrada no backend.");
    }

    @FXML
    public void editar() {
        Exercicio selecionado = tabelaExercicios.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um exercício na tabela para editar.");
            return;
        }
        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Editando exercício: " + selecionado.getNome());
    }

    @FXML
    public void excluir() {
        Exercicio selecionado = tabelaExercicios.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um exercício na tabela para excluir.");
            return;
        }
        mostrarAlerta(Alert.AlertType.CONFIRMATION, "Demonstração Visual", "Confirma a exclusão ilustrativa do exercício " + selecionado.getNome() + "?");
    }

    @FXML
    public void limpar() {
        idField.clear();
        nomeField.clear();
        grupoMuscularCombo.setValue(null);
        descricaoField.clear();
        tabelaExercicios.getSelectionModel().clearSelection();
    }

    @FXML
    public void buscar() {
        String termo = buscaField.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            tabelaExercicios.setItems(listaExercicios);
        } else {
            ObservableList<Exercicio> filtrados = listaExercicios.filtered(e ->
                    e.getNome().toLowerCase().contains(termo) ||
                    (e.getGrupoMuscular() != null && e.getGrupoMuscular().toLowerCase().contains(termo)) ||
                    (e.getDescricao() != null && e.getDescricao().toLowerCase().contains(termo))
            );
            tabelaExercicios.setItems(filtrados);
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
