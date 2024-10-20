package org.cardona.estructuras.controller.pilasC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.cardona.estructuras.modelo.URLSS;
import org.cardona.estructuras.modelo.pila.Mipila;
import org.cardona.estructuras.modelo.pila.Pila;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;


public class PilasController implements Initializable {
    private Pila pila;
    private Stage stage;

    @FXML
    private Label pilaEstadoLabel, cimaLabel, tamanoLabel;
    @FXML
    private TextField urlTextField;
    @FXML
    private Button crearPilaBtn, apilarBtn, desapilarBtn, vaciarPilaBtn, destruirPilaBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize with empty or default values
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void crearPila() {
        if (pila != null) {
            showWarningAlert("Pila ya creada", "Destruye la pila antes de crear una nueva.");
        } else {
            pila = new Mipila();
            actualizarEstado();
        }
    }

    @FXML
    public void apilar() {
        if (pila == null) {
            showWarningAlert("Pila no creada", "Primero crea una pila.");
        } else {
            String url = urlTextField.getText();
            if (!url.isEmpty()) {
                pila.push(new URLSS(url));
                urlTextField.clear();
                actualizarEstado();
            } else {
                showWarningAlert("URL vacía", "Ingresa una URL válida.");
            }
        }
    }

    @FXML
    public void desapilar() {
        if (pila == null) {
            showWarningAlert("Pila no creada", "Primero crea una pila.");
        } else if (pila.isEmpty()) {
            showWarningAlert("Pila vacía", "No hay elementos para desapilar.");
        } else {
            pila.pop();
            actualizarEstado();
        }
    }

    @FXML
    public void vaciarPila() {
        if (pila == null) {
            showWarningAlert("Pila no creada", "Primero crea una pila.");
        } else {
            pila.clear();
            actualizarEstado();
        }
    }

    @FXML
    public void destruirPila() {
        if (pila == null) {
            showWarningAlert("Pila no creada", "Primero crea una pila.");
        } else {
            pila = null;  // Destroy the stack
            actualizarEstado();
        }
    }

    private void actualizarEstado() {
        if (pila == null || pila.isEmpty()) {
            cimaLabel.setText("Pila vacía");
            tamanoLabel.setText("Tamaño: 0");
        } else {
            cimaLabel.setText("Cima: " + pila.top().getUrl());
            tamanoLabel.setText("Tamaño: " + pila.size());
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

    public void setPila(Pila listaPila) {
        this.pila = listaPila;
        actualizarEstado();
    }
}

