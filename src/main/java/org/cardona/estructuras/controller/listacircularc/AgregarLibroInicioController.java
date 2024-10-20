package org.cardona.estructuras.controller.listacircularc;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.listasDoblesC.ListaDobleAppController;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;
import org.cardona.estructuras.modelo.listasdobles.ListaDobleLibros;

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

    private ListaCircularDoble listaCircularDoble;
    private ListaDobleLibros listaDobleLibros;


    private ListaCircularAppController listaCircularAppController;
    private ListaDobleAppController listaDobleLibrosAppController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setListaCircular(ListaCircularDoble lista) {
        this.listaCircularDoble = lista;
    }

    public void setListaCircularAppController(ListaCircularAppController listaCircularAppController) {
        this.listaCircularAppController = listaCircularAppController;
    }

    public void setListaDoble(ListaDobleLibros lista) {
        this.listaDobleLibros = lista;
    }

    public void setListaDobleLibrosAppController(ListaDobleAppController listaCircularAppController) {
        this.listaDobleLibrosAppController = listaCircularAppController;
    }

    public void onGuardar(ActionEvent actionEvent) throws Exception {
        String tituloLibro = titulo.getText();
        String autorLibro = autor.getText();
        String editorialLibro = editorial.getText();


        if (!tituloLibro.isEmpty() && !autorLibro.isEmpty() && !editorialLibro.isEmpty()) {
            Libro nuevoLibro = new Libro(tituloLibro, autorLibro, editorialLibro);

            listaCircularDoble.insertarInicio(nuevoLibro);

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

            listaCircularAppController.mostrarLista();
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
    }
}
