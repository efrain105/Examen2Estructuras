package org.cardona.estructuras.controller.listasSimplesC;

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
import org.cardona.estructuras.modelo.listassimples.ListaSimple;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListaSimpleAppController implements Initializable {
    private static ListaSimple lista;

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

    public void setLista(ListaSimple lista) {
        this.lista = lista;
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

    public void OnActionSalir(ActionEvent actionEvent) throws Exception {
        stage.close();
    }

    public void OnGoAgregarLibroFinal(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/agregar-libro-final.fxml"));
            Parent root = loader.load();

            AgregarLibroFinalController agregarLibroFinalController = loader.getController();
            agregarLibroFinalController.setListaSimple(lista);  // Pass the existing list to the new controller
            agregarLibroFinalController.setListaSimpleAppController(this);  // Pasar la referencia del controlador principal

            Stage stage = new Stage();
            agregarLibroFinalController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void OnGoAgregarLibroPosicion(ActionEvent actionEvent) throws Exception {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/agregar-libro-posicion.fxml"));
            Parent root = loader.load();

            AgregarLibroPosicionController agregarLibroPosicionController = loader.getController();
            agregarLibroPosicionController.setListaSimple(lista);  // Pass the existing list to the new controller
            agregarLibroPosicionController.setListaSimpleAppController(this);  // Pasar la referencia del controlador principal

            Stage stage = new Stage();
            agregarLibroPosicionController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }

    }
    // Crear lista

    @FXML
    private void crearLista() {

        if (lista != null) {
            showWarningAlert("Lista ya creada", "La lista ya ha sido creada.");
            return;
        }

        lista = new ListaSimple();


        // Agregamos 5 libros automáticamente
        lista.agregar(new Libro("Cien años de soledad", "Gabriel García Márquez", "Sudamericana"));
        lista.agregar(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles"));
        lista.agregar(new Libro("La Odisea", "Homero", "Desconocida"));
        lista.agregar(new Libro("1984", "George Orwell", "Secker & Warburg"));
        lista.agregar(new Libro("Matar a un ruiseñor", "Harper Lee", "J.B. Lippincott & Co."));


        mostrarLista();

    }


    @FXML
    public void mostrarLista() {
        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }
        if (lista.estaVacia()) {
            outputArea.setText("La lista está vacía.");
            return;
        }
        outputArea.setText(lista.mostrar());
    }

    // Eliminar libro al inicio

    @FXML
    private void eliminarLibroFinal() {
        if(lista == null){
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (!lista.estaVacia()) {
            lista.quitarLibro();
            mostrarLista();
        } else {
            showWarningAlert("Lista vacía", "La lista está vacía.");
        }
        mostrarLista();
    }
    // Eliminar libro en posición específica

    @FXML
    private void eliminarLibroPosicion() {
        if(lista == null){
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (lista.estaVacia()) {
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
                // toDo
            }
        }
    }

    @FXML
    private void vaciarLista() {
        if (lista == null || lista.estaVacia()) {
            showWarningAlert("Lista vacía o no creada", "La lista está vacía o no ha sido creada.");
            return;
        }
        lista.vaciar();
        mostrarLista();
    }
    // Destruir lista

    @FXML
    private void destruirLista() {
        if (lista == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
        }else {
            lista = null;
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
           showWarningAlert("Entrada inválida", "Por favor, ingrese un número entero.");
        }
    }
    return index;
}

}
