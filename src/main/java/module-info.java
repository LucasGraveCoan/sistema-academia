module com.senai.sistemaacademia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.senai.sistemaacademia to javafx.fxml;
    exports com.senai.sistemaacademia;
}