package org.cardona.estructuras.controller.listasDoblesC;

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
import org.cardona.estructuras.controller.listacircularc.AgregarLibroInicioController;
import org.cardona.estructuras.controller.listacircularc.ModificarLibroPosicionController;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listasdobles.ListaDobleLibros;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListaDobleAppController implements Initializable {
    private static ListaDobleLibros listaDoble;

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

    public void setLista(ListaDobleLibros lista) {
        this.listaDoble = lista;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarLista();
    }


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
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
        if (listaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (listaDoble == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/agregar-libro-inicio.fxml"));
            Parent root = loader.load();

            AgregarLibroInicioController agregarLibroInicioController = loader.getController();
            agregarLibroInicioController.setListaDoble(listaDoble);  // Pass the existing list to the new controller
            agregarLibroInicioController.setListaDobleLibrosAppController(this);  // Pasar la referencia del controlador principal

            Stage stage = new Stage();
            agregarLibroInicioController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }



    }

    public void OnActionSalir(ActionEvent actionEvent) throws Exception {
        TabPane tabPane = (TabPane) this.stage.getScene().lookup("#paneCirculares"); // Reemplaza "tabPaneId" con el ID de tu TabPane
        Tab currentTab = tabPane.getSelectionModel().getSelectedItem();
        tabPane.getTabs().remove(currentTab);
    }
    public void OnGoAgregarLibroFinal(ActionEvent actionEvent) throws Exception {
        if (listaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (listaDoble == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/agregar-libro-final.fxml"));
            Parent root = loader.load();

            AgregarLibroFinalController agregarLibroFinalController = loader.getController();
            agregarLibroFinalController.setListaDoble(listaDoble);  // Pass the existing list to the new controller
            agregarLibroFinalController.setListaDobleLibrosAppController(this);  // Pasar la referencia del controlador principal

            Stage stage = new Stage();
            agregarLibroFinalController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void OnGoAgregarLibroPosicion(ActionEvent actionEvent) throws Exception {
        if (listaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (listaDoble == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/agregar-libro-posicion.fxml"));
            Parent root = loader.load();

            AgregarLibroPosicionController agregarLibroPosicionController = loader.getController();
            agregarLibroPosicionController.setListaDoble(listaDoble);  // Pass the existing list to the new controller
            agregarLibroPosicionController.setListaDobleAppController(this);  // Pasar la referencia del controlador principal

            Stage stage = new Stage();
            agregarLibroPosicionController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }

    }
    // Crear lista

    @FXML
    private void crearLista() {

        if (listaDoble != null) {
            showWarningAlert("Lista ya creada", "La lista ya ha sido creada.");
            return;
        }

        listaDoble = new ListaDobleLibros();


        // Agregamos 5 libros automáticamente
        listaDoble.insertarFinal(new Libro("Cien años de soledad", "Gabriel García Márquez", "Sudamericana"));
        listaDoble.insertarFinal(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles"));
        listaDoble.insertarFinal(new Libro("La Odisea", "Homero", "Desconocida"));
        listaDoble.insertarFinal(new Libro("1984", "George Orwell", "Secker & Warburg"));
        listaDoble.insertarFinal(new Libro("Matar a un ruiseñor", "Harper Lee", "J.B. Lippincott & Co."));


        mostrarLista();

    }
    @FXML
    private void OnGoModificarLibro() throws IOException {
        if (listaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (listaDoble.isEmpty()) {
           showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/modificar-libro-posicion.fxml"));
            Parent root = loader.load();
            ModificarLibroPosicionController modificarLibroPosicionController = loader.getController();
            modificarLibroPosicionController.setListaDoble(listaDoble);  // Pass the existing list to the new controller
            modificarLibroPosicionController.setListaDobleAppController(this);  // Pasar la referencia del controlador principal

        Stage stage = new Stage();
            modificarLibroPosicionController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();


    }
    // Mostrar lista de libros

    @FXML
    public void mostrarLista() {
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

    // Eliminar libro al inicio

    @FXML
    private void eliminarLibroInicio() {
        if(listaDoble == null){
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (!listaDoble.isEmpty()) {
            listaDoble.quitarInicio();
            mostrarLista();
        } else {
            showWarningAlert("Lista vacía", "La lista está vacía.");
        }

        mostrarLista();
    }
    // Eliminar libro al final
    @FXML
    private void eliminarLibroFinal() {
        if(listaDoble == null){
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (!listaDoble.isEmpty()) {
            listaDoble.quitarFinal();
            mostrarLista();
        } else {
            showWarningAlert("Lista vacía", "La lista está vacía.");
        }
        mostrarLista();
    }
    // Eliminar libro en posición específica

    @FXML
    private void eliminarLibroPosicion() {
        if(listaDoble == null){
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (listaDoble.isEmpty()) {
           showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }
        while (true) {
            try {
                int posicion = validarIndice(listaDoble.size());
                listaDoble.quitarEnPosicion(posicion - 1);
                mostrarLista();
                break;
            } catch (IllegalStateException e) {
                // toDo
            }
        }
    }

    @FXML
    private void vaciarLista() {
        if (listaDoble == null || listaDoble.isEmpty()) {
            showWarningAlert("Lista vacía o no creada", "La lista está vacía o no ha sido creada.");
            return;
        }
        listaDoble.vaciarLista();
        mostrarLista();
    }
    // Destruir lista

    @FXML
    private void destruirLista() {
        if (listaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
        }else {
            listaDoble = null;
            showWarningAlert("Lista destruida", "La lista ha sido destruida.");
            mostrarLista();
        }
    }


private int validarIndice(int tamanoLista) {
    int index = -1;
    while (index < 1 || index > tamanoLista) {
        if (index != -1) {
            showWarningAlert("Índice inválido", "Por favor, ingrese un índice válido.");
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
            showWarningAlert("Entrada no válida", "Por favor, ingrese un número válido.");
        }
    }
    return index;
}


}
