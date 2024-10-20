package org.cardona.estructuras.tests;


import org.cardona.estructuras.modelo.URLSS;
import org.cardona.estructuras.modelo.pila.Mipila;
import org.cardona.estructuras.modelo.pila.Pila;

import java.util.Scanner;

public class TestUserPila {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Pila pila = null;

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
                    sc.nextLine();
                    saltoDeLineas();
                    if (pila != null) {
                        System.out.println("Primero destruya la pila actual");
                        break;
                    }
                    pila = new Mipila();
                    System.out.println("Pila creada");
                    imprimirEstado(pila);
                    break;
                case 2:
                    sc.nextLine();
                    saltoDeLineas();
                    if (pila == null) {
                        System.out.println("Primero cree una pila");
                        break;
                    }
                    System.out.println("Ingrese la Pilas.URL a apilar: ");
                    pila.push(new URLSS(sc.next()));
                    imprimirEstado(pila);
                    break;
                case 3:
                    sc.nextLine();
                    saltoDeLineas();
                    if (pila == null) {
                        System.out.println("Primero cree una pila");
                        break;
                    }
                    System.out.println("Desapilando");
                    pila.pop();
                    imprimirEstado(pila);
                    break;
                case 4:
                    sc.nextLine();
                    saltoDeLineas();
                    if (pila == null) {
                        System.out.println("Primero cree una pila");
                        break;
                    }
                    System.out.println(pila.top() != null ?"La Pilas.URL en la cima de la pila es: " + pila.top().getUrl() : "La pila está vacía");
                    break;
                case 5:
                    saltoDeLineas();
                    if (pila == null) {
                        System.out.println("Primero cree una pila");
                        break;
                    }
                    sc.nextLine();
                    imprimirEstado(pila);
                    break;
                case 6:
                    sc.nextLine();
                    saltoDeLineas();
                    if (pila == null) {
                        System.out.println("Primero cree una pila");
                        break;
                    }
                    System.out.println("Vaciando pila");
                    pila.clear();
                    break;
                case 7:
                    sc.nextLine();
                    saltoDeLineas();
                    if (pila == null) {
                        System.out.println("Primero cree una pila");
                        break;
                    }
                    System.out.println("Destruyendo pila");
                    pila = null;
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
    public static void imprimirEstado(Pila pila) {

        if (pila.isEmpty()){
            System.out.println("La pila esta vacia");
        } else {
            System.out.println("La Pilas.URL en la cima de la pila es: " + pila.top().getUrl());

        }

        System.out.println("El tamaño de la pila es: " + pila.size());

    }

    private static void menu() {
        System.out.println("0. Salir");
        System.out.println("1. Crear Pila");
        System.out.println("2. Apilar");
        System.out.println("3. Desapilar");
        System.out.println("4. Mostrar cima");
        System.out.println("5. Mostrar pila");
        System.out.println("6. Vaciar pila");
        System.out.println("7. Destruir pila");
        System.out.println();
        System.out.print("Ingrese una opcion: ");
    }
}
