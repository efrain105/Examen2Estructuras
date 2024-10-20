package org.cardona.estructuras.stages.listacircular;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.AgregarLibroFinalController;
import org.cardona.estructuras.controller.AgregarLibroPosicionController;

import java.util.Objects;

public class AgregarLibroPosStage extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(AgregarLibroPosicionController.class.getResource("/org/cardona/estructuras/listasV/agregar-libro-posicion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AgregarLibroFinalController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/cardona/estructuras/style.css")).toExternalForm());
    }
}
