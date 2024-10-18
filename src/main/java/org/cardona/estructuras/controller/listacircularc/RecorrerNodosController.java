package org.cardona.estructuras.controller.listacircularc;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;
import org.cardona.estructuras.modelo.listacircular.Nodo;

import java.net.URL;
import java.util.ResourceBundle;

public class RecorrerNodosController implements Initializable {
    public Button regresar;

    public Label menuTitulo;
    public Button primero;
    public Button siguiente;
    public Button anterior;
    public Button ultimo;
    public Button todos;
    public FlowPane flowPaneLibros;
    private Stage stage;
    private int contador = 0;
    private Nodo nodoActual;  // para recorrer la lista
    private ListaCircularDoble lista;  // Referencia a la lista compartida
    private ListaCircularAppController listaCircularAppController; // Referencia al controlador de la lista

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setListaCircularAppController(ListaCircularAppController listaCircularAppController) {
        this.listaCircularAppController = listaCircularAppController;
    }

    public void setLista(ListaCircularDoble lista) {
        this.lista = lista;
        this.nodoActual = lista.primer();  // Empezar por el primer nodo, especificamente el nodo cabeza
        this.contador = 1;
        mostrarNodoActual();
    }

    public void onRegresar(ActionEvent actionEvent) throws Exception {
        contador = 0;
        stage.close();
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void OnPrimero(ActionEvent actionEvent) {
        nodoActual = lista.primer();
        contador = 1;
        mostrarNodoActual();
    }

    public void OnSiguiente(ActionEvent actionEvent) {
        if (contador == lista.obtenerLibros().length) {
            contador = 0;
        }
        contador++;
        if (nodoActual != null) {
            nodoActual = lista.siguiente(nodoActual);
            mostrarNodoActual();
        }
    }

    public void OnAnterior(ActionEvent actionEvent) {
        if (contador == 1) {
            contador = lista.obtenerLibros().length + 1;
        }
        contador--;
        if (nodoActual != null) {
            nodoActual = lista.anterior(nodoActual);
            mostrarNodoActual();
        }
    }

    public void OnUltimo(ActionEvent actionEvent) {
        contador = lista.obtenerLibros().length;
        nodoActual = lista.ultimo();
        mostrarNodoActual();
    }

    public void OnTodos(ActionEvent actionEvent) {
        mostrarTodosLosLibros();
    }

    private void mostrarTodosLosLibros() {
        Libro[] libros = lista.obtenerLibros();
        flowPaneLibros.getChildren().clear();
        int contadorTodos = 1;
        for (Libro libro : libros) {
            Label libroLabel = new Label(contadorTodos + ". Título: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nEditorial: " + libro.getEditorial());
            libroLabel.setStyle(
                    "-fx-background-color: #ffffff;" +
                            "-fx-text-fill: #031f23;" +
                            "-fx-font-family: Arial;" +
                            "-fx-font-size: 17px;" +
                            "-fx-padding: 10px;" +
                            "-fx-border-color: #659d9f;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-background-radius: 10px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(45, 112, 105, 0.5), 8, 0.5, 0, 0);"
            );
            flowPaneLibros.getChildren().add(libroLabel);
            contadorTodos++;
        }
    }

    private void mostrarNodoActual() {
        flowPaneLibros.getChildren().clear();
        if (nodoActual != null) {
            Libro libro = nodoActual.getDato();
            Label libroLabel = new Label("Posicion: '" + contador + "' en la lista" + "\nTítulo: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nEditorial: " + libro.getEditorial());
            libroLabel.setStyle(
                    "-fx-background-color: #ffffff;" +
                            "-fx-text-fill: #031f23;" +
                            "-fx-font-family: Arial;" +
                            "-fx-font-size: 17px;" +
                            "-fx-padding: 10px;" +
                            "-fx-border-color: #659d9f;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-background-radius: 10px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(45, 112, 105, 0.5), 8, 0.5, 0, 0);"
            );
            flowPaneLibros.getChildren().add(libroLabel);
        }
    }
}
