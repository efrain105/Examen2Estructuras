package org.cardona.estructuras.tests;//Cardona De luna Efrain Guadalupe
import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listasdobles.ListaDobleLibros;

import java.util.Scanner;

public class TestListaDobleLibros {

    public static ListaDobleLibros lista = null;
    public static Libro libro = null;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        System.out.println("Cardona De luna Efrain Guadalupe");

        while (continuar) {
            menu();
            int opcion = sc.nextInt();
            //sc.nextLine(); // Consumir buffer
            switch (opcion) {
                case 1:
                    bufferFormato(sc);
                    lista = crearLista(lista);
                    break;
                case 2:
                    bufferFormato(sc);
                    mostrarLista(lista);
                    break;
                case 3:
                    bufferFormato(sc);
                    agregarLibroInicio(lista, sc);
                    break;
                case 4:
                    bufferFormato(sc);
                    agregarLibroFinal(lista, sc);
                    break;
                case 5:
                    bufferFormato(sc);
                    agregarLibroPosicion(lista, sc);
                    sc.nextLine(); // Consumir buffer
                    break;
                case 6:
                    bufferFormato(sc);
                    eliminarLibroInicio(lista);
                    break;
                case 7:
                    bufferFormato(sc);
                    eliminarLibroFinal(lista);
                    break;
                case 8:
                    bufferFormato(sc);
                    eliminarLibroPosicion(lista, sc);
                    sc.nextLine(); // Consumir buffer
                    break;
                case 9:
                    bufferFormato(sc);
                    modificarLibroPosicion(lista, sc);
                    break;
                case 10:
                    bufferFormato(sc);
                    cantidadLibro(lista);
                    break;
                case 11:
                    bufferFormato(sc);
                    vaciarLista(lista);
                    break;
                case 12:
                    bufferFormato(sc);
                    lista = destruirLista(lista);
                    break;
                case 13:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            if (continuar) {
                System.out.println("-".repeat(120));
                System.out.println("Presione Enter para continuar...");
                sc.nextLine();
            }

        }
        System.out.println("Cardona De luna Efrain Guadalupe");

    }

    // Métodos estáticos
    private static ListaDobleLibros crearLista(ListaDobleLibros lista) {
        if (lista != null) {
            System.out.println("La lista ya fue creada.");
            return lista;
        }
        lista = new ListaDobleLibros();
        System.out.println("Lista creada.");

        // Agregamos 5 libro automáticamente
        lista.insertarFinal(new Libro("Cien años de soledad", "Gabriel García Márquez", "Sudamericana"));
        lista.insertarFinal(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles"));
        lista.insertarFinal(new Libro("La Odisea", "Homero", "Desconocida"));
        lista.insertarFinal(new Libro("1984", "George Orwell", "Secker & Warburg"));
        lista.insertarFinal(new Libro("Matar a un ruiseñor", "Harper Lee", "J.B. Lippincott & Co."));

        System.out.println("Se han agregado 5 libros a la lista automáticamente.");
        mostrarLista(lista);

        return lista;
    }

    private static void mostrarLista(ListaDobleLibros lista) {
        if (lista == null) {
            System.out.println("La lista no ha sido creada.");
            return;
        }
        if (lista.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println("Libros en la lista:");
        System.out.println(lista.mostrarLista());
    }

    private static void agregarLibroInicio(ListaDobleLibros lista, Scanner sc) {
        if (lista == null) {
            System.out.println("Primero debe crear la lista.");
            return;
        }
        System.out.println("Ingrese los datos del libro:");
        libro = new Libro(validarTitulo(sc), validarAutor(sc), validarEditorial(sc));
        lista.insertarInicio(libro);
        System.out.println("Libro agregado al inicio.");
        mostrarLista(lista);
    }

    private static void agregarLibroFinal(ListaDobleLibros lista, Scanner sc) {
        if (lista == null) {
            System.out.println("Primero debe crear la lista.");
            return;
        }
        System.out.println("Ingrese los datos del libro:");
        libro = new Libro(validarTitulo(sc), validarAutor(sc), validarEditorial(sc));
        lista.insertarFinal(libro);
        System.out.println("Libro agregado al final.");
        mostrarLista(lista);
    }

    private static void agregarLibroPosicion(ListaDobleLibros lista, Scanner sc) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Primero debe crear la lista o asegurarse de que no esté vacía.");
            return;
        }

        try {
            // Mostrar lista actual
            System.out.println("Los libros en la lista son: ");
            System.out.println(lista.mostrarLista());

            // Solicitar datos del nuevo libro
            libro = new Libro(validarTitulo(sc), validarAutor(sc), validarEditorial(sc));

            // Solicitar posición
            System.out.print("Ingrese la posición donde desea agregar el libro: ");
            int index = validarIndice(lista.size(), sc.nextInt());
            // Insertar en la posición
            lista.insertarEnPosicion(index - 1, libro);
            System.out.println("Libro agregado correctamente.");

            // Mostrar lista actualizada
            System.out.println(lista.mostrarLista());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            sc.nextLine(); // Limpiar el buffer del scanner
        }
    }

    private static void eliminarLibroInicio(ListaDobleLibros lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Primero debe crear la lista o la lista está vacía.");
            return;
        }
        lista.quitarInicio();
        System.out.println("Libro eliminado del inicio.");
        mostrarLista(lista);
    }

    private static void eliminarLibroFinal(ListaDobleLibros lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Primero debe crear la lista o la lista está vacía.");
            return;
        }
        lista.quitarFinal();
        System.out.println("Libro eliminado del final.");
        mostrarLista(lista);
    }

    private static void eliminarLibroPosicion(ListaDobleLibros lista, Scanner sc) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Primero debe crear la lista o asegurarse de que no esté vacía.");
            return;
        }

        try {
            // Mostrar lista actual
            System.out.println("Los libros en la lista son: ");
            System.out.println(lista.mostrarLista());

            // Solicitar posición
            System.out.print("Ingrese la posición del libro que desea eliminar: ");
            int index = validarIndice(lista.size(), sc.nextInt());
            // Eliminar libro en la posición
            Libro eliminado = lista.quitarEnPosicion(index - 1);
            System.out.println("Libro eliminado correctamente: " + eliminado);

            // Mostrar lista actualizada
            System.out.println(lista.mostrarLista());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            sc.nextLine(); // Limpiar el buffer del scanner
        }
    }

    private static int validarIndice(int tamanoLista, int index) {
        while (index < 1 || index > tamanoLista) {
            System.out.println("El índice no puede ser menor a 1 o mayor al tamaño de la lista.");
            System.out.print("Ingrese nuevamente el índice: ");
            index = new Scanner(System.in).nextInt();
        }
        return index;
    }

    private static void modificarLibroPosicion(ListaDobleLibros lista, Scanner sc) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Primero debe crear la lista o la lista está vacía.");
            return;
        }
        mostrarLista(lista);
        System.out.print("Ingrese la posición del libro a modificar: ");
        validarIndice(lista.size(), sc.nextInt());
        sc.nextLine(); // Consumir buffer
        System.out.println("Ingrese los nuevos datos del libro:");
        libro = new Libro(validarTitulo(sc), validarAutor(sc), validarEditorial(sc));
        System.out.print("Ingrese la posición donde desea modificar el libro: ");
        int posicion = validarIndice(lista.size(), sc.nextInt());
        lista.modificarEnPosicion(posicion - 1, libro);
        System.out.println("Libro modificado en la posición " + posicion);
        mostrarLista(lista);
    }

    private static void cantidadLibro(ListaDobleLibros lista) {
        if (lista == null) {
            System.out.println("Primero debe crear la lista.");
            return;
        }
        System.out.println("Cantidad de libros en la lista: " + lista.size());
    }

    private static void vaciarLista(ListaDobleLibros lista) {
        if (lista == null) {
            System.out.println("Primero debe crear la lista.");
            return;
        }
        lista.vaciarLista();
        System.out.println("Lista vaciada.");
        mostrarLista(lista);
    }

    private static ListaDobleLibros destruirLista(ListaDobleLibros lista) {
        if (lista == null) {
            System.out.println("Primero debe crear la lista.");
            return lista;
        }
        lista.vaciarLista();
        System.out.println("Lista destruida.");
        return null;
    }

    // Métodos auxiliares para validar datos
    private static String validarTitulo(Scanner sc) {
        String titulo;
        do {
            System.out.print("Ingrese el título del libro: ");
            titulo = sc.nextLine();
            if (titulo.isEmpty()) {
                System.out.println("El título no puede estar vacío.");
            }
        } while (titulo.isEmpty());
        return titulo;
    }

    private static String validarAutor(Scanner sc) {
        String autor;
        do {
            System.out.print("Ingrese el autor del libro: ");
            autor = sc.nextLine();
            if (autor.isEmpty()) {
                System.out.println("El autor no puede estar vacío.");
            }
        } while (autor.isEmpty());
        return autor;
    }

    private static String validarEditorial(Scanner sc) {
        String editorial;
        do {
            System.out.print("Ingrese la editorial del libro: ");
            editorial = sc.nextLine();
            if (editorial.isEmpty()) {
                System.out.println("La editorial no puede estar vacía.");
            }
        } while (editorial.isEmpty());
        return editorial;
    }

    private static void menu() {
        System.out.println("1. Crear lista de libros");
        System.out.println("2. Mostrar lista de libros");
        System.out.println("3. Agregar libro al inicio");
        System.out.println("4. Agregar libro al final");
        System.out.println("5. Agregar libro en posición específica");
        System.out.println("6. Eliminar libro al inicio");
        System.out.println("7. Eliminar libro al final");
        System.out.println("8. Eliminar libro en posición específica");
        System.out.println("9. Modificar libro en posición específica");
        System.out.println("10. Mostrar cantidad de libros");
        System.out.println("11. Vaciar lista");
        System.out.println("12. Destruir lista");
        System.out.println("13. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void bufferFormato(Scanner sc) {
        System.out.println("-".repeat(120));
        sc.nextLine(); // Consumir buffer

    }
}
