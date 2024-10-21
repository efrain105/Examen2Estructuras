package org.cardona.estructuras.controller.arrayC;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;
import org.cardona.estructuras.modelo.array.vehiculos.coches.Coche;
import org.cardona.estructuras.modelo.array.vehiculos.motos.Moto;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarCocheController implements Initializable {
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
    public TextField tipoCarroceria;
    public TextField numeroPuertas;
    public Button guardar;
    public Button regresar;
    private Stage stage;

    private Vehiculo[] vehiculos;

    private ArrayController arrayVehiculosController;


    private int slotIndexToController = 50;

    public void setSlotIndex(int slotIndex) {
        this.slotIndexToController = slotIndex;
    }


    public void setListaVehiculos(Vehiculo[] listaVehiculos) {
        this.vehiculos = listaVehiculos;
    }

    public void setArrayVehiculosController(ArrayController arrayVehiculosController) {
        this.arrayVehiculosController = arrayVehiculosController;
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
        String tipoCarroceriaCoche = tipoCarroceria.getText();
        String numeroPuertasCoche = numeroPuertas.getText();

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
        if (!isValidTipoCarroceria(tipoCarroceriaCoche)) {
            showWarningAlert("Datos inválidos", "El tipo de carrocería no es válido.");
            return;
        }
        if (!isValidNumeroPuertas(numeroPuertasCoche)) {
            showWarningAlert("Datos inválidos", "El número de puertas no es válido.");
            return;
        }

        int slotIndex = Integer.parseInt(slotEstacionamiento) - 1;
        if (slotIndex == (slotIndexToController)) {

            Vehiculo  nuevoCoche = new Coche(marcaVehiculo, modeloVehiculo, Integer.parseInt(anioVehiculo), matriculaVehiculo, numeroMotorVehiculo, colorVehiculo, duenioVehiculo, tipoCarroceriaCoche, Integer.parseInt(numeroPuertasCoche));
            vehiculos[slotIndex] = nuevoCoche;
            slotIndexToController = 50;
        } else {
            if (!isValidSlot(slotEstacionamiento)) {
                showWarningAlert("Datos inválidos", "El slot de estacionamiento no es válido.");
                return;
            }
            Vehiculo  nuevoCoche = new Coche(marcaVehiculo, modeloVehiculo, Integer.parseInt(anioVehiculo), matriculaVehiculo, numeroMotorVehiculo, colorVehiculo, duenioVehiculo, tipoCarroceriaCoche, Integer.parseInt(numeroPuertasCoche));
            vehiculos[slotIndex] = nuevoCoche;
        }

        showWarningAlert("Éxito", "El coche ha sido agregado exitosamente.");
        if (arrayVehiculosController != null) {
            arrayVehiculosController.mostrarLista();
        }
        slotIndexToController = 50;
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
            return year > 1885 && year <= java.time.Year.now().getValue();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidMatricula(String matricula) {
        return matricula != null && !matricula.trim().isEmpty();
    }

    private static boolean isValidTipoCarroceria(String tipoCarroceria) {
        return tipoCarroceria != null && !tipoCarroceria.trim().isEmpty();
    }

    private static boolean isValidNumeroPuertas(String numeroPuertas) {
        try {
            int puertas = Integer.parseInt(numeroPuertas);
            return puertas > 0;
        } catch (NumberFormatException e) {
            return false;
        }
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
            for (Vehiculo moto : vehiculos) {
                if (moto == null) {
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

    public void setVehiculo(Coche vehiculo) {
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
            tipoCarroceria.setText(vehiculo.getTipoCarroceria());
            numeroPuertas.setText(String.valueOf(vehiculo.getNumeroPuertas()));
        }
    }
}