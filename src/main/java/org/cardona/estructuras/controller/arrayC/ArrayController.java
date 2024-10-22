package org.cardona.estructuras.controller.arrayC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;
import org.cardona.estructuras.modelo.array.vehiculos.camiones.Camion;
import org.cardona.estructuras.modelo.array.vehiculos.coches.Coche;
import org.cardona.estructuras.modelo.array.vehiculos.motos.Moto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ArrayController implements Initializable {
    public Pane ventanaprincipal;
    public Button goModificarVehiculo;
    public Button goMostrarVehiculos;
    public Button goEliminarVehiculo;
    public Button goAgregarCoche;
    public Button goVaciarEstacionamiento;
    public Button goCerrarEstacionamiento;
    public Button goAgregarVechiculo;
    public Button goAgregarMoto;
    public Button goAgregarCamion;
    public Button salir;
    public Button goCrearEstacionamiento;
    public TextArea outputArea;
    public Label menuTitulo;
    private Stage stage;

    private static int size;
    private static Vehiculo[] listaVehiculos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void onGoCrearEstacionamiento(ActionEvent actionEvent) {
        if (listaVehiculos != null) {
            if (listaVehiculos.length > 0) {
                showWarningAlert("Estacionamiento ya creado", "El estacionamiento ya ha sido creado");
                return;
            }
            showWarningAlert("Estacionamiento ya creado", "El estacionamiento ya ha sido creado.");
            return;
        }
        definirTamanoArray();
        Coche nuevocamuo = new Coche("MarcaEjemplo", "ModeloEjemplo", 2023, "ABC123", "123456789", "Rojo", "DueñoEjemplo", "150", 4);
        listaVehiculos[0] = nuevocamuo;
        Moto nuevaMoto = new Moto("MarcaMoto", "ModeloMoto", 2022, "XYZ789", "987654321", "Azul", 200, true);
        listaVehiculos[1] = nuevaMoto;
        Camion nuevoCamion = new Camion("MarcaCamion", "ModeloCamion", 2021, "LMN456", "654321987", "Verde", "DueñoCamion", 10000, 6);
        listaVehiculos[2] = nuevoCamion;

        mostrarLista();
    }

    public void onEliminarVechiculo(ActionEvent actionEvent) {
        if (listaVehiculos == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (listaVehiculos.length == 0) {
            showWarningAlert("Lista vacía", "No hay vehículos para eliminar.");
            return;
        }

        int indice = validarIndice(listaVehiculos.length);
        if (indice == -1) {
            return; // El usuario canceló la operación
        }

        // Eliminar el vehículo en el índice especificado
        listaVehiculos[indice - 1] = null;

        // Mostrar mensaje de confirmación
        showWarningAlert("Vehículo eliminado", "Vehículo eliminado exitosamente.");

        // Actualizar la lista mostrada
        mostrarLista();
    }

    public void onGoAgregarVechiculo(ActionEvent actionEvent) throws IOException {
        if (listaVehiculos == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (listaVehiculos == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-vehiculo-view.fxml"));
            Parent root = loader.load();

            AgregarVehiculoController agregarVehiculoController = loader.getController();
            agregarVehiculoController.setListaVehiculos(listaVehiculos);  // Pass the existing list to the new controller
            agregarVehiculoController.setArrayVehiculosController(this);  // Pasar la referencia del controlador principal

            Stage stage = new Stage();
            agregarVehiculoController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }


    public void onGoVaciarEstacionamiento(ActionEvent actionEvent) {
        if (listaVehiculos == null || listaVehiculos.length == 0) {
            showWarningAlert("Estacionamiento no creado o vacío", "El estacionamiento no ha sido creado o ya está vacío.");
            return;
        }

        for (int i = 0; i < listaVehiculos.length; i++) {
            listaVehiculos[i] = null;
        }

        showWarningAlert("Estacionamiento vaciado", "Todos los vehículos han sido eliminados del estacionamiento.");
        mostrarLista();
    }

    public void onGoCerrarEstacionamiento(ActionEvent actionEvent) {
        if (listaVehiculos == null) {
            showWarningAlert("Estacionamiento no creado", "El estacionamiento no ha sido creado.");
            return;
        }

        listaVehiculos = null;
        showWarningAlert("Estacionamiento cerrado", "El estacionamiento ha sido cerrado.");
        outputArea.setText("El estacionamiento ha sido cerrado.");
    }

    public void OnGoAgregarMoto(ActionEvent actionEvent) throws IOException {
        if (listaVehiculos == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-moto-view.fxml"));
        Parent root = loader.load();

        AgregarMotoController agregarMotoController = loader.getController();
        agregarMotoController.setListaVehiculos(listaVehiculos);  // Pass the existing list to the new controller
        agregarMotoController.setArrayVehiculosController(this);  // Pasar la referencia del controlador principal

        Stage stage = new Stage();
        agregarMotoController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void OnGoAgregarCamion(ActionEvent actionEvent) throws IOException {
        if (listaVehiculos == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-camion-view.fxml"));
        Parent root = loader.load();

        AgregarCamionController agregarCamionController = loader.getController();
        agregarCamionController.setListaVehiculos(listaVehiculos);  // Pass the existing list to the new controller
        agregarCamionController.setArrayVehiculosController(this);  // Pasar la referencia del controlador principal

        Stage stage = new Stage();
        agregarCamionController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onGoAgregarCoche(ActionEvent actionEvent) throws IOException {
        if (listaVehiculos == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-coche-view.fxml"));
        Parent root = loader.load();

        AgregarCocheController agregarCocheController = loader.getController();
        agregarCocheController.setListaVehiculos(listaVehiculos);  // Pass the existing list to the new controller
        agregarCocheController.setArrayVehiculosController(this);  // Pasar la referencia del controlador principal

        Stage stage = new Stage();
        agregarCocheController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onGoModificarVehiculo(ActionEvent actionEvent) {
        if (listaVehiculos == null || listaVehiculos.length == 0) {
            showWarningAlert("Estacionamiento no creado o vacío", "El estacionamiento no ha sido creado o ya está vacío.");
            return;
        }

        int indice = validarIndice(listaVehiculos.length);
        if (indice == -1) {
            return; // El usuario canceló la operación
        }

        Vehiculo vehiculo = listaVehiculos[indice - 1];
        if (vehiculo == null) {
            showWarningAlert("Slot vacío", "No hay vehículo en el slot especificado.");
            return;
        }

        try {
            Stage stage = new Stage();
            FXMLLoader loader;
            if (vehiculo instanceof Moto) {
                loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-moto-view.fxml"));
                Parent root = loader.load();
                AgregarMotoController controller = loader.getController();
                controller.setListaVehiculos(listaVehiculos);
                controller.setArrayVehiculosController(this);
                controller.setStage(stage);
                controller.setSlotIndex(indice - 1); // Pass the slot index
                controller.setVehiculo((Moto) vehiculo); // Pass the existing vehicle data
            } else if (vehiculo instanceof Coche) {
                loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-coche-view.fxml"));
                Parent root = loader.load();
                AgregarCocheController controller = loader.getController();
                controller.setListaVehiculos(listaVehiculos);
                controller.setArrayVehiculosController(this);
                controller.setStage(stage);
                controller.setSlotIndex(indice - 1); // Pass the slot index
                controller.setVehiculo((Coche) vehiculo); // Pass the existing vehicle data
                System.out.println("Este es el valor de indice: " + indice);
            } else if (vehiculo instanceof Camion) {
                loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-camion-view.fxml"));
                Parent root = loader.load();
                AgregarCamionController controller = loader.getController();
                controller.setListaVehiculos(listaVehiculos);
                controller.setArrayVehiculosController(this);
                controller.setStage(stage);
                controller.setSlotIndex(indice - 1); // Pass the slot index
                controller.setVehiculo((Camion) vehiculo); // Pass the existing vehicle data
                System.out.println("Este es el valor de indice: " + indice);

            } else {
                loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/arrayV/agregar-vehiculo-view.fxml"));
                Parent root = loader.load();
                AgregarVehiculoController controller = loader.getController();
                controller.setListaVehiculos(listaVehiculos);
                controller.setArrayVehiculosController(this);
                controller.setStage(stage);
                controller.setSlotIndex(indice - 1); // Pass the slot index
                controller.setVehiculo(vehiculo); // Pass the existing vehicle data
            }

            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showWarningAlert("Error", "No se pudo cargar la vista de modificación.");
        }

    }

    private int validarIndice(int tamanoLista) {
        int index = -1;
        while (index < 1 || index > tamanoLista || listaVehiculos[index - 1] == null) {
            if (index != -1) {
                if (index < 1 || index > tamanoLista) {
                    showWarningAlert("Índice no válido", "El índice debe ser un número entero entre 1 y " + tamanoLista + ".");
                } else if (listaVehiculos[index - 1] == null) {
                    showWarningAlert("Índice no válido", "No hay vehículo en el índice especificado.");
                }
            }

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Índice");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese el índice:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && result.get().matches("\\d+")) {
                index = Integer.parseInt(result.get());
            } else if (!result.isPresent()) {
                // Si se presiona cancelar retorna -1 y sales del método
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
    public void mostrarLista() {
        if (listaVehiculos == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }
        if (listaVehiculos.length == 0) {
            outputArea.setText("La lista está vacía.");
            return;
        }
        outputArea.setText(mostrarEstacionamiento());
    }


    private static void definirTamanoArray() {
        while (true) {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Información");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("El tamaño del estacionamiento se asignará entre 3 y 7");
            infoAlert.showAndWait();

            int tamano = 3 + (int) (Math.random() * 5);
            if (tamano >= 3 && tamano <= 7) {
                listaVehiculos = new Vehiculo[tamano]; // Initialize the array here

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Éxito");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tamaño del Estacionamiento definido a " + tamano);
                successAlert.showAndWait();

                mostrarEstacionamiento();
                break;
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("El tamaño debe estar entre 3 y 7.");
                errorAlert.showAndWait();
            }
        }
    }

    private static String mostrarEstacionamiento() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estacionamiento de vehículos:\n");
        sb.append("+------+----------------------+----------------------+----------------------+-------------------+-------------------+-------------------+------------------------------------------------------------+\n");
        sb.append("| Slot | Tipo                 | Dueño                | Marca                | Modelo            | Año               | Matrícula         | Detalles                                                   |\n");
        sb.append("+------+----------------------+----------------------+----------------------+-------------------+-------------------+-------------------+------------------------------------------------------------+\n");

        for (int i = 0; i < listaVehiculos.length; i++) {
            Vehiculo vehiculo = listaVehiculos[i];
            if (vehiculo != null) {
                String tipoVehiculo = "Genérico";
                if (vehiculo instanceof Moto) {
                    tipoVehiculo = "Moto";
                } else if (vehiculo instanceof Coche) {
                    tipoVehiculo = "Coche";
                } else if (vehiculo instanceof Camion) {
                    tipoVehiculo = "Camión";
                }
                sb.append(String.format("| %-4d | %-20s | %-20s | %-20s | %-17s | %-17d | %-17s | %-58s |\n",
                        i + 1,
                        tipoVehiculo,
                        vehiculo.getDuenio() != null ? vehiculo.getDuenio() : "N/A",
                        vehiculo.getMarca(),
                        vehiculo.getModelo(),
                        vehiculo.getAnio(),
                        vehiculo.getMatricula(),
                        vehiculo.toString()));  // Utilizamos un nuevo método formateado
            } else {
                sb.append(String.format("| %-4d | %-20s | %-20s | %-20s | %-17s | %-17s | %-17s | %-58s |\n",
                        i + 1,
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A"));
            }
        }

        sb.append("+------+----------------------+----------------------+----------------------+-------------------+-------------------+-------------------+------------------------------------------------------------+\n");
        return sb.toString();
    }



    public void setArray(Vehiculo[] arrayLista) {
        this.listaVehiculos = arrayLista;
    }
}
