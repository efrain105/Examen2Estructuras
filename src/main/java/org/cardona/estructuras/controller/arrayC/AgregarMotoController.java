package org.cardona.estructuras.controller.arrayC;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;
import org.cardona.estructuras.modelo.array.vehiculos.motos.Moto;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarMotoController implements Initializable {
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
    public TextField cilindrada;
    public CheckBox tieneMaletero;
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
        String cilindradaMoto = cilindrada.getText();
        boolean tieneMaleteroMoto = tieneMaletero.isSelected();

        if (!isValidMarca(marcaVehiculo)) {
            showWarningAlert("Datos inválidos", "La marca del vehículo no es válida.");
            return;
        }
        if (!isValidCilindrada(cilindradaMoto)) {
            showWarningAlert("Datos inválidos", "La cilindrada de la moto no es válida.");
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

        if (slotIndex == (slotIndexToController)) {
            Vehiculo nuevaMoto = new Moto(marcaVehiculo, modeloVehiculo, Integer.parseInt(anioVehiculo), matriculaVehiculo, numeroMotorVehiculo, colorVehiculo, duenioVehiculo, Integer.parseInt(cilindradaMoto), tieneMaleteroMoto);
            vehiculos[slotIndex] = nuevaMoto;
        } else {
            if (!isValidSlot(slotEstacionamiento)) {
                showWarningAlert("Datos inválidos", "El slot de estacionamiento no es válido.");
                return;
            }
            Vehiculo nuevaMoto = new Moto(marcaVehiculo, modeloVehiculo, Integer.parseInt(anioVehiculo), matriculaVehiculo, numeroMotorVehiculo, colorVehiculo, duenioVehiculo, Integer.parseInt(cilindradaMoto), tieneMaleteroMoto);
            vehiculos[slotIndex] = nuevaMoto;
        }
        slotIndexToController = 50;

        showWarningAlert("Éxito", "La moto ha sido agregada exitosamente.");
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

    private boolean isValidCilindrada(String cilindrada) {
        try {
            int value = Integer.parseInt(cilindrada);
            return value > 0; // Cilindrada should be a positive integer
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

    public void setVehiculo(Moto vehiculo) {
        if (vehiculo != null) {
            slot.setText(String.valueOf(slotIndexToController + 1));
            slot.setDisable(true); // Deshabilitar el campo de slot
            marca.setText(vehiculo.getMarca());
            modelo.setText(vehiculo.getModelo());
            anio.setText(String.valueOf(vehiculo.getAnio()));
            matricula.setText(vehiculo.getMatricula());
            numeroMotor.setText(vehiculo.getNumeroMotor());
            color.setText(vehiculo.getColor());
            duenio.setText(vehiculo.getDuenio());
            cilindrada.setText(String.valueOf(vehiculo.getCilindrada()));
            tieneMaletero.setSelected(vehiculo.getTieneMaletero());
        }
    }


}