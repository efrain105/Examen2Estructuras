package org.cardona.estructuras.controller.queueC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.cardona.estructuras.modelo.URLSS;
import org.cardona.estructuras.modelo.cola.MiQueue;
import org.cardona.estructuras.modelo.cola.Queue;
import java.net.URL;
import java.util.ResourceBundle;

public class QueueController implements Initializable {
    private Queue queue;
    private Stage stage;

    @FXML
    private Label queueEstadoLabel, cimaLabel, tamanoLabel;
    @FXML
    private TextField urlTextField;
    @FXML
    private Button crearQueueBtn, encolarBtn, desencolarBtn, vaciarQueueBtn, destruirQueueBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize with empty or default values
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void crearQueue() {
        if (queue != null) {
            showWarningAlert("Queue ya creada", "Destruye la queue antes de crear una nueva.");
        } else {
            queue = new MiQueue();
            actualizarEstado();
        }
    }

    @FXML
    public void encolar() {
        if (queue == null) {
            showWarningAlert("Queue no creada", "Primero crea una queue.");
        } else {
            String url = urlTextField.getText();
            if (!url.isEmpty()) {
                queue.enqueue(new URLSS(url));
                urlTextField.clear();
                actualizarEstado();
            } else {
                showWarningAlert("URL vacía", "Ingresa una URL válida.");
            }
        }
    }

    @FXML
    public void desencolar() {
        if (queue == null) {
            showWarningAlert("Queue no creada", "Primero crea una queue.");
        } else if (queue.isEmpty()) {
            showWarningAlert("Queue vacía", "No hay elementos para desencolar.");
        } else {
            queue.dequeue();
            actualizarEstado();
        }
    }

    @FXML
    public void vaciarQueue() {
        if (queue == null) {
            showWarningAlert("Queue no creada", "Primero crea una queue.");
        } else {
            queue.clear();
            actualizarEstado();
        }
    }

    @FXML
    public void destruirQueue() {
        if (queue == null) {
            showWarningAlert("Queue no creada", "Primero crea una queue.");
        } else {
            queue = null;  // Destroy the queue
            actualizarEstado();
        }
    }

    private void actualizarEstado() {
        if (queue == null || queue.isEmpty()) {
            cimaLabel.setText("Queue vacía");
            tamanoLabel.setText("Tamaño: 0");
        } else {
            cimaLabel.setText("Frente: " + queue.front().getUrl());
            tamanoLabel.setText("Tamaño: " + queue.size());
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

    public void setQueue(Queue queue) {
        this.queue = queue;
        actualizarEstado();
    }

}