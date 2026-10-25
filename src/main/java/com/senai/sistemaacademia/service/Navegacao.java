package com.senai.sistemaacademia.service;

import com.senai.sistemaacademia.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Navegacao {

    public static void abrirTela(String nomeArquivo, String titulo) throws IOException {
        String fxmlNome = nomeArquivo.endsWith(".fxml") ? nomeArquivo : nomeArquivo + ".fxml";

        // Tenta carregar via getResource (padrão JavaFX)
        URL resource = HelloApplication.class.getResource(fxmlNome);

        // Se não encontrar direto, busca recursivamente na pasta de resources
        if (resource == null) {
            Path pastaResources = Paths.get("src/main/resources");
            if (Files.exists(pastaResources)) {
                try (Stream<Path> arquivos = Files.walk(pastaResources)) {
                    Path arquivoEncontrado = arquivos
                            .filter(Files::isRegularFile)
                            .filter(p -> p.getFileName().toString().equalsIgnoreCase(fxmlNome))
                            .findFirst()
                            .orElse(null);

                    if (arquivoEncontrado != null) {
                        resource = arquivoEncontrado.toUri().toURL();
                    }
                }
            }
        }

        if (resource == null) {
            throw new IOException("FXML não encontrado: " + nomeArquivo);
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }
}
