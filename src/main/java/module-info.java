module com.senai.sistemaacademia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens com.senai.sistemaacademia to javafx.fxml;
    exports com.senai.sistemaacademia;
    exports com.senai.sistemaacademia.controller;
    opens com.senai.sistemaacademia.controller to javafx.fxml;
    exports com.senai.sistemaacademia.model;
    opens com.senai.sistemaacademia.model to javafx.base, javafx.fxml;
}
