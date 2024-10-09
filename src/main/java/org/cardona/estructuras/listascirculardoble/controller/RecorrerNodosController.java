package org.cardona.estructuras.listascirculardoble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.cardona.estructuras.listascirculardoble.modelo.Libro;
import org.cardona.estructuras.listascirculardoble.modelo.ListaCircularDobleLibros;
import org.cardona.estructuras.listascirculardoble.modelo.Nodo;
import org.cardona.estructuras.listascirculardoble.stages.MenuPrincipalStage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecorrerNodosController implements Initializable {

    public Button regresar;
    public Label menuTitulo;
    public TextArea outputArea;
    public Button primero;
    public Button siguiente;
    public Button anterior;
    public Button ultimo;
    public Button todos;
    public FlowPane flowPaneLibros;
    private Stage stage;

    private ListaCircularDobleLibros lista;  // Reference to the main list
    private Nodo nodoActual;  // To track the current node

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setLista(ListaCircularDobleLibros lista) {
        this.lista = lista;
        this.nodoActual = lista.primer();  // Start at the first node
        mostrarNodoActual();
    }

    public void onRegresar(ActionEvent actionEvent) throws Exception {
        stage.close();
        MenuPrincipalStage menuPrincipalStage = new MenuPrincipalStage();
        menuPrincipalStage.start(stage);
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void OnPrimero(ActionEvent actionEvent) {
        nodoActual = lista.primer();
        mostrarNodoActual();
    }

    public void OnSiguiente(ActionEvent actionEvent) {
        if (nodoActual != null) {
            nodoActual = lista.siguiente(nodoActual);
            mostrarNodoActual();
        }
    }

    public void OnAnterior(ActionEvent actionEvent) {
        if (nodoActual != null) {
            nodoActual = lista.anterior(nodoActual);
            mostrarNodoActual();
        }
    }

    public void OnUltimo(ActionEvent actionEvent) {
        nodoActual = lista.ultimo();
        mostrarNodoActual();
    }

    public void OnTodos(ActionEvent actionEvent) {
        mostrarTodosLosLibros();
    }


    private void mostrarTodosLosLibros() {
        List<Libro> libros = lista.obtenerLibros();
        flowPaneLibros.getChildren().clear();
        for (Libro libro : libros) {
            Label libroLabel = new Label("Título: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nEditorial: " + libro.getEditorial());
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

    private void mostrarNodoActual() {
        flowPaneLibros.getChildren().clear();
        if (nodoActual != null) {
            Libro libro = nodoActual.getDato();
            Label libroLabel = new Label("Título: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nEditorial: " + libro.getEditorial());
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

