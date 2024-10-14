package org.cardona.estructuras.listascirculardoble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.cardona.estructuras.listascirculardoble.modelo.Libro;
import org.cardona.estructuras.listascirculardoble.modelo.ListaCircularDobleLibros;
import org.cardona.estructuras.listascirculardoble.modelo.Nodo;
import org.cardona.estructuras.listascirculardoble.stages.MenuPrincipalStage;

import java.net.URL;
import java.util.Optional;
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
    public VBox voxPos;
    private Stage stage;
    @FXML
    private TextArea posicionesDisponibles;

    private ListaCircularDobleLibros lista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setLista(ListaCircularDobleLibros lista) {
        this.lista = lista;
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

                if (posicionLibro < 0 || posicionLibro > lista.size()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Posición Inválida");
                    alert.setHeaderText(null);
                    alert.setContentText("La posición no puede ser menor a 1 o mayor al tamaño de la lista.");
                    alert.showAndWait();
                    return;
                }
                Libro nuevoLibro = new Libro(tituloLibro, autorLibro, editorialLibro);


                lista.insertarEnPosicion(posicionLibro , nuevoLibro);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Libro Agregado");
                alert.setHeaderText(null);
                alert.setContentText("El libro ha sido agregado en la posición " + (posicionLibro + 1) + " de la lista.");

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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Posición Inválida");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, ingrese un número válido para la posición.");
                alert.showAndWait();
            }
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

    private int validarIndice(int tamanoLista) {
        int index = -1;
        while (index < 1 || index > tamanoLista) {
            if (index != -1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Índice no válido");
                alert.setHeaderText(null);
                alert.setContentText("El índice no puede ser menor a 1 o mayor al tamaño de la lista.");
                alert.showAndWait();
            }

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Índice");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese el índice:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && result.get().matches("\\d+")) {
                index = Integer.parseInt(result.get());
            } else if (!result.isPresent()) {
                // Si se preciona cancel retorna -1 y sales del método
                return -1;
            } else {
                Alert invalidInputAlert = new Alert(Alert.AlertType.WARNING);
                invalidInputAlert.setTitle("Entrada no válida");
                invalidInputAlert.setHeaderText(null);
                invalidInputAlert.setContentText("Por favor, ingrese un número válido.");
                invalidInputAlert.showAndWait();
            }
        }
        return index;
    }
}
