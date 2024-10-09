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

public class ModificarLibroPosicionController implements Initializable {
    @FXML
    public TextField editorial;
    public TextField autor;
    public TextField titulo;
    public TextField posicion;
    public Label menuTitulo;
    public Pane ventanaprincipal;
    public Button regresar;
    public TextArea outputArea;
    public Button modificar;
    private Stage stage;
    @FXML
    private TextArea posicionesDisponibles;

    private ListaCircularDobleLibros lista;  // Reference to the main list

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setLista(ListaCircularDobleLibros lista) {
        this.lista = lista;  // Set the shared list from MenuPrincipalController
        mostrarLista();
    }

    @FXML
    void mostrarLista() {
        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }
        if (lista.isEmpty()) {
            outputArea.setText("La lista está vacía.");
            return;
        }
        outputArea.setText(lista.mostrarLista());
    }


    public void onRegresar(ActionEvent actionEvent) throws Exception {
        stage.close();
        MenuPrincipalStage menuPrincipalStage = new MenuPrincipalStage();
        menuPrincipalStage.start(stage);
    }


    public void onModificar(ActionEvent actionEvent) {
        String tituloLibro = titulo.getText();
        String autorLibro = autor.getText();
        String editorialLibro = editorial.getText();
        String posicionTexto = posicion.getText();

        if (!tituloLibro.isEmpty() && !autorLibro.isEmpty() && !editorialLibro.isEmpty() && !posicionTexto.isEmpty()) {
            try {
                int posicionLibro = Integer.parseInt(posicionTexto) - 1;

                // Validate the position
                if (posicionLibro < 0 || posicionLibro >= lista.size()) {
                    throw new IndexOutOfBoundsException("Posición no válida");
                }

                // Create the new book to replace the existing one
                Libro libroModificado = new Libro(tituloLibro, autorLibro, editorialLibro);

                // Modify the book in the specified position
                lista.modificarEnPosicion(posicionLibro, libroModificado);

                System.out.println("Libro modificado en la posición " + (posicionLibro + 1) + ": " + libroModificado);

                // Show a success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Libro Modificado");
                alert.setHeaderText(null);
                alert.setContentText("El libro ha sido modificado en la posición " + (posicionLibro + 1) + " de la lista.");

                // Wait for the user to press "OK"
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Stage stage = (Stage) modificar.getScene().getWindow();
                        mostrarLista();
                    }
                });

            } catch (NumberFormatException e) {
                // Show a warning if the position is not a valid number
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Posición Inválida");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, ingrese un número válido para la posición.");
                alert.showAndWait();
            } catch (IndexOutOfBoundsException e) {
                // Show a warning if the position is out of bounds
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Posición Inválida");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // Show a warning if any fields are empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos antes de modificar.");

            alert.showAndWait();
        }
    }

}
