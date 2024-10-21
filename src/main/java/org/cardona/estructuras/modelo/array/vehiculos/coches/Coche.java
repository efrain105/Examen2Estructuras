package org.cardona.estructuras.modelo.array.vehiculos.coches;

import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;

public class Coche extends Vehiculo {
    private String tipoCarroceria;  // Sedán, Hatchback, SUV, etc.
    private Integer numeroPuertas;


    public Coche(String marca, String modelo, int anio, String matricula, String numeroMotor, String color, String duenio, String tipoCarroceria, Integer numeroPuertas) {
        super(marca, modelo, anio, matricula, numeroMotor, color, duenio);
        this.tipoCarroceria = tipoCarroceria;
        this.numeroPuertas = numeroPuertas;
    }

    public Coche(String marca, String modelo, int anio, String matricula, String numeroMotor, String color, String tipoCarroceria, Integer numeroPuertas) {
        super(marca, modelo, anio, matricula, numeroMotor, color);
        this.tipoCarroceria = tipoCarroceria;
        this.numeroPuertas = numeroPuertas;
    }

    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(String tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public void vaciarDatos() {
        super.vaciarDatos(); // Llama al método de la clase padre para vaciar los datos comunes
        this.tipoCarroceria = null;
        this.numeroPuertas = null;
    }


    @Override
    public String toString() {
        return super.toString() + String.format("Tipo de Carrocería: %s | Número de Puertas: %d", tipoCarroceria, numeroPuertas).trim();
    }

}
