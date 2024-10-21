package org.cardona.estructuras.controller.listacircularc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.listasDoblesC.ListaDobleAppController;
import org.cardona.estructuras.controller.listasSimplesC.ListaSimpleAppController;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;
import org.cardona.estructuras.modelo.listasdobles.ListaDobleLibros;
import org.cardona.estructuras.modelo.listassimples.ListaSimple;

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
    public VBox voxPos;
    private Stage stage;
    @FXML
    private TextArea posicionesDisponibles;

    private int slotIndexToController;

    public void setSlotIndex(int slotIndex) {
        this.slotIndexToController = slotIndex;
    }

    //Desde el menu se hace referencia a esta lista, es el mismo objeto
    private ListaCircularDoble listaCircularDoble;  // Referencia a la lista compartida
    private ListaCircularAppController listaCircularAppController;


    private ListaSimpleAppController listaSimpleAppController;

    private ListaDobleAppController listaDobleAppController;
    private ListaDobleLibros listaDoble;

    public void setListaCircularAppController(ListaCircularAppController listaCircularAppController) {
        this.listaCircularAppController = listaCircularAppController;
    }

    public void setListaCircular(ListaCircularDoble lista) {
        this.listaCircularDoble = lista;  // Establecer la lista compartida es importante mencionar this.lista para referirse a la lista de la clase
    }


    public void setListaDobleAppController(ListaDobleAppController listaDobleAppController) {
        this.listaDobleAppController = listaDobleAppController;
        listaDobleAppController.mostrarLista();
    }

    public void setListaDoble(ListaDobleLibros lista) {
        this.listaDoble = lista;  // Establecer la lista compartida es importante mencionar this.lista para referirse a la lista de la clase
    }


    public void setListaSimple(ListaSimple lista) {
        this.listaSimpleAppController = listaSimpleAppController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }


    /*
        @FXML
        void mostrarListaDoble() {
            if (listaDoble == null) {
                outputArea.setText("La lista no ha sido creada.");
                return;
            }
            if (listaDoble.isEmpty()) {
                outputArea.setText("La lista está vacía.");
                return;
            }
            outputArea.setText(listaDoble.mostrarLista());
        }

    */
    public void onRegresar(ActionEvent actionEvent) throws Exception {
        stage.close();
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
                if (posicionLibro < 0 || posicionLibro >= indexActual()) {
                    throw new IndexOutOfBoundsException("Posición no válida");
                }

                // crear un nuevo libro con los datos ingresados
                Libro libroModificado = new Libro(tituloLibro, autorLibro, editorialLibro);

                // Modificar el libro en la posición indicada
                if (listaDoble != null) {
                    listaDoble.modificarEnPosicion(posicionLibro, libroModificado);
                }
                if (listaCircularDoble != null) {
                    listaCircularDoble.modificarEnPosicion(posicionLibro, libroModificado);
                }

                System.out.println("Libro modificado en la posición " + (posicionLibro + 1) + ": " + libroModificado);

                // Mostar un mensaje de éxito
                showWarningAlert("Éxito", "El libro ha sido modificado exitosamente.");


                if (listaCircularAppController != null) {
                    listaCircularAppController.mostrarLista();
                }
                if (listaDobleAppController != null) {
                    listaDobleAppController.mostrarLista();
                }


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

    private void showWarningAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/org/cardona/estructuras/style.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");
        alert.showAndWait();
    }

    private int indexActual() {
        if (listaCircularDoble != null) {
            return listaCircularDoble.size();
        }
        if (listaDoble != null) {
            return listaDoble.size();
        }
        return 0;
    }


    public void setLibro(Libro libro) {
        if (libro != null) {
            posicion.setText(String.valueOf(slotIndexToController + 1));
            posicion.setDisable(true); // Deshabilitar el campo de slot
            titulo.setText(libro.getTitulo());
            autor.setText(libro.getAutor());
            editorial.setText(libro.getEditorial());
        }
    }
}
