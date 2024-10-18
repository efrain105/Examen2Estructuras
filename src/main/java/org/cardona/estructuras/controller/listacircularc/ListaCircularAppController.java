package org.cardona.estructuras.controller.listacircularc;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.AgregarLibroFinalController;
import org.cardona.estructuras.controller.AgregarLibroPosicionController;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListaCircularAppController implements Initializable {
    private static ListaCircularDoble lista;

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
    public Button eliminarInicio;
    public Button eliminarFinal;
    public Button eliminarPos;
    private Stage stage;

    public void setLista(ListaCircularDoble lista) {
        this.lista = lista;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarLista();
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    private int validarIndice(int tamanoLista) {
        int index = -1;
        while (index < 1 || index > tamanoLista) {
            if (index != -1) {
                showWarningAlert("Índice no válido", "El índice debe ser un número entero entre 1 y " + tamanoLista + ".");
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
                showWarningAlert("Índice no válido", "El índice debe ser un número entero.");

            }
        }
        return index;
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

    @FXML
    public void onGoAgregarLibroInicio(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/agregar-libro-inicio.fxml"));
        Parent root = loader.load();

        AgregarLibroInicioController agregarLibroInicioController = loader.getController();
        agregarLibroInicioController.setListaCircular(lista);
        agregarLibroInicioController.setListaCircularAppController(this);

        Stage stage = new Stage();
        agregarLibroInicioController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void OnActionSalir(ActionEvent actionEvent) throws Exception {
        TabPane tabPane = (TabPane) this.stage.getScene().lookup("#paneCirculares");
        Tab currentTab = tabPane.getSelectionModel().getSelectedItem();
        tabPane.getTabs().remove(currentTab);
    }

    @FXML
    public void OnGoAgregarLibroFinal(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/agregar-libro-final.fxml"));
        Parent root = loader.load();

        AgregarLibroFinalController agregarLibroFinalController = loader.getController();
        agregarLibroFinalController.setListaCircularDoble(lista);
        agregarLibroFinalController.setListaCircularAppController(this);

        Stage stage = new Stage();
        agregarLibroFinalController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void OnGoAgregarLibroPosicion(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/agregar-libro-posicion.fxml"));
        Parent root = loader.load();

        AgregarLibroPosicionController agregarLibroPosicionController = loader.getController();
        agregarLibroPosicionController.setListaCircular(lista);
        agregarLibroPosicionController.setListaCircularAppController(this);

        Stage stage = new Stage();
        agregarLibroPosicionController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void crearLista() {
        if (lista != null) {
            showWarningAlert("La lista ya existe", "La lista ya ha sido creada.");
            return;
        }

        lista = new ListaCircularDoble();
        lista.insertarFinal(new Libro("Cien años de soledad", "Gabriel García Márquez", "Sudamericana"));
        lista.insertarFinal(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles"));
        lista.insertarFinal(new Libro("La Odisea", "Homero", "Desconocida"));
        lista.insertarFinal(new Libro("1984", "George Orwell", "Secker & Warburg"));
        lista.insertarFinal(new Libro("Matar a un ruiseñor", "Harper Lee", "J.B. Lippincott & Co."));

        mostrarLista();
    }

    @FXML
    public void OnGoModificarLibro() throws IOException {
        if (lista == null) {
            showWarningAlert("La lista no existe", "La lista aun no ha sido creada.");
            return;
        }

        if (lista.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/modificar-libro-posicion.fxml"));
        Parent root = loader.load();
        ModificarLibroPosicionController modificarLibroPosicionController = loader.getController();
        modificarLibroPosicionController.setListaCircular(lista);
        modificarLibroPosicionController.setListaCircularAppController(this);

        Stage stage = new Stage();
        modificarLibroPosicionController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void mostrarLista() {
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

    @FXML
    private void eliminarLibroInicio() {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (!lista.isEmpty()) {
            lista.quitarInicio();
            mostrarLista();
        } else {
            showWarningAlert("Lista vacía", "La lista está vacía.");
        }
    }

    @FXML
    private void eliminarLibroFinal() {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (!lista.isEmpty()) {
            lista.quitarFinal();
            mostrarLista();
        } else {
            showWarningAlert("Lista vacía", "La lista está vacía.");
        }
    }

    @FXML
    private void eliminarLibroPosicion() {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (lista.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }

        while (true) {
            try {
                int posicion = validarIndice(lista.size());
                lista.quitarEnPosicion(posicion - 1);
                mostrarLista();
                break;
            } catch (IllegalStateException e) {
                // Handle exception
            }
        }
    }

    @FXML
    private void vaciarLista() {
        if (lista == null || lista.isEmpty()) {
            showWarningAlert("Lista no creada o vacía", "La lista no ha sido creada o ya esta vacia.");
            return;
        }
        lista.vaciarLista();
        mostrarLista();
    }
    

    @FXML
    private void destruirLista() {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        lista = null;
        outputArea.setText("La lista ha sido destruida.");
    }

    public void OnRecorrerNodos(ActionEvent actionEvent) throws IOException {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/recorrer-nodos.fxml"));
            Parent root = loader.load();
            RecorrerNodosController recorrerNodosController = loader.getController();
            recorrerNodosController.setLista(lista);
            recorrerNodosController.setListaCircularAppController(this);
            Stage stage = new Stage();
            recorrerNodosController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
        if (lista.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }

    }
}

