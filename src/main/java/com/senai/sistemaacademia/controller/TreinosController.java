package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TreinosController implements Initializable {

    // Dados gerais do Treino
    @FXML
    private TextField idTreinoField;

    @FXML
    private TextField nomeTreinoField;

    @FXML
    private TextField objetivoField;

    @FXML
    private ComboBox<Aluno> alunoCombo;

    @FXML
    private ComboBox<Professor> professorCombo;

    // Área de Inclusão de Exercício
    @FXML
    private ComboBox<Exercicio> exercicioCombo;

    @FXML
    private TextField seriesField;

    @FXML
    private TextField repeticoesField;

    @FXML
    private TextField cargaField;

    @FXML
    private TextField observacaoField;

    // Tabela de Exercícios do Treino
    @FXML
    private TableView<Itemtreino> tabelaItensTreino;

    @FXML
    private TableColumn<Itemtreino, String> colExercicio;

    @FXML
    private TableColumn<Itemtreino, String> colGrupo;

    @FXML
    private TableColumn<Itemtreino, Integer> colSeries;

    @FXML
    private TableColumn<Itemtreino, Integer> colRepeticoes;

    @FXML
    private TableColumn<Itemtreino, Double> colCarga;

    @FXML
    private TableColumn<Itemtreino, String> colObs;

    private ObservableList<Itemtreino> itensDoTreino;
    private ObservableList<Aluno> listaAlunos;
    private ObservableList<Professor> listaProfessores;
    private ObservableList<Exercicio> listaExercicios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
        carregarCombosEDados();
    }

    private void configurarTabela() {
        colExercicio.setCellValueFactory(new PropertyValueFactory<>("exercicioNome"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("grupoMuscular"));
        colSeries.setCellValueFactory(new PropertyValueFactory<>("series"));
        colRepeticoes.setCellValueFactory(new PropertyValueFactory<>("repeticoes"));
        colCarga.setCellValueFactory(new PropertyValueFactory<>("carga"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("observacao"));
    }

    private void carregarCombosEDados() {
        Plano pAnual = new Plano(1, "Plano Anual VIP", "Musculação + Aulas", 12, 139.90);
        Professor profCarlos = new Professor(1, "Carlos Silva", "111.222.333-44", "(11) 98888-1111", "Musculação & Hipertrofia", 4500.0);
        Professor profJuliana = new Professor(2, "Juliana Mendes", "222.333.444-55", "(11) 97777-2222", "Treinamento Funcional", 4200.0);

        listaProfessores = FXCollections.observableArrayList(profCarlos, profJuliana);
        professorCombo.setItems(listaProfessores);

        listaAlunos = FXCollections.observableArrayList(
                new Aluno(1, "Lucas Grave Coan", "456.789.123-01", "(11) 99123-4567", "lucas@email.com", LocalDate.of(2002, 5, 14), pAnual, profCarlos),
                new Aluno(2, "Mariana Oliveira", "321.654.987-02", "(11) 98234-5678", "mariana@email.com", LocalDate.of(1999, 11, 20), pAnual, profJuliana)
        );
        alunoCombo.setItems(listaAlunos);

        listaExercicios = FXCollections.observableArrayList(
                new Exercicio(1, "Supino Reto com Barra", "Peito (Peitorais)", "4 séries até a falha"),
                new Exercicio(2, "Agachamento Livre", "Pernas", "Movimento profundo"),
                new Exercicio(3, "Puxada Alta na Frente", "Costas (Dorsais)", "Puxada pronada no pulley"),
                new Exercicio(4, "Desenvolvimento com Halteres", "Ombros (Deltoides)", "Sentado em banco 90°"),
                new Exercicio(5, "Rosca Direta com Barra W", "Bíceps", "Isolamento de bíceps"),
                new Exercicio(6, "Tríceps Corda no Pulley", "Tríceps", "Extensão completa")
        );
        exercicioCombo.setItems(listaExercicios);

        // Preenche com um treino ilustrativo inicial
        idTreinoField.setText("1");
        nomeTreinoField.setText("Treino A - Peito, Ombros e Tríceps");
        objetivoField.setText("Hipertrofia Muscular");
        alunoCombo.setValue(listaAlunos.get(0));
        professorCombo.setValue(listaProfessores.get(0));

        itensDoTreino = FXCollections.observableArrayList(
                new Itemtreino(listaExercicios.get(0), 4, 10, 30.0, "Descanso de 60s entre séries"),
                new Itemtreino(listaExercicios.get(3), 4, 12, 14.0, "Halteres de 14kg cada lado"),
                new Itemtreino(listaExercicios.get(5), 3, 15, 25.0, "Drop-set na última série")
        );
        tabelaItensTreino.setItems(itensDoTreino);
    }

    @FXML
    public void novoTreino() {
        idTreinoField.clear();
        nomeTreinoField.clear();
        objetivoField.clear();
        alunoCombo.setValue(null);
        professorCombo.setValue(null);
        limparCamposExercicio();
        itensDoTreino.clear();
        nomeTreinoField.requestFocus();
    }

    @FXML
    public void adicionarExercicio() {
        Exercicio ex = exercicioCombo.getValue();
        if (ex == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Exercício Obrigatório", "Selecione um exercício no menu para adicionar.");
            return;
        }

        int series = 3;
        int reps = 10;
        double carga = 0.0;

        try {
            if (!seriesField.getText().trim().isEmpty()) {
                series = Integer.parseInt(seriesField.getText().trim());
            }
            if (!repeticoesField.getText().trim().isEmpty()) {
                reps = Integer.parseInt(repeticoesField.getText().trim());
            }
            if (!cargaField.getText().trim().isEmpty()) {
                carga = Double.parseDouble(cargaField.getText().trim().replace(",", "."));
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Inválido", "Séries, repetições e carga devem conter números válidos.");
            return;
        }

        String obs = observacaoField.getText().trim();
        Itemtreino item = new Itemtreino(ex, series, reps, carga, obs);
        itensDoTreino.add(item);
        limparCamposExercicio();
    }

    @FXML
    public void removerExercicio() {
        Itemtreino selecionado = tabelaItensTreino.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um exercício na tabela para remover da rotina.");
            return;
        }
        itensDoTreino.remove(selecionado);
    }

    private void limparCamposExercicio() {
        exercicioCombo.setValue(null);
        seriesField.clear();
        repeticoesField.clear();
        cargaField.clear();
        observacaoField.clear();
    }

    @FXML
    public void salvarTreino() {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Ficha de treino pronta para ser salva com " + itensDoTreino.size() + " exercícios!");
    }

    @FXML
    public void editarTreino() {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Demonstração Visual", "Modo de edição da ficha de treino atual ativado.");
    }

    @FXML
    public void consultarTreino() {
        Aluno aluno = alunoCombo.getValue();
        String nomeAluno = aluno != null ? aluno.getNome() : "aluno não selecionado";
        mostrarAlerta(Alert.AlertType.INFORMATION, "Consulta de Treinos",
                "Consultando fichas ativas para: " + nomeAluno +
                "\nTreino Atual: " + nomeTreinoField.getText() +
                "\nTotal de Exercícios: " + itensDoTreino.size());
    }

    @FXML
    public void historicoTreinos() {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Histórico de Treinos",
                "Exibindo histórico de fichas de treino anteriores do aluno.");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
