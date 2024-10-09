package org.cardona.estructuras.listascirculardoble.stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cardona.estructuras.listascirculardoble.controller.MenuPrincipalController;

import java.util.Objects;

public class MenuPrincipalStage extends Application {
    private Stage primaryStage;
    private MenuPrincipalController controller;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listascirculardoble/menu-principal.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        MenuPrincipalController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/cardona/estructuras/listascirculardoble/style.css")).toExternalForm());
    }



    public static void lanzarApp() {
        launch();
    }
}
