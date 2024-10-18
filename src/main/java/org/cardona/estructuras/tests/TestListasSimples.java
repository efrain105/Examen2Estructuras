package org.cardona.estructuras.tests;

import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listassimples.ListaSimple;

import java.util.Scanner;

//Cardona De luna Efrain Guadalupe
public class TestListasSimples {

    private static ListaSimple lista = null;

    public static void main(String[] args) {
        Libro libro;
        //Nodo nodo;
        Scanner sc = new Scanner(System.in);
        System.out.println("Cardona De luna Efrain Guadalupe");

        boolean continuar = true;
        while (continuar) {
            menu();
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    bufferFormato(sc);
                    lista = crearLista(lista);
                    break;
                case 2:
                    bufferFormato(sc);
                    agregarLibroFinal(lista, sc);
                    break;
                case 3:
                    bufferFormato(sc);
                    agregarLibroEnPos(lista, sc);
                    break;
                case 4:
                    bufferFormato(sc);
                    eliminarFinal(lista);
                    break;
                case 5:
                    bufferFormato(sc);
                    eliminarPos(lista, sc);
                    break;
                case 6:
                    bufferFormato(sc);
                    mostrarLista(lista);
                    break;
                case 7:
                    bufferFormato(sc);
                    vaciarLista(lista);
                    break;
                case 8:
                    bufferFormato(sc);
                    lista = destruirLista(lista);
                    break;
                case 9:
                    continuar = false;
                    break;
            }
            System.out.println("-".repeat(60));
            System.out.println("Presione Enter para continuar");
            sc.nextLine();

        }
        System.out.println("Cardona De luna Efrain Guadalupe");
    }

    private static ListaSimple destruirLista(ListaSimple lista) {
        if (lista == null) {
            System.out.println("Para destruir la lista primero debe crearla");
        }
        System.out.println("Lista destruida");
        return lista = null;
    }

    private static void vaciarLista(ListaSimple lista) {
        if (lista == null) {
            System.out.println("Para vaciar la lista primero debe crearla");
            return;
        }
        try {
            if (lista.size() == 0) {
                System.out.println("La lista ya esta vacia");
                return;
            }
            lista.vaciar();
            System.out.println("Lista vaciada correctamente");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
        }
    }

    private static void mostrarLista(ListaSimple lista) {
        if (lista != null) {
            System.out.println("La cantidad de libro en la lista es: " + lista.size());
            System.out.println(lista.size() != 0 ? "Los libros en la lista son: " : "La lista esta vacia");
            System.out.println(lista.mostrar());
        } else {
            System.out.println("La lista no ha sido creada");
        }
    }

    private static void eliminarPos(ListaSimple lista, Scanner sc) {
        if (lista == null) {
            System.out.println("Primero debe crear la lista");
            return;
        }
        try {
            System.out.println(lista.size() != 0 ? "Los libros de esta lista son: " : "La lista esta vacia");
            System.out.println(lista.mostrar());
            System.out.println("Ingrese la posicion del libro que desea eliminar: ");
            int index = sc.nextInt();
            validarIndice(lista.size(), index);
            lista.quitarEnPosicion(index - 1);
            System.out.println("Libro eliminado correctamente");
            return;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            sc.nextLine(); // Limpiar el buffer del scanner
        }
    }

    private static void eliminarFinal(ListaSimple lista) {
        if (lista == null) {
            System.out.println("Primero debe crear la lista");
            return;
        }
        try {
            lista.quitarLibro();
            System.out.println("Libro eliminado del final de la lista");
        } catch (Exception e) {
            System.out.println(e.getMessage() + ". Primero agregue un libro.");
        }
    }

    private static void agregarLibroEnPos(ListaSimple lista, Scanner sc) {
        Libro libro;
        if (lista == null) {
            System.out.println("Primero debe crear la lista");
            return;
        }
        try {
            if (lista.size() == 0) {
                libro = new Libro(validarTitulo(sc), validarAutor(sc), validarEditorial(sc));
                lista.agregar(libro);
                System.out.println("Libro agregado correctamente");
                return;
            }
            libro = new Libro(validarTitulo(sc), validarAutor(sc), validarEditorial(sc));
            System.out.println("Los libros en la lista son: ");
            System.out.println(lista.mostrar());
            System.out.print("Ingrese la posicion donde desea agregar el libro: ");
            int index = sc.nextInt();
            validarIndice(lista.size(), index);
            lista.agregarEnPosicion(index - 1, libro);
            System.out.println("Libro agregado correctamente");
            return;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            sc.nextLine(); // Limpiar el buffer del scanner
        }
    }

    private static void agregarLibroFinal(ListaSimple lista, Scanner sc) {
        Libro libro;
        if (lista == null) {
            System.out.println("Primero debe crear la lista");
            return;
        }
        try {
            libro = new Libro(validarTitulo(sc), validarAutor(sc), validarEditorial(sc));
            lista.agregar(libro);
            System.out.println("Libro agregado correctamente");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            sc.nextLine(); // Limpiar el buffer del scanner
        }
    }

    private static ListaSimple crearLista(ListaSimple lista) {
        if (lista != null) {
            System.out.println("La lista ya fue creada");
        }
        lista = new ListaSimple();
        System.out.println("Lista creada");
        return lista;
    }

    private static void bufferFormato(Scanner sc) {
        System.out.println("-".repeat(60));
        sc.nextLine();
    }

    private static void validarIndice(int tamaoLista, int index) {

        while (index < 1 || index > tamaoLista) {
            System.out.println("El indice no puede ser menor a 1 o mayor al tamaño de la lista");
            System.out.print("Ingrese nuevamente el indice: ");
            index = new Scanner(System.in).nextInt();
        }
    }

    private static String validarTitulo(Scanner sc) {
        String titulo;
        while (true) {
            try {
                System.out.print("Ingrese el titulo del libro: ");
                titulo = sc.nextLine();
                if (titulo.isEmpty()) {
                    System.out.println("El titulo no puede estar vacio");
                    continue;
                }
                return titulo;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            }
        }
    }

    public static String validarAutor(Scanner sc) {
        while (true) {
            try {
                System.out.print("Ingrese el autor del libro: ");
                String autor = sc.nextLine();
                if (autor.isEmpty()) {
                    System.out.println("El autor no puede estar vacío. Intente nuevamente.");
                    continue;
                }
                return autor;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            }
        }
    }

    public static String validarEditorial(Scanner sc) {
        while (true) {
            try {
                System.out.print("Ingrese la editorial del libro: ");
                String editorial = sc.nextLine();
                if (editorial.isEmpty()) {
                    System.out.println("La editorial no puede estar vacía. Intente nuevamente.");
                    continue;
                }
                return editorial;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
            }
        }
    }

    private static void menu() {
        System.out.println("1. Crear lista de libros");
        System.out.println("2. Insertar elemento al final de la lista");
        System.out.println("3. Agregar libro en una posicion especifica");
        System.out.println("4. Eliminar elemento al final de la lista");
        System.out.println("5. Eliminar elemento de una posicion especifica");
        System.out.println("6. Mostar lista de libros");
        System.out.println("7. Vaciar lista de libros");
        System.out.println("8. Destruir lista de libros");
        System.out.println("9. Salir");
        System.out.print("Ingrese una opcion: ");
    }

}