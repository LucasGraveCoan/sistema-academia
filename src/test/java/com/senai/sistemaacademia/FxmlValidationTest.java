package com.senai.sistemaacademia;

import org.junit.jupiter.api.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FxmlValidationTest {

    private final List<String> fxmlFiles = Arrays.asList(
            "main-view.fxml",
            "dashboard-view.fxml",
            "alunos-view.fxml",
            "professores-view.fxml",
            "planos-view.fxml",
            "exercicios-view.fxml",
            "treinos-view.fxml"
    );

    @Test
    void testAllFxmlFilesAreValidXml() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        for (String fxml : fxmlFiles) {
            try (InputStream in = getClass().getResourceAsStream(fxml)) {
                assertNotNull(in, "FXML não encontrado no classpath: " + fxml);
                builder.parse(in);
            }
        }
    }
}
