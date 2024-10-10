package org.cardona.estructuras.listascirculardoble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.listascirculardoble.modelo.Libro;
import org.cardona.estructuras.listascirculardoble.modelo.ListaCircularDobleLibros;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {
    private static ListaCircularDobleLibros lista = null;


    @FXML
    public Pane ventanaprincipal;
    public Button salir;
    public Label menuTitulo;

    public Button goAgregarLibroInicio;
    public Button goAgregarLibroFinal;
    public Button goAgregarLibroPosicion;
    public TextArea outputArea;
    public Button goModificarLibro;
    public Button recorrerNodos;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarLista();
    }


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    @FXML
    public void onGoAgregarLibroInicio(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada.");
            alert.showAndWait();
            return;
        }

        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listascirculardoble/agregar-libro-inicio.fxml"));
            Parent root = loader.load();

            AgregarLibroInicioController agregarLibroInicioController = loader.getController();
            agregarLibroInicioController.setLista(lista);  // Pass the existing list to the new controller

            Stage stage = new Stage();
            agregarLibroInicioController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
        stage.close();


    }

    public void OnActionSalir(ActionEvent actionEvent) throws Exception {
        stage.close();
    }

    public void OnGoAgregarLibroFinal(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada.");
            alert.showAndWait();
            return;
        }
        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listascirculardoble/agregar-libro-final.fxml"));
            Parent root = loader.load();

            AgregarLibroFinalController agregarLibroFinalController = loader.getController();
            agregarLibroFinalController.setLista(lista);  // Pass the existing list to the new controller

            Stage stage = new Stage();
            agregarLibroFinalController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
        stage.close();
    }

    public void OnGoAgregarLibroPosicion(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada.");
            alert.showAndWait();
            return;
        }

        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listascirculardoble/agregar-libro-posicion.fxml"));
            Parent root = loader.load();

            AgregarLibroPosicionController agregarLibroPosicionController = loader.getController();
            agregarLibroPosicionController.setLista(lista);  // Pass the existing list to the new controller

            Stage stage = new Stage();
            agregarLibroPosicionController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
        stage.close();
    }

    // Crear lista
    @FXML
    private void crearLista() {

        if (lista != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("La lista ya existe");
            alert.setHeaderText(null);
            alert.setContentText("La lista ya ha sido creada.");
            alert.showAndWait();
            return;
        }

        lista = new ListaCircularDobleLibros();


        // Agregamos 5 libros automáticamente
        lista.insertarFinal(new Libro("Cien años de soledad", "Gabriel García Márquez", "Sudamericana"));
        lista.insertarFinal(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles"));
        lista.insertarFinal(new Libro("La Odisea", "Homero", "Desconocida"));
        lista.insertarFinal(new Libro("1984", "George Orwell", "Secker & Warburg"));
        lista.insertarFinal(new Libro("Matar a un ruiseñor", "Harper Lee", "J.B. Lippincott & Co."));


        mostrarLista();

    }

    @FXML
    private void OnGoModificarLibro() throws IOException {
        if (lista == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("La lista no existe");
            alert.setHeaderText(null);
            alert.setContentText("La lista aun no ha sido creada.");
            alert.showAndWait();
            return;
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listascirculardoble/modificar-libro-posicion.fxml"));
            Parent root = loader.load();

            ModificarLibroPosicionController modificarLibroPosicionController = loader.getController();
            modificarLibroPosicionController.setLista(lista);  // Pass the existing list to the new controller
            Stage stage = new Stage();
            modificarLibroPosicionController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
        if (lista.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista vacía");
            alert.setHeaderText(null);
            alert.setContentText("La lista está vacía.");
            alert.showAndWait();
            return;
        }
        stage.close();
    }
    // Mostrar lista de libros
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


    // Eliminar libro al inicio
    @FXML
    private void eliminarLibroInicio() {
        if(lista == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada.");
            alert.showAndWait();
            return;
        }
        if (!lista.isEmpty()) {
            lista.quitarInicio();
            mostrarLista();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista vacía");
            alert.setHeaderText(null);
            alert.setContentText("La lista está vacía.");
            alert.showAndWait();
        }

        mostrarLista();
    }

    // Eliminar libro al final
    @FXML
    private void eliminarLibroFinal() {
        if(lista == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada.");
            alert.showAndWait();
            return;
        }
        if (!lista.isEmpty()) {
            lista.quitarFinal();
            mostrarLista();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista vacía");
            alert.setHeaderText(null);
            alert.setContentText("La lista está vacía.");
            alert.showAndWait();
        }
        mostrarLista();
    }
    // Eliminar libro en posición específica
    @FXML
    private void eliminarLibroPosicion() {
        if(lista == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada.");
            alert.showAndWait();
            return;
        }
        if (lista.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista vacía");
            alert.setHeaderText(null);
            alert.setContentText("La lista está vacía.");
            alert.showAndWait();
            return;
        }
        while (true) {
            try {
                int posicion = validarIndice(lista.size());
                lista.quitarEnPosicion(posicion - 1);
                mostrarLista();
                break;
            } catch (IllegalStateException e) {
                // toDo
            }
        }
    }

    @FXML
    private void vaciarLista() {
        if (lista == null || lista.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada o vacía");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada o ya esta vacia.");
            alert.showAndWait();
            return;
        }
        lista.vaciarLista();
        mostrarLista();
    }

    // Destruir lista
    @FXML
    private void destruirLista() {
        if (lista == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista no creada");
            alert.setHeaderText(null);
            alert.setContentText("La lista no ha sido creada.");
            alert.showAndWait();
        }else {
            lista = null;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lista destruida");
            alert.setContentText("La lista fue destruida.");
            alert.showAndWait();
            mostrarLista();
        }
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


    public void OnRecorrerNodos(ActionEvent actionEvent) throws IOException {
        if (lista == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("La lista no existe");
            alert.setHeaderText(null);
            alert.setContentText("La lista aun no ha sido creada.");
            alert.showAndWait();
            return;
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listascirculardoble/recorrer-nodos.fxml"));
            Parent root = loader.load();
            RecorrerNodosController recorrerNodosController = loader.getController();
            recorrerNodosController.setLista(lista);
            Stage stage = new Stage();
            recorrerNodosController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
        if (lista.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lista vacía");
            alert.setHeaderText(null);
            alert.setContentText("La lista está vacía.");
            alert.showAndWait();
            return;
        }
        stage.close();
    }
}
