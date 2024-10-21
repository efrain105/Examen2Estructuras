package org.cardona.estructuras.modelo.array.vehiculos.camiones;


import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;

public class Camion extends Vehiculo {
    private Integer tonelaje;  // capacidad de carga
    private Integer numeroEjes;

    public Camion(String marca, String modelo, int anio, String matricula, String numeroMotor, String color, Integer tonelaje, Integer numeroEjes) {
        super(marca, modelo, anio, matricula, numeroMotor, color);
        this.tonelaje = tonelaje;
        this.numeroEjes = numeroEjes;
    }

    public Camion(String marca, String modelo, int anio, String matricula, String numeroMotor, String color, String duenio, Integer tonelaje, Integer numeroEjes) {
        super(marca, modelo, anio, matricula, numeroMotor, color, duenio);
        this.tonelaje = tonelaje;
        this.numeroEjes = numeroEjes;
    }

    public int getTonelaje() {
        return tonelaje;
    }

    public void setTonelaje(int tonelaje) {
        this.tonelaje = tonelaje;
    }

    public int getNumeroEjes() {
        return numeroEjes;
    }

    public void setNumeroEjes(int numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    @Override
    public void vaciarDatos() {
        super.vaciarDatos(); // Llama al método de la clase padre para vaciar los datos comunes
        this.tonelaje = null;
        this.numeroEjes = null;
    }

    @Override
    public void actualizarDatosVehiculos(DatosCamion vehiculo) {
        super.actualizarDatosVehiculos(vehiculo);
        if (vehiculo.numeroEjes() != null) {
            this.numeroEjes = vehiculo.numeroEjes();
        }
        if (vehiculo.tonelaje() != null) {
            this.tonelaje = vehiculo.tonelaje();
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Tonelaje: %d | Número de Ejes: %d", tonelaje, numeroEjes).trim();
    }

}
