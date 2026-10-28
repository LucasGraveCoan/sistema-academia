package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.Aluno;
import com.senai.sistemaacademia.model.Plano;
import com.senai.sistemaacademia.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlunosController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker dataNascimentoPicker;

    @FXML
    private ComboBox<Plano> planoCombo;

    @FXML
    private ComboBox<Professor> professorCombo;

    @FXML
    private TextField buscaField;

    @FXML
    private TableView<Aluno> tabelaAlunos;

    @FXML
    private TableColumn<Aluno, Integer> colId;

    @FXML
    private TableColumn<Aluno, String> colNome;

    @FXML
    private TableColumn<Aluno, String> colCpf;

    @FXML
    private TableColumn<Aluno, String> colTelefone;

    @FXML
    private TableColumn<Aluno, String> colEmail;

    @FXML
    private TableColumn<Aluno, String> colPlano;

    @FXML
    private TableColumn<Aluno, String> colProfessor;

    private ObservableList<Aluno> listaAlunos;
    private ObservableList<Plano> listaPlanos;
    private ObservableList<Professor> listaProfessores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
        carregarCombosEDados();
        configurarSelecaoTabela();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPlano.setCellValueFactory(new PropertyValueFactory<>("planoNome"));
        colProfessor.setCellValueFactory(new PropertyValueFactory<>("professorNome"));
    }

    private void carregarCombosEDados() {
        // Mock de Planos
        listaPlanos = FXCollections.observableArrayList(
                new Plano(1, "Plano Mensal", "Musculação", 1, 99.90),
                new Plano(2, "Plano Trimestral Fit", "Musculação + Aulas", 3, 119.90),
                new Plano(3, "Plano Semestral Pro", "Todas as Atividades", 6, 129.90),
                new Plano(4, "Plano Anual VIP", "Livre Total + Avaliação", 12, 139.90)
        );
        planoCombo.setItems(listaPlanos);

        // Mock de Professores
        listaProfessores = FXCollections.observableArrayList(
                new Professor(1, "Carlos Silva", "111.222.333-44", "(11) 98888-1111", "Musculação & Hipertrofia", 4500.0),
                new Professor(2, "Juliana Mendes", "222.333.444-55", "(11) 97777-2222", "Treinamento Funcional", 4200.0),
                new Professor(3, "Rodrigo Souza", "333.444.555-66", "(11) 96666-3333", "Condicionamento Físico", 4000.0),
                new Professor(4, "Fernanda Lima", "444.555.666-77", "(11) 95555-4444", "Pilates & Postura", 4300.0)
        );
        professorCombo.setItems(listaProfessores);

        // Mock de Alunos
        listaAlunos = FXCollections.observableArrayList(
                new Aluno(1, "Lucas Grave Coan", "456.789.123-01", "(11) 99123-4567", "lucas@email.com", LocalDate.of(2002, 5, 14), listaPlanos.get(3), listaProfessores.get(0)),
                new Aluno(2, "Mariana Oliveira", "321.654.987-02", "(11) 98234-5678", "mariana@email.com", LocalDate.of(1999, 11, 20), listaPlanos.get(2), listaProfessores.get(1)),
                new Aluno(3, "Pedro Henrique Santos", "789.123.456-03", "(11) 97345-6789", "pedro@email.com", LocalDate.of(2001, 3, 8), listaPlanos.get(0), listaProfessores.get(2)),
                new Aluno(4, "Beatriz Souza Lima", "654.987.321-04", "(11) 96456-7890", "beatriz@email.com", LocalDate.of(2003, 8, 25), listaPlanos.get(3), listaProfessores.get(0)),
                new Aluno(5, "Gabriel Alencar", "852.963.741-05", "(11) 95567-8901", "gabriel@email.com", LocalDate.of(1998, 1, 30), listaPlanos.get(1), listaProfessores.get(3))
        );
        tabelaAlunos.setItems(listaAlunos);
    }

    private void configurarSelecaoTabela() {
        tabelaAlunos.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                preencherCampos(novo);
            }
        });
    }

    private void preencherCampos(Aluno aluno) {
        idField.setText(String.valueOf(aluno.getId()));
        nomeField.setText(aluno.getNome());
        cpfField.setText(aluno.getCpf());
        telefoneField.setText(aluno.getTelefone());
        emailField.setText(aluno.getEmail());
        dataNascimentoPicker.setValue(aluno.getDataNascimento());

        for (Plano p : planoCombo.getItems()) {
            if (aluno.getPlano() != null && p.getId() == aluno.getPlano().getId()) {
                planoCombo.setValue(p);
                break;
            }
        }

        for (Professor prof : professorCombo.getItems()) {
            if (aluno.getProfessor() != null && prof.getId() == aluno.getProfessor().getId()) {
                professorCombo.setValue(prof);
                break;
            }
        }
    }

    @FXML
    public void novo() {
        limpar();
        nomeField.requestFocus();
    }

    @FXML
    public void salvar() {
        mostrarMensagem(Alert.AlertType.INFORMATION, "Demonstração Visual", "Formulário validado com sucesso! A persistência no backend será implementada na próxima etapa.");
    }

    @FXML
    public void editar() {
        Aluno selecionado = tabelaAlunos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarMensagem(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um aluno na tabela para editar.");
            return;
        }
        mostrarMensagem(Alert.AlertType.INFORMATION, "Demonstração Visual", "Modo de edição do aluno: " + selecionado.getNome());
    }

    @FXML
    public void excluir() {
        Aluno selecionado = tabelaAlunos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarMensagem(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um aluno na tabela para excluir.");
            return;
        }
        mostrarMensagem(Alert.AlertType.CONFIRMATION, "Demonstração Visual", "Confirma a exclusão ilustrativa do aluno " + selecionado.getNome() + "?");
    }

    @FXML
    public void limpar() {
        idField.clear();
        nomeField.clear();
        cpfField.clear();
        telefoneField.clear();
        emailField.clear();
        dataNascimentoPicker.setValue(null);
        planoCombo.setValue(null);
        professorCombo.setValue(null);
        tabelaAlunos.getSelectionModel().clearSelection();
    }

    @FXML
    public void buscar() {
        String termo = buscaField.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            tabelaAlunos.setItems(listaAlunos);
        } else {
            ObservableList<Aluno> filtrados = listaAlunos.filtered(a ->
                    a.getNome().toLowerCase().contains(termo) ||
                    a.getCpf().contains(termo) ||
                    a.getTelefone().contains(termo)
            );
            tabelaAlunos.setItems(filtrados);
        }
    }

    @FXML
    public void verTreinos() {
        Aluno selecionado = tabelaAlunos.getSelectionModel().getSelectedItem();
        String nome = selecionado != null ? selecionado.getNome() : "aluno selecionado";
        mostrarMensagem(Alert.AlertType.INFORMATION, "Treinos do Aluno", "Visualização dos treinos associados a: " + nome + "\n(Direcionando para o módulo de Treinos)");
        if (MainController.getInstance() != null) {
            MainController.getInstance().mostrarTreinos();
        }
    }

    private void mostrarMensagem(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
