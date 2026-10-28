package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.Professor;
import com.senai.sistemaacademia.service.ProfessorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfessoresController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField cargoField;

    @FXML
    private TextField salarioField;

    @FXML
    private TextField buscaField;

    @FXML
    private TableView<Professor> tabelaProfessores;

    @FXML
    private TableColumn<Professor, Integer> colId;

    @FXML
    private TableColumn<Professor, String> colNome;

    @FXML
    private TableColumn<Professor, String> colCpf;

    @FXML
    private TableColumn<Professor, String> colTelefone;

    @FXML
    private TableColumn<Professor, String> colCargo;

    @FXML
    private TableColumn<Professor, Double> colSalario;

    private ObservableList<Professor> listaProfessores;

    private ProfessorService professorService = new ProfessorService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
        carregarDados();
        configurarSelecao();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
    }

    private void carregarDados() {
        listaProfessores = FXCollections.observableArrayList(
                new Professor(1, "Carlos Silva", "111.222.333-44", "(11) 98888-1111", "Musculação & Hipertrofia", 4500.0),
                new Professor(2, "Juliana Mendes", "222.333.444-55", "(11) 97777-2222", "Treinamento Funcional", 4200.0),
                new Professor(3, "Rodrigo Souza", "333.444.555-66", "(11) 96666-3333", "Condicionamento Físico", 4000.0),
                new Professor(4, "Fernanda Lima", "444.555.666-77", "(11) 95555-4444", "Pilates & Postura", 4300.0),
                new Professor(5, "Marcelo Santos", "555.666.777-88", "(11) 94444-5555", "Cross Training & Força", 4600.0)
        );
        tabelaProfessores.setItems(listaProfessores);
    }

    private void configurarSelecao() {
        tabelaProfessores.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                idField.setText(String.valueOf(novo.getId()));
                nomeField.setText(novo.getNome());
                cpfField.setText(novo.getCpf());
                telefoneField.setText(novo.getTelefone());
                cargoField.setText(novo.getCargo());
                salarioField.setText(String.format("%.2f", novo.getSalario()));
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
        String nomeProfessor = nomeField.getText();
        String cpfProfessor = cpfField.getText();

        String stringSalario = salarioField.getText();
        Double salario = Double.parseDouble(stringSalario);

        Professor professor = new Professor(
                1,
                nomeProfessor,
                cpfProfessor,
                telefoneField.getText(),
                cargoField.getText(),
                salario);

        professorService.salvar(professor);

        // for each (stream)
        professorService.listar().forEach(p -> {
            System.out.println(p);
        });

        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Professor pronto para ser cadastrado no backend.");
    }

    @FXML
    public void editar() {
        Professor selecionado = tabelaProfessores.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um professor na tabela para editar.");
            return;
        }
        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Editando professor: " + selecionado.getNome());
    }

    @FXML
    public void excluir() {
        Professor selecionado = tabelaProfessores.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um professor na tabela para excluir.");
            return;
        }
        mostrarAlerta(Alert.AlertType.CONFIRMATION, "Demonstração Visual", "Confirma a exclusão ilustrativa do professor " + selecionado.getNome() + "?");
    }

    @FXML
    public void limpar() {
        idField.clear();
        nomeField.clear();
        cpfField.clear();
        telefoneField.clear();
        cargoField.clear();
        salarioField.clear();
        tabelaProfessores.getSelectionModel().clearSelection();
    }

    @FXML
    public void buscar() {
        String termo = buscaField.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            tabelaProfessores.setItems(listaProfessores);
        } else {
            ObservableList<Professor> filtrados = listaProfessores.filtered(p ->
                    p.getNome().toLowerCase().contains(termo) ||
                    p.getCpf().contains(termo) ||
                    (p.getCargo() != null && p.getCargo().toLowerCase().contains(termo))
            );
            tabelaProfessores.setItems(filtrados);
        }
    }

    @FXML
    public void verAlunos() {
        Professor selecionado = tabelaProfessores.getSelectionModel().getSelectedItem();
        String nome = selecionado != null ? selecionado.getNome() : "professor selecionado";
        mostrarAlerta(Alert.AlertType.INFORMATION, "Alunos do Professor", "Exibindo alunos acompanhados por: " + nome + "\n(Direcionando para o módulo de Alunos)");
        if (MainController.getInstance() != null) {
            MainController.getInstance().mostrarAlunos();
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
