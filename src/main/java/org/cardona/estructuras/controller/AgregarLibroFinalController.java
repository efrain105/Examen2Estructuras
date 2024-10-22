package org.cardona.estructuras.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.listacircularc.ListaCircularAppController;
import org.cardona.estructuras.controller.listasDoblesC.ListaDobleAppController;
import org.cardona.estructuras.controller.listasSimplesC.ListaSimpleAppController;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;
import org.cardona.estructuras.modelo.listasdobles.ListaDobleLibros;
import org.cardona.estructuras.modelo.listassimples.ListaSimple;

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

    private ListaCircularAppController listaCircularAppController;
    private ListaCircularDoble listaCircularDoble;

    private ListaSimple listaSimple;
    private ListaSimpleAppController listaSimpleAppController;

    private ListaDobleLibros listaDobleLibros;
    private ListaDobleAppController listaDobleLibrosAppController;

    public void setListaCircularAppController(ListaCircularAppController listaCircularAppController) {
        this.listaCircularAppController = listaCircularAppController;
    }
    public void setListaCircularDoble(ListaCircularDoble listaCircularDoble) {
        this.listaCircularDoble = listaCircularDoble;
    }

    public void setListaSimpleAppController(ListaSimpleAppController listaSimpleAppController) {
        this.listaSimpleAppController = listaSimpleAppController;
    }

    public void setListaSimple(ListaSimple lista) {
        this.listaSimple = lista;
    }

    public void setListaDoble(ListaDobleLibros lista) {
        this.listaDobleLibros = lista;
    }

    public void setListaDobleLibrosAppController(ListaDobleAppController listaCircularAppController) {
        this.listaDobleLibrosAppController = listaCircularAppController;
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }



    public void onGuardar(ActionEvent actionEvent) throws Exception {
        String tituloLibro = titulo.getText();
        String autorLibro = autor.getText();
        String editorialLibro = editorial.getText();

        if (!tituloLibro.isEmpty() && !autorLibro.isEmpty() && !editorialLibro.isEmpty()) {
            Libro nuevoLibro = new Libro(tituloLibro, autorLibro, editorialLibro);

            if(listaCircularDoble != null){
                listaCircularDoble.insertarFinal(nuevoLibro);
            }
            if(listaSimple != null){
                listaSimple.agregar(nuevoLibro);
            }

            if(listaDobleLibros != null){
                listaDobleLibros.insertarFinal(nuevoLibro);
            }

            System.out.println("Libro agregado al final: " + nuevoLibro);

            // Formato alertas
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Libro Agregado");
            alert.setHeaderText(null);
            alert.setContentText("El libro ha sido agregado al final de la lista.");

            // esperar  "OK"
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // cerrar vista actual (AgregarLibroInicio window)
                    Stage stage = (Stage) guardar.getScene().getWindow();
                    stage.close();

                }
            });
            if (listaCircularAppController != null){
                listaCircularAppController.mostrarLista();
            }
            if (listaSimpleAppController != null){
                listaSimpleAppController.mostrarLista();
            }
            if (listaDobleLibrosAppController != null){
                listaDobleLibrosAppController.mostrarLista();
            }

        } else {
            // alerta vacio
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
