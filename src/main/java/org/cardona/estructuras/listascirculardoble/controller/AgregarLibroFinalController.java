package org.cardona.estructuras.listascirculardoble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.listascirculardoble.modelo.Libro;
import org.cardona.estructuras.listascirculardoble.modelo.ListaCircularDobleLibros;
import org.cardona.estructuras.listascirculardoble.stages.MenuPrincipalStage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarLibroFinalController implements Initializable {
    public TextField editorial;
    public TextField autor;
    public TextField titulo;
    public Label menuTitulo;
    public Pane ventanaprincipal;
    public Button guardar;
    public Button regresar;
    private Stage stage;

    private ListaCircularDobleLibros lista;  // Reference to the main list

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setLista(ListaCircularDobleLibros lista) {
        this.lista = lista;  // Set the shared list from MenuPrincipalController
    }

    public void onGuardar(ActionEvent actionEvent) throws Exception {
        String tituloLibro = titulo.getText();
        String autorLibro = autor.getText();
        String editorialLibro = editorial.getText();

        if (!tituloLibro.isEmpty() && !autorLibro.isEmpty() && !editorialLibro.isEmpty()) {
            Libro nuevoLibro = new Libro(tituloLibro, autorLibro, editorialLibro);

            // Add the new book at the beginning of the list
            lista.insertarFinal(nuevoLibro);

            System.out.println("Libro agregado al final: " + nuevoLibro);

            // Show an alert to notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Libro Agregado");
            alert.setHeaderText(null);
            alert.setContentText("El libro ha sido agregado al final de la lista.");

            // Wait for the user to press "OK"
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Close the current window (AgregarLibroInicio window)
                    Stage stage = (Stage) guardar.getScene().getWindow();
                    stage.close();
                }
            });
            MenuPrincipalStage menuPrincipalStage = new MenuPrincipalStage();
            menuPrincipalStage.start(stage);
        } else {
            // Show a warning if the fields are empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos antes de guardar.");

            alert.showAndWait();
        }

    }

    public void onRegresar(ActionEvent actionEvent) throws Exception {
        stage.close();
        MenuPrincipalStage menuPrincipalStage = new MenuPrincipalStage();
        menuPrincipalStage.start(stage);
    }
}
