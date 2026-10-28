package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.Plano;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanosController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private ComboBox<String> modalidadeCombo;

    @FXML
    private TextField duracaoField;

    @FXML
    private TextField valorField;

    @FXML
    private TextField buscaField;

    @FXML
    private TableView<Plano> tabelaPlanos;

    @FXML
    private TableColumn<Plano, Integer> colId;

    @FXML
    private TableColumn<Plano, String> colNome;

    @FXML
    private TableColumn<Plano, String> colModalidade;

    @FXML
    private TableColumn<Plano, Integer> colDuracao;

    @FXML
    private TableColumn<Plano, Double> colValor;

    private ObservableList<Plano> listaPlanos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
        carregarCombosEDados();
        configurarSelecao();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colModalidade.setCellValueFactory(new PropertyValueFactory<>("modalidade"));
        colDuracao.setCellValueFactory(new PropertyValueFactory<>("duracaoMeses"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    private void carregarCombosEDados() {
        modalidadeCombo.setItems(FXCollections.observableArrayList(
                "Musculação Livre",
                "Musculação + Aulas Coletivas",
                "Crossfit / Treino Funcional",
                "Pilates + Musculação",
                "Acesso Total VIP (Todas as modalidades)"
        ));

        listaPlanos = FXCollections.observableArrayList(
                new Plano(1, "Plano Mensal Básico", "Musculação Livre", 1, 99.90),
                new Plano(2, "Plano Trimestral Fit", "Musculação + Aulas Coletivas", 3, 119.90),
                new Plano(3, "Plano Semestral Pro", "Crossfit / Treino Funcional", 6, 129.90),
                new Plano(4, "Plano Anual VIP", "Acesso Total VIP (Todas as modalidades)", 12, 139.90)
        );
        tabelaPlanos.setItems(listaPlanos);
    }

    private void configurarSelecao() {
        tabelaPlanos.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                idField.setText(String.valueOf(novo.getId()));
                nomeField.setText(novo.getNome());
                modalidadeCombo.setValue(novo.getModalidade());
                duracaoField.setText(String.valueOf(novo.getDuracaoMeses()));
                valorField.setText(String.format("%.2f", novo.getValor()));
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
        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Plano validado visualmente! Persistência será integrada no backend.");
    }

    @FXML
    public void editar() {
        Plano selecionado = tabelaPlanos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um plano na tabela para editar.");
            return;
        }
        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Editando plano: " + selecionado.getNome());
    }

    @FXML
    public void excluir() {
        Plano selecionado = tabelaPlanos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um plano na tabela para excluir.");
            return;
        }
        mostrarAlerta(Alert.AlertType.CONFIRMATION, "Demonstração Visual", "Confirma a exclusão ilustrativa do plano " + selecionado.getNome() + "?");
    }

    @FXML
    public void limpar() {
        idField.clear();
        nomeField.clear();
        modalidadeCombo.setValue(null);
        duracaoField.clear();
        valorField.clear();
        tabelaPlanos.getSelectionModel().clearSelection();
    }

    @FXML
    public void buscar() {
        String termo = buscaField.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            tabelaPlanos.setItems(listaPlanos);
        } else {
            ObservableList<Plano> filtrados = listaPlanos.filtered(p ->
                    p.getNome().toLowerCase().contains(termo) ||
                    (p.getModalidade() != null && p.getModalidade().toLowerCase().contains(termo))
            );
            tabelaPlanos.setItems(filtrados);
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
