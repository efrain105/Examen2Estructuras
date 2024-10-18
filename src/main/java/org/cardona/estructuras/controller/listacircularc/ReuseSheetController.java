package org.cardona.estructuras.controller.listacircularc;

import javafx.scene.control.*;
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.ListaCircularDoble;
import javafx.fxml.FXML;

import java.util.Optional;

public class ReuseSheetController {

    private static ListaCircularDoble lista = null;
    private static Libro libro = null;

    @FXML
    private TextArea outputArea;

    // Crear lista
    @FXML
    private void crearLista() {
        if (lista != null) {
            outputArea.setText("La lista ya ha sido creada.");
            return;
        }
        lista = new ListaCircularDoble();
        System.out.println("Lista creada.");

        // Agregamos 5 libros automáticamente
        lista.insertarFinal(new Libro("Cien años de soledad", "Gabriel García Márquez", "Sudamericana"));
        lista.insertarFinal(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles"));
        lista.insertarFinal(new Libro("La Odisea", "Homero", "Desconocida"));
        lista.insertarFinal(new Libro("1984", "George Orwell", "Secker & Warburg"));
        lista.insertarFinal(new Libro("Matar a un ruiseñor", "Harper Lee", "J.B. Lippincott & Co."));

        mostrarLista();

    }

    // Agregar libro al inicio
    @FXML
    private void agregarLibroInicio() {
        Libro libro = obtenerDatosLibro();
        lista.insertarInicio(libro);
        mostrarLista();
    }

    // Agregar libro al final
    @FXML
    private void agregarLibroFinal() {
        Libro libro = obtenerDatosLibro();
        lista.insertarFinal(libro);
        mostrarLista();
    }

    // Agregar libro en posición específica
    @FXML
    private void agregarLibroPosicion() {
        Libro libro = obtenerDatosLibro();
        int posicion = obtenerPosicion();
        lista.insertarEnPosicion(posicion - 1, libro);
        mostrarLista();
    }

    // Eliminar libro al inicio
    @FXML
    private void eliminarLibroInicio() {
        lista.quitarInicio();
        mostrarLista();
    }

    // Eliminar libro al final
    @FXML
    private void eliminarLibroFinal() {
        lista.quitarFinal();
        mostrarLista();
    }

    // Eliminar libro en posición específica
    @FXML
    private void eliminarLibroPosicion() {
        int posicion = obtenerPosicion();
        lista.quitarEnPosicion(posicion - 1);
        mostrarLista();
    }

    // Modificar libro en posición específica
    @FXML
    private void modificarLibroPosicion() {
        int posicion = obtenerPosicion();
        Libro libro = obtenerDatosLibro();
        lista.modificarEnPosicion(posicion - 1, libro);
        mostrarLista();
    }

    // Mostrar lista de libros
    @FXML
    private void mostrarLista() {
        if (lista == null) {
            outputArea.setText("La lista no ha sido creada.");
            return;
        }
        if (lista.isEmpty()) {
            outputArea.setText("La lista está vacía.");
            return;
        }
        outputArea.setText(lista.mostrarLista());
    }

    // Vaciar lista
    @FXML
    private void vaciarLista() {
        lista.vaciarLista();
        mostrarLista();
    }

    // Destruir lista
    @FXML
    private void destruirLista() {
        lista = null;
        mostrarLista();
    }

    // Métodos auxiliares
    private Libro obtenerDatosLibro() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo Libro");
        dialog.setHeaderText(null);

        dialog.setContentText("Título:");
        Optional<String> titulo = dialog.showAndWait();

        dialog.setContentText("Autor:");
        Optional<String> autor = dialog.showAndWait();

        dialog.setContentText("Editorial:");
        Optional<String> editorial = dialog.showAndWait();

        return new Libro(titulo.orElse("Sin título"), autor.orElse("Sin autor"), editorial.orElse("Sin editorial"));
    }

    private int obtenerPosicion() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Posición");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese la posición:");
        Optional<String> posicion = dialog.showAndWait();
        posicion.ifPresent(p -> {
            if (!p.matches("\\d+")) {
                mostrarMensaje("La posición debe ser un número entero.");
            }
        });
        return Integer.parseInt(posicion.orElseThrow(() -> new IllegalStateException("Posición no válida")));
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


