package org.cardona.estructuras.controller.arrayC;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;
import org.cardona.estructuras.modelo.array.vehiculos.camiones.Camion;
import org.cardona.estructuras.modelo.array.vehiculos.coches.Coche;


import java.net.URL;
import java.util.ResourceBundle;

public class AgregarVehiculoController implements Initializable {
    public Pane ventanaprincipal;
    public Label menuTitulo;
    public TextField marca;
    public TextField modelo;
    public TextField anio;
    public TextField matricula;
    public TextField numeroMotor;
    public TextField color;
    public TextField duenio;
    public TextField slot;
    public Button guardar;
    public Button regresar;
    private Stage stage;
    private int slotIndex;

    // Existing methods...

    private Vehiculo[] vehiculos;


    private ArrayController arrayVehiculosController;

    private int slotIndexToController = 50;


    public void setListaVehiculos(Vehiculo[] listaVehiculos) {
        this.vehiculos = listaVehiculos;
    }
    public void setArrayVehiculosController(ArrayController arrayVehiculosController) {
        this.arrayVehiculosController = arrayVehiculosController;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndexToController = slotIndex;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void onGuardar(ActionEvent actionEvent) {
        String marcaVehiculo = marca.getText();
        String modeloVehiculo = modelo.getText();
        String anioVehiculo = anio.getText();
        String matriculaVehiculo = matricula.getText();
        String numeroMotorVehiculo = numeroMotor.getText();
        String colorVehiculo = color.getText();
        String duenioVehiculo = duenio.getText();
        String slotEstacionamiento = slot.getText();

        if (!isValidMarca(marcaVehiculo)) {
            showWarningAlert("Datos inválidos", "La marca del vehículo no es válida.");
            return;
        }

        if (!isValidModelo(modeloVehiculo)) {
            showWarningAlert("Datos inválidos", "El modelo del vehículo no es válido.");
            return;
        }

        if (!isValidAnio(anioVehiculo)) {
            showWarningAlert("Datos inválidos", "El año del vehículo no es válido.");
            return;
        }

        if (!isValidMatricula(matriculaVehiculo)) {
            showWarningAlert("Datos inválidos", "La matrícula del vehículo no es válida.");
            return;
        }

        int slotIndex = Integer.parseInt(slotEstacionamiento) - 1;

        if (slotIndex == slotIndexToController) {
            Vehiculo nuevoCoche = new Vehiculo(marcaVehiculo, modeloVehiculo, Integer.parseInt(anioVehiculo), matriculaVehiculo, numeroMotorVehiculo, colorVehiculo, duenioVehiculo);
            vehiculos[slotIndex] = nuevoCoche;
            slotIndexToController = 50;
        } else {
            if (!isValidSlot(slotEstacionamiento)) {
                showWarningAlert("Datos inválidos", "El slot de estacionamiento no es válido.");
                return;
            }
            Vehiculo nuevoCoche = new Vehiculo(marcaVehiculo, modeloVehiculo, Integer.parseInt(anioVehiculo), matriculaVehiculo, numeroMotorVehiculo, colorVehiculo, duenioVehiculo);
            vehiculos[slotIndex] = nuevoCoche;
        }

        Vehiculo nuevoVehiculo = new Vehiculo(marcaVehiculo, modeloVehiculo, Integer.parseInt(anioVehiculo), matriculaVehiculo, numeroMotorVehiculo, colorVehiculo, duenioVehiculo);
        vehiculos[slotIndex] = nuevoVehiculo;

        showWarningAlert("Éxito", "El vehículo ha sido agregado exitosamente.");

        if (arrayVehiculosController != null) {
            arrayVehiculosController.mostrarLista();
        }

        stage.close();
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

    private static boolean isValidMarca(String marca) {
        return marca != null && !marca.trim().isEmpty();
    }

    private static boolean isValidModelo(String modelo) {
        return modelo != null && !modelo.trim().isEmpty();
    }

    private static boolean isValidAnio(String anio) {
        try {
            int year = Integer.parseInt(anio);
            return year > 1885 && year <= java.time.Year.now().getValue(); // Valid year range
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidMatricula(String matricula) {
        return matricula != null && !matricula.trim().isEmpty();
    }

    private boolean isValidSlot(String slot) {
        try {
            int slotIndex = Integer.parseInt(slot) - 1;
            if (slotIndex < 0 || slotIndex >= vehiculos.length) {
                return false;
            }
            if (vehiculos[slotIndex] != null) {
                showWarningAlert("Slot ocupado", "El slot de estacionamiento ya está ocupado.");
                return false;
            }
            for (Vehiculo vehiculo : vehiculos) {
                if (vehiculo == null) {
                    return true;
                }
            }
            showWarningAlert("Estacionamiento lleno", "El estacionamiento está lleno.");
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void onRegresar(ActionEvent actionEvent) {
        stage.close();
    }

    public void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            slot.setText(String.valueOf(slotIndexToController + 1));
            slot.setDisable(true);
            marca.setText(vehiculo.getMarca());
            modelo.setText(vehiculo.getModelo());
            anio.setText(String.valueOf(vehiculo.getAnio()));
            matricula.setText(vehiculo.getMatricula());
            numeroMotor.setText(vehiculo.getNumeroMotor());
            color.setText(vehiculo.getColor());
            duenio.setText(vehiculo.getDuenio());

        }
    }
}
