module com.senai.sistemaacademia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.senai.sistemaacademia to javafx.fxml;
    exports com.senai.sistemaacademia;
    exports com.senai.sistemaacademia.controller;
    opens com.senai.sistemaacademia.controller to javafx.fxml;
}