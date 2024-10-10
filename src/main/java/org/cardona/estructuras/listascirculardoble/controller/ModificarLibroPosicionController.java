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

    //Desde el menu se hace referencia a esta lista, es el mismo objeto
    private ListaCircularDobleLibros lista;  // Referencia a la lista compartida


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setLista(ListaCircularDobleLibros lista) {
        this.lista = lista;  // Establecer la lista compartida es importante mencionar this.lista para referirse a la lista de la clase
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

                // Validar que la posición sea válida
                if (posicionLibro < 0 || posicionLibro >= lista.size()) {
                    throw new IndexOutOfBoundsException("Posición no válida");
                }

                // crear un nuevo libro con los datos ingresados
                Libro libroModificado = new Libro(tituloLibro, autorLibro, editorialLibro);

                // Modificar el libro en la posición indicada
                lista.modificarEnPosicion(posicionLibro, libroModificado);

                System.out.println("Libro modificado en la posición " + (posicionLibro + 1) + ": " + libroModificado);

                // Mostar un mensaje de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Libro Modificado");
                alert.setHeaderText(null);
                alert.setContentText("El libro ha sido modificado en la posición " + (posicionLibro + 1) + " de la lista.");

                // Esperar a que el usuario presione "OK"
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Stage stage = (Stage) modificar.getScene().getWindow();
                        mostrarLista();
                    }
                });

            } catch (NumberFormatException e) {
                // Mostrar un mensaje de advertencia si la posición no es un número
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Posición Inválida");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, ingrese un número válido para la posición.");
                alert.showAndWait();
            } catch (IndexOutOfBoundsException e) {
                // Mostrar un mensaje de advertencia si la posición no es válida
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Posición Inválida");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // Mostar un mensaje de advertencia si faltan campos por llenar
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos antes de modificar.");

            alert.showAndWait();
        }
    }

}
