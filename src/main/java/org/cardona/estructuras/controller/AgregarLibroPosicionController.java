package org.cardona.estructuras.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.listacircularc.ListaCircularAppController;
import org.cardona.estructuras.controller.listasDoblesC.ListaDobleAppController;
import org.cardona.estructuras.controller.listasSimplesC.ListaSimpleAppController;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;
import org.cardona.estructuras.modelo.listacircular.Nodo;
import org.cardona.estructuras.modelo.listasdobles.ListaDobleLibros;
import org.cardona.estructuras.modelo.listassimples.ListaSimple;

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

    private ListaCircularDoble listaCircularDoble;
    private ListaCircularAppController listaCircularAppController;

    private ListaSimple listaSimple;
    private ListaSimpleAppController listaSimpleAppController;

    private ListaDobleAppController listaDobleAppController;
    private ListaDobleLibros listaDoble;


    public void setListaCircularAppController(ListaCircularAppController listaCircularAppController) {
        this.listaCircularAppController = listaCircularAppController;
    }

    public void setListaCircular(ListaCircularDoble lista) {
        this.listaCircularDoble = lista;
        if (listaCircularDoble != null) {
            mostrarListaNumeradaCircular();
        }
    }

    public void setListaSimpleAppController(ListaSimpleAppController listaSimpleAppController) {
        this.listaSimpleAppController = listaSimpleAppController;
    }

    public void setListaSimple(ListaSimple lista) {
        this.listaSimple = lista;
        if (listaSimple != null) {
            mostrarListaNumeradaSimple();
        }
    }

    public void setListaDobleAppController(ListaDobleAppController listaSimpleAppController) {
        this.listaDobleAppController = listaSimpleAppController;
    }

    public void setListaDoble(ListaDobleLibros lista) {
        this.listaDoble = lista;
        if (listaDoble != null) {
            mostrarListaNumeradaDoble();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }


    private void mostrarListaNumeradaSimple() {
        int i = 1;
        ListaSimple.Nodo actual = listaSimple.getInicio();
        StringBuilder posiciones = new StringBuilder("Posiciones de libros:\n");
        while (actual != null) {
            String libro = actual.libros.getTitulo();
            posiciones.append(i).append(". ").append(libro).append("\n");
            actual = actual.siguiente;
            i++;
        }
        posicionesDisponibles.setText(posiciones.toString());
    }

    private void mostrarListaNumeradaCircular() {
        Nodo actual = listaCircularDoble.primer();
        if (listaCircularDoble == null || listaCircularDoble.isEmpty()) {
            posicionesDisponibles.setText("La lista está vacía.");
            return;
        }

        StringBuilder posiciones = new StringBuilder("Posiciones de libros:\n");
        for (int i = 0; i < listaCircularDoble.size(); i++) {
            String libro = actual.getDato().getTitulo();// Obtener el libro en la posición
            posiciones.append((i + 1)).append(". ").append(libro).append("\n");
            actual = actual.getSiguiente();
        }
        posicionesDisponibles.setText(posiciones.toString());
    }

    private void mostrarListaNumeradaDoble() {
        if (listaDoble == null || listaDoble.isEmpty()) {
            posicionesDisponibles.setText("La lista está vacía.");
            return;
        }

        ListaDobleLibros.Nodo actual = listaDoble.getInicio();
        StringBuilder posiciones = new StringBuilder("Posiciones de libros:\n");
        int i = 1;
        while (actual != null) {
            String libro = actual.libro.getTitulo(); // Obtener el libro en la posición
            posiciones.append(i).append(". ").append(libro).append("\n");
            actual = actual.siguiente;
            i++;
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

                int posicionLibro = Integer.parseInt(posicionTexto) - 1;

                if (posicionLibro < 0 || posicionLibro > indexActual()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Posición Inválida");
                    alert.setHeaderText(null);
                    alert.setContentText("La posición no puede ser menor a 1 o mayor al tamaño de la lista.");
                    alert.showAndWait();
                    return;
                }

                Libro nuevoLibro = new Libro(tituloLibro, autorLibro, editorialLibro);

                if (listaDoble != null) {
                    listaDoble.insertarEnPosicion(posicionLibro, nuevoLibro);
                }
                if (listaCircularDoble != null) {
                    listaCircularDoble.insertarEnPosicion(posicionLibro, nuevoLibro);
                }
                if (listaSimple != null) {
                    listaSimple.agregarEnPosicion(posicionLibro, nuevoLibro);
                }

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
                if (listaCircularAppController != null) {
                    listaCircularAppController.mostrarLista();
                }
                if (listaSimpleAppController != null) {
                    listaSimpleAppController.mostrarLista();
                }
                if (listaDobleAppController != null) {
                    listaDobleAppController.mostrarLista();
                }
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

    private int indexActual() {
        if (listaCircularDoble != null) {
            return listaCircularDoble.size();
        }
        if (listaSimple != null) {
            return listaSimple.size();
        }
        if (listaDoble != null) {
            return listaDoble.size();
        }
        return 0;
    }


    public void onRegresar(ActionEvent actionEvent) throws Exception {
        stage.close();
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
