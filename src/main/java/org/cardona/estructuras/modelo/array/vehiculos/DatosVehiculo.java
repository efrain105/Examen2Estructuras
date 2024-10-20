package org.cardona.estructuras.modelo.array.vehiculos;

public record DatosVehiculo(

        String marca,
        String modelo,
        Integer anio,
        String matricula,
        String numeroMotor,
        String color,
        String duenio


) {

    public DatosVehiculo(Vehiculo vehiculo) {
        this(vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getAnio(), vehiculo.getMatricula(), vehiculo.getNumeroMotor(), vehiculo.getColor(), vehiculo.getDuenio());
    }


}
