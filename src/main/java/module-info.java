module com.senai.sistemaacademia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.senai.academia to javafx.fxml;
    exports com.senai.academia;
}