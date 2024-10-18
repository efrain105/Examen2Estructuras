package org.cardona.estructuras.modelo;//Cardona De luna Efrain Guadalupe
import java.util.Objects;

public class Libro {

    private String titulo;
    private String autor;
    private String editorial;

    public Libro(){

    }

    public Libro(String titulo, String autor, String editorial){
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libros = (Libro) o;
        return Objects.equals(titulo, libros.titulo) && Objects.equals(autor, libros.autor) && Objects.equals(editorial, libros.editorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, editorial);
    }
}
