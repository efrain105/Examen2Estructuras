package org.cardona.estructuras.modelo.array.persona;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Persona {
    //atributos
    private String curp;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Integer edad;

    //Constructor


    public Persona() {
    }

    public Persona(String curp, String nombre, String apellido, LocalDate fechaNacimiento, Integer edad) {
        this.curp = curp;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
    }


    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void asignarDatos(String nombre, String apellido, LocalDate fechaNacimiento, String curp) {
        this.nombre = nombre;
        this.curp = curp;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad();
    }

    public int calcularEdad(){
        LocalDate hoy = LocalDate.now();
        return Period.between(fechaNacimiento, hoy).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(curp, persona.curp) && Objects.equals(nombre, persona.nombre) && Objects.equals(apellido, persona.apellido) && Objects.equals(fechaNacimiento, persona.fechaNacimiento) && Objects.equals(edad, persona.edad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curp, nombre, apellido, fechaNacimiento, edad);
    }

    @Override
    public String toString() {
        return "--------- Datos de la Persona ---------\n" +
                "CURP:              " + curp + "\n" +
                "Nombre:            " + nombre + "\n" +
                "Apellido:          " + apellido + "\n" +
                "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                "Edad:              " + edad + " años\n";
    }


    public String toStringPersonaCreadaConExito() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("---------Persona creada exitosamente---------\n");
        sb.append("Nombre: ").append(this.getNombre()).append("\n");
        sb.append("Apellido: ").append(this.getApellido()).append("\n");
        sb.append("Curp: ").append(this.getCurp()).append("\n");
        sb.append("Fecha de Nacimiento: ").append(this.getFechaNacimiento()).append("\n");
        sb.append("Edad: ").append(this.getEdad());
        return sb.toString();
    }

    public void vaciarDatos(Persona persona) {
        if (persona != null) {
            persona.setCurp(null);
            persona.setNombre(null);
            persona.setApellido(null);
            persona.setFechaNacimiento(null);
        }
    }
}
