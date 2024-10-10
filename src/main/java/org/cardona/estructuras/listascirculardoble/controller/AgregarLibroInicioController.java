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

public class AgregarLibroInicioController implements Initializable {
    public TextField editorial;
    public TextField autor;
    public TextField titulo;
    public Label menuTitulo;
    public Pane ventanaprincipal;
    public Button guardar;
    public Button regresar;
    private Stage stage;

    private ListaCircularDobleLibros lista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setLista(ListaCircularDobleLibros lista) {
        this.lista = lista;
    }

    public void onGuardar(ActionEvent actionEvent) throws Exception {
        String tituloLibro = titulo.getText();
        String autorLibro = autor.getText();
        String editorialLibro = editorial.getText();


        if (!tituloLibro.isEmpty() && !autorLibro.isEmpty() && !editorialLibro.isEmpty()) {
            Libro nuevoLibro = new Libro(tituloLibro, autorLibro, editorialLibro);

            lista.insertarInicio(nuevoLibro);

            System.out.println("Libro agregado al inicio: " + nuevoLibro);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Libro Agregado");
            alert.setHeaderText(null);
            alert.setContentText("El libro ha sido agregado al inicio de la lista.");


            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Stage stage = (Stage) guardar.getScene().getWindow();
                    stage.close();
                }
            });
            MenuPrincipalStage menuPrincipalStage = new MenuPrincipalStage();
            menuPrincipalStage.start(stage);
        } else {
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
