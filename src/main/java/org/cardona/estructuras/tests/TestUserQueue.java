package org.cardona.estructuras.tests;
import org.cardona.estructuras.modelo.URLSS;
import org.cardona.estructuras.modelo.cola.MiQueue;

import java.util.Scanner;

public class TestUserQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MiQueue cola = null;

        boolean continuar = true;

        while (continuar) {
            saltoDeLineas();
            menu();
            switch (sc.nextInt()) {
                case 0:
                    System.out.println("Saliendo del programa");
                    continuar = false;
                    break;
                case 1:
                    saltoDeLineas();
                    sc.nextLine();
                    if (cola != null) {
                        System.out.println("Primero destruya la cola actual");
                        break;
                    }
                    cola = new MiQueue();
                    System.out.println("Cola creada");
                    imprimirEstado(cola);
                    break;
                case 2:
                    saltoDeLineas();
                    if (cola == null) {
                        System.out.println("Primero cree una cola");
                        break;
                    }
                    System.out.println("Ingrese la URL a encolar: ");
                    cola.enqueue(new URLSS(sc.next()));
                    imprimirEstado(cola);
                    sc.nextLine();
                    break;
                case 3:
                    saltoDeLineas();
                    sc.nextLine();
                    if (cola == null) {
                        System.out.println("Primero cree una cola");
                        break;
                    }
                    System.out.println("Desencolando");
                    cola.dequeue();
                    imprimirEstado(cola);
                    break;
                case 4:
                    saltoDeLineas();
                    sc.nextLine();
                    if (cola == null) {
                        System.out.println("Primero cree una cola");
                        break;
                    }
                    System.out.println(cola.front() != null ? "La URL al frente de la cola es: " + cola.front().getUrl() : "La cola está vacía");
                    break;
                case 5:
                    saltoDeLineas();
                    sc.nextLine();
                    if (cola == null) {
                        System.out.println("Primero cree una cola");
                        break;
                    }
                    imprimirEstado(cola);
                    break;
                case 6:
                    saltoDeLineas();
                    sc.nextLine();
                    if (cola == null) {
                        System.out.println("Primero cree una cola");
                        break;
                    }
                    System.out.println("Vaciando cola");
                    cola.clear();
                    break;
                case 7:
                    saltoDeLineas();
                    sc.nextLine();
                    if (cola == null) {
                        System.out.println("Primero cree una cola");
                        break;
                    }
                    System.out.println("Destruyendo cola");
                    cola = null;
                    break;
            }

            saltoDeLineas();
            System.out.println("Presione enter para continuar");
            sc.nextLine();
        }
    }

    private static void saltoDeLineas() {
        System.out.println("-".repeat(50));
    }

    public static void imprimirEstado(MiQueue cola) {
        if (cola.isEmpty()) {
            System.out.println("La cola está vacía");
        } else {
            System.out.println(cola.front() != null ? "La URL al frente de la cola es: " + cola.front().getUrl() : "La cola no tiene url");
            System.out.println("La cola es: " + cola.size());
        }
        System.out.println("El tamaño de la cola es: " + cola.size());
    }

    private static void menu() {
        System.out.println("0. Salir");
        System.out.println("1. Crear Cola");
        System.out.println("2. Encolar");
        System.out.println("3. Desencolar");
        System.out.println("4. Mostrar frente");
        System.out.println("5. Mostrar cola");
        System.out.println("6. Vaciar cola");
        System.out.println("7. Destruir cola");
        System.out.println();
        System.out.print("Ingrese una opción: ");
    }

}
