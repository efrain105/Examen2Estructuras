package org.cardona.estructuras.stages;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.TabPaneController;

import java.util.Objects;

public class TabPaneStage extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/tabPane.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Segundo Examen Estructuras de datos");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.sizeToScene();
        TabPaneController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/cardona/estructuras/style.css")).toExternalForm());
    }



    public static void lanzarApp() {
        launch();
    }
}
