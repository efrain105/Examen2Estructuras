package org.cardona.estructuras.modelo.array.vehiculos.motos;

import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;

public class Moto extends Vehiculo {
    private Integer cilindrada;  // cc de la moto
    private Boolean tieneMaletero;

    //Basico

    public Moto(String marca, String modelo, int anio, String matricula) {
        super(marca,modelo,anio,matricula);
    }

    public Moto(String marca, String modelo, int anio, String matricula, String numeroMotor, String color, String duenio) {
        super(marca,modelo,anio,matricula,numeroMotor,color,duenio);
    }

    public Moto(String marca, String modelo, int anio, String matricula, String numeroMotor, String color, String duenio, Integer cilindrada, Boolean tieneMaletero) {
        super(marca, modelo, anio, matricula, numeroMotor, color, duenio);
        this.cilindrada = cilindrada;
        this.tieneMaletero = tieneMaletero;
    }
    //constructor con dueño
    public Moto(String marca, String modelo, int anio, String matricula, String numeroMotor, String color) {
        super(marca,modelo,anio,matricula,matricula,numeroMotor,color);
    }


    // Constructor con número de motor y color
    public Moto(String marca, String modelo, int anio, String matricula, String numeroMotor, String color, Integer cilindrada, Boolean tieneMaletero) {
        super(marca, modelo, anio, matricula, numeroMotor, color);
        this.cilindrada = cilindrada;
        this.tieneMaletero = tieneMaletero;
    }

    public int getCilindrada() {
        return cilindrada;
    }


    public Boolean getTieneMaletero() {
        return tieneMaletero;
    }

    public void setTieneMaletero(Boolean tieneMaletero) {
        this.tieneMaletero = tieneMaletero;
    }

    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public void vaciarDatos() {
        super.vaciarDatos(); // Llama al método de la clase padre para vaciar los datos comunes
        this.cilindrada = null;
        this.tieneMaletero = null;
    }


    @Override
    public String toString() {
        return super.toString() + String.format(" | Cilindrada: %d | Tiene Maletero: %b", cilindrada, tieneMaletero);
    }


}
