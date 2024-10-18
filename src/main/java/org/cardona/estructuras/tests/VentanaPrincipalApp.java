package org.cardona.estructuras.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipalApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carga del archivo FXML para la interfaz gráfica
        FXMLLoader fxmlLoader = new FXMLLoader(VentanaPrincipalApp.class.getResource("/org/cardona/estructuras/reuse-sheet.fxml"));

        // Configura la escena y su tamaño
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);  // Ajusta el tamaño a la interfaz diseñada

        // Establece el título de la ventana
        stage.setTitle("Gestión de Lista Circular de Libros");

        // Aplica la escena y muestra la ventana
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
