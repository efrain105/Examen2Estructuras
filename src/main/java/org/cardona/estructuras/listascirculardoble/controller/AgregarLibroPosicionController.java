package org.cardona.estructuras.listascirculardoble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.listascirculardoble.modelo.Libro;
import org.cardona.estructuras.listascirculardoble.modelo.ListaCircularDobleLibros;
import org.cardona.estructuras.listascirculardoble.modelo.Nodo;
import org.cardona.estructuras.listascirculardoble.stages.MenuPrincipalStage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarLibroPosicionController implements Initializable {
    @FXML
    public TextField editorial;
    public TextField autor;
    public TextField titulo;
    public TextField posicion;
    public Label menuTitulo;
    public Pane ventanaprincipal;
    public Button guardar;
    public Button regresar;
    public TextArea outputArea;
    private Stage stage;
    @FXML
    private TextArea posicionesDisponibles;

    private ListaCircularDobleLibros lista;  // Reference to the main list

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setLista(ListaCircularDobleLibros lista) {
        this.lista = lista;  // Set the shared list from MenuPrincipalController
        mostrarListaNumerada();
    }

    private void mostrarListaNumerada() {
        Nodo actual = lista.primer();
        if (lista == null || lista.isEmpty()) {
            posicionesDisponibles.setText("La lista está vacía.");
            return;
        }

        StringBuilder posiciones = new StringBuilder("Posiciones de libros:\n");
        for (int i = 0; i < lista.size(); i++) {
            String libro = actual.getDato().getTitulo();// Obtener el libro en la posición
            posiciones.append((i + 1)).append(". ").append(libro).append("\n");
            actual = actual.getSiguiente();
        }
        posicionesDisponibles.setText(posiciones.toString());
    }

    public void onGuardar(ActionEvent actionEvent) throws Exception {
        String tituloLibro = titulo.getText();
        String autorLibro = autor.getText();
        String editorialLibro = editorial.getText();
        String posicionTexto = posicion.getText();

        if (!tituloLibro.isEmpty() && !autorLibro.isEmpty() && !editorialLibro.isEmpty() && !posicionTexto.isEmpty()) {
            try {
                int posicionLibro = Integer.parseInt(posicionTexto)-1;

                Libro nuevoLibro = new Libro(tituloLibro, autorLibro, editorialLibro);

                // Add the new book at the specified position
                lista.insertarEnPosicion(posicionLibro , nuevoLibro);

                System.out.println("Libro agregado en posición " + (posicionLibro + 1) + ": " + nuevoLibro);

                // Show an alert to notify the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Libro Agregado");
                alert.setHeaderText(null);
                alert.setContentText("El libro ha sido agregado en la posición " + (posicionLibro + 1) + " de la lista.");

                // Wait for the user to press "OK"
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // Close the current window (AgregarLibroPosicion window)
                        Stage stage = (Stage) guardar.getScene().getWindow();
                        stage.close();
                    }
                });
                MenuPrincipalStage menuPrincipalStage = new MenuPrincipalStage();
                menuPrincipalStage.start(stage);
            } catch (NumberFormatException e) {
                // Show a warning if the position is not a valid number
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Posición Inválida");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, ingrese un número válido para la posición.");
                alert.showAndWait();
            }
        } else {
            // Show a warning if any fields are empty
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
