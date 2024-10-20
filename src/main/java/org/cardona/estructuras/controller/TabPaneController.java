package org.cardona.estructuras.controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.cardona.estructuras.controller.arrayC.ArrayController;
import org.cardona.estructuras.controller.listacircularc.ListaCircularAppController;
import org.cardona.estructuras.controller.listasDoblesC.ListaDobleAppController;
import org.cardona.estructuras.controller.listasSimplesC.ListaSimpleAppController;
import org.cardona.estructuras.controller.pilasC.PilasController;
import org.cardona.estructuras.controller.queueC.QueueController;
import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;
import org.cardona.estructuras.modelo.cola.Queue;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;
import org.cardona.estructuras.modelo.listasdobles.ListaDobleLibros;
import org.cardona.estructuras.modelo.listassimples.ListaSimple;
import org.cardona.estructuras.modelo.pila.Pila;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabPaneController implements Initializable {

    public TabPane tabPane;
    public Tab paneCirculares;
    public Tab listasSimples;
    public Tab listasDobles;
    public Stage stage;
    public Tab pilas;
    public Tab queue;
    public Tab array;


    private ListaCircularAppController listaCircularAppController;
    private static ListaCircularDoble listaCircularDoble = null;

    private ListaSimpleAppController listaSimpleAppController;
    private static ListaSimple listaSimple = null;

    private ListaDobleAppController listaDobleAppController;
    private static ListaDobleLibros listaDoble = null;

    private PilasController listaPilaAppController;
    private static Pila listaPila = null;

    private QueueController queueController;
    private static Queue queueLista = null;

    private ArrayController arrayAppController;
    private static Vehiculo[] arrayLista = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar las diferentes listas


        // Cargar el contenido de MenuPrincipalStage en la pestaña cuando la aplicación se inicia
        if (paneCirculares.getContent() instanceof StackPane) {
            try {
                // Cargar el archivo FXML de ListaCircularApp
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/listas-circulares-view.fxml"));
                Parent loadListaCircularStage = loader.load();

                // Establecer el contenido de la pestaña con la vista cargada
                ((StackPane) paneCirculares.getContent()).getChildren().setAll(loadListaCircularStage);

                // Obtener el controlador de ListaCircularController
                listaCircularAppController = loader.getController();
                listaCircularAppController.setLista(listaCircularDoble);
                listaCircularAppController.setStage(stage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Listener para cuando se cambien las pestañas
        listasSimples.setOnSelectionChanged(event -> {
            if (listasSimples.isSelected() && listasSimples.getContent() instanceof StackPane) {
                if (listaSimpleAppController == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/pane-listas-simples.fxml"));
                        Parent listasSimplesContent = loader.load();
                        ((StackPane) listasSimples.getContent()).getChildren().setAll(listasSimplesContent);

                        // Obtener el controlador de ListaSimpleAppController
                        listaSimpleAppController = loader.getController();
                        listaSimpleAppController.setLista(listaSimple);
                        listaSimpleAppController.setStage(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        listasDobles.setOnSelectionChanged(event -> {
            if (listasDobles.isSelected() && listasDobles.getContent() instanceof StackPane) {
                if (listaDobleAppController == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/listasV/pane-lista-doble.fxml"));
                        Parent listasDoblesContent = loader.load();
                        ((StackPane) listasDobles.getContent()).getChildren().setAll(listasDoblesContent);

                        // Obtener el controlador de ListaDobleAppController
                        listaDobleAppController = loader.getController();
                        listaDobleAppController.setLista(listaDoble);
                        listaDobleAppController.setStage(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        pilas.setOnSelectionChanged(event -> {
            if (pilas.isSelected() && pilas.getContent() instanceof StackPane) {
                if (listaPilaAppController == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/pilasQueueV/pila-view.fxml"));
                        Parent pilasContent = loader.load();

                        ((StackPane) pilas.getContent()).getChildren().setAll(pilasContent);

                        // Obtener el controlador de pilasController
                        listaPilaAppController = loader.getController();
                        listaPilaAppController.setPila(listaPila);
                        listaPilaAppController.setStage(stage);


                        // Adjust the stage size based on the content
                        //stage.sizeToScene();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        queue.setOnSelectionChanged(event -> {
            if (queue.isSelected() && queue.getContent() instanceof StackPane) {
                if (queueController == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/pilasQueueV/queue-view.fxml"));
                        Parent queueContent = loader.load();

                        ((StackPane) queue.getContent()).getChildren().setAll(queueContent);

                        // Obtener el controlador de QueueController
                        queueController = loader.getController();
                        queueController.setQueue(queueLista);
                        queueController.setStage(stage);

                        // Adjust the stage size based on the content
                        stage.sizeToScene();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        array.setOnSelectionChanged(event -> {
            if (array.isSelected() && array.getContent() instanceof StackPane) {
                if (arrayAppController == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/array-view.fxml"));
                        Parent arrayContent = loader.load();

                        ((StackPane) array.getContent()).getChildren().setAll(arrayContent);

                        // Obtener el controlador de ArrayController
                        arrayAppController = loader.getController();
                        arrayAppController.setArray(arrayLista);
                        arrayAppController.setStage(stage);

                        // Adjust the stage size based on the content
                        stage.sizeToScene();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }


    public void OnListasSimples(Event event) {
    }

    public void OnListasDobles(Event event) {
    }

    public void OnPilas(Event event) {


    }

    public void OnQueue(Event event) {
    }
}