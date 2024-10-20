//Cardona De luna Efrain Guadalupe
package org.cardona.estructuras.modelo.array.vehiculos;
import org.cardona.estructuras.interfaces.ActualizarDatosVehiculos;
import org.cardona.estructuras.modelo.array.vehiculos.camiones.DatosCamion;

import java.util.Objects;

public class Vehiculo implements ActualizarDatosVehiculos {
    private String marca;
    private String modelo;
    private Integer anio;
    private String matricula;
    private String numeroMotor;
    private String color;
    private String duenio;

    public Vehiculo() {

    }
    // Constructor básico
    public Vehiculo(String marca, String modelo, Integer anio, String matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.matricula = matricula;
    }

    // Constructor con número de motor y color
    public Vehiculo(String marca, String modelo, Integer anio, String matricula, String numeroMotor, String color) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.matricula = matricula;
        this.numeroMotor = numeroMotor;
        this.color = color;
    }


//constructor con dueño
    public Vehiculo(String marca, String modelo, Integer anio, String matricula, String numeroMotor, String color, String duenio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.matricula = matricula;
        this.numeroMotor = numeroMotor;
        this.color = color;
        this.duenio = duenio;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void actualizarDatosVehiculos(DatosCamion vehiculo) {
        validarDatosVehiculoBasico(vehiculo.marca(), vehiculo.modelo(), vehiculo.anio(), vehiculo.matricula(), vehiculo.numeroMotor(), vehiculo.color(), vehiculo.duenio());
    }

    @Override
    public void actualizarDatosVehiculos(DatosVehiculo vehiculo) {
        validarDatosVehiculoBasico(vehiculo.marca(), vehiculo.modelo(), vehiculo.anio(), vehiculo.matricula(), vehiculo.numeroMotor(), vehiculo.color(), vehiculo.duenio());
    }


    private void validarDatosVehiculoBasico(String marca, String modelo, Integer anio, String matricula, String s, String color, String duenio) {
        if (marca != null && !marca.isEmpty()) {
            this.marca = marca;
        }
        if (modelo != null && !modelo.isEmpty()) {
            this.modelo = modelo;
        }
        if (anio != null) {  // No se verifica si está vacío porque es Integer
            this.anio = anio;
        }
        if (matricula != null && !matricula.isEmpty()) {
            this.matricula = matricula;
        }
        if (s != null && !s.isEmpty()) {
            this.numeroMotor = s;
        }
        if (color != null && !color.isEmpty()) {
            this.color = color;
        }
        if (duenio != null && !duenio.isEmpty()) {
            this.duenio = duenio;
        }
    }


    // metodo toString para mostrar la información del vehículo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return anio == vehiculo.anio &&
                Objects.equals(marca, vehiculo.marca) &&
                Objects.equals(modelo, vehiculo.modelo) &&
                Objects.equals(matricula, vehiculo.matricula) &&
                Objects.equals(numeroMotor, vehiculo.numeroMotor) &&
                Objects.equals(color, vehiculo.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo, anio, matricula, numeroMotor, color);
    }

    // metodo para vaciar datos del vehículo (dejar atributos con valores nulos)
    public void vaciarDatos() {
        this.marca = null;
        this.modelo = null;
        this.anio = null;
        this.numeroMotor = null;
        this.color = null;
        this.duenio = null;
    }



}
