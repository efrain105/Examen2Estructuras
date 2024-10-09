package org.cardona.estructuras.listascirculardoble.stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cardona.estructuras.listascirculardoble.controller.AgregarLibroInicioController;

import java.util.Objects;

public class AgregarLibroInicioStage extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(AgregarLibroInicioController.class.getResource("/org/cardona/estructuras/listascirculardoble/agregar-libro-inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AgregarLibroInicioController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/cardona/estructuras/listascirculardoble/style.css")).toExternalForm());
    }
}
