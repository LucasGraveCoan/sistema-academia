package com.senai.sistemaacademia.controller;

import com.senai.sistemaacademia.model.Aluno;
import com.senai.sistemaacademia.model.Plano;
import com.senai.sistemaacademia.model.Professor;
import com.senai.sistemaacademia.model.Treino;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label totalAlunosLabel;

    @FXML
    private Label totalProfessoresLabel;

    @FXML
    private Label totalPlanosLabel;

    @FXML
    private Label totalTreinosLabel;

    @FXML
    private TableView<Aluno> tabelaAlunosRecentes;

    @FXML
    private TableColumn<Aluno, String> colAlunoNome;

    @FXML
    private TableColumn<Aluno, String> colAlunoPlano;

    @FXML
    private TableColumn<Aluno, String> colAlunoProfessor;

    @FXML
    private TableView<Treino> tabelaTreinosRecentes;

    @FXML
    private TableColumn<Treino, String> colTreinoNome;

    @FXML
    private TableColumn<Treino, String> colTreinoAluno;

    @FXML
    private TableColumn<Treino, String> colTreinoObjetivo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabelas();
        carregarDadosIlustrativos();
    }

    private void configurarTabelas() {
        colAlunoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAlunoPlano.setCellValueFactory(new PropertyValueFactory<>("planoNome"));
        colAlunoProfessor.setCellValueFactory(new PropertyValueFactory<>("professorNome"));

        colTreinoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTreinoAluno.setCellValueFactory(new PropertyValueFactory<>("alunoNome"));
        colTreinoObjetivo.setCellValueFactory(new PropertyValueFactory<>("objetivo"));
    }

    private void carregarDadosIlustrativos() {
        totalAlunosLabel.setText("128");
        totalProfessoresLabel.setText("8");
        totalPlanosLabel.setText("4");
        totalTreinosLabel.setText("64");

        Plano pAnual = new Plano(1, "Plano Anual VIP", "Musculação + Aulas", 12, 139.90);
        Plano pMensal = new Plano(2, "Plano Mensal", "Musculação", 1, 99.90);
        Plano pSemestral = new Plano(3, "Plano Semestral Fit", "Todas as Atividades", 6, 119.90);

        Professor profCarlos = new Professor(1, "Carlos Silva", "111.222.333-44", "(11) 98888-1111", "Musculação & Hipertrofia", 4500.0);
        Professor profJuliana = new Professor(2, "Juliana Mendes", "222.333.444-55", "(11) 97777-2222", "Treinamento Funcional", 4200.0);
        Professor profRodrigo = new Professor(3, "Rodrigo Souza", "333.444.555-66", "(11) 96666-3333", "Condicionamento Físico", 4000.0);

        ObservableList<Aluno> alunosMock = FXCollections.observableArrayList(
                new Aluno(1, "Lucas Grave Coan", "456.789.123-01", "(11) 99123-4567", "lucas@email.com", LocalDate.of(2002, 5, 14), pAnual, profCarlos),
                new Aluno(2, "Mariana Oliveira", "321.654.987-02", "(11) 98234-5678", "mariana@email.com", LocalDate.of(1999, 11, 20), pSemestral, profJuliana),
                new Aluno(3, "Pedro Henrique Santos", "789.123.456-03", "(11) 97345-6789", "pedro@email.com", LocalDate.of(2001, 3, 8), pMensal, profRodrigo),
                new Aluno(4, "Beatriz Souza Lima", "654.987.321-04", "(11) 96456-7890", "beatriz@email.com", LocalDate.of(2003, 8, 25), pAnual, profCarlos)
        );
        tabelaAlunosRecentes.setItems(alunosMock);

        ObservableList<Treino> treinosMock = FXCollections.observableArrayList(
                new Treino(1, "Treino A - Peito & Tríceps", "Hipertrofia", alunosMock.get(0), profCarlos),
                new Treino(2, "Treino B - Costas & Bíceps", "Hipertrofia", alunosMock.get(0), profCarlos),
                new Treino(3, "Treino Funcional Completo", "Condicionamento", alunosMock.get(1), profJuliana),
                new Treino(4, "Treino C - Pernas & Abdômen", "Resistência", alunosMock.get(2), profRodrigo)
        );
        tabelaTreinosRecentes.setItems(treinosMock);
    }

    @FXML
    public void irParaAlunos() {
        if (MainController.getInstance() != null) {
            MainController.getInstance().mostrarAlunos();
        }
    }

    @FXML
    public void irParaTreinos() {
        if (MainController.getInstance() != null) {
            MainController.getInstance().mostrarTreinos();
        }
    }

    @FXML
    public void irParaProfessores() {
        if (MainController.getInstance() != null) {
            MainController.getInstance().mostrarProfessores();
        }
    }
}
