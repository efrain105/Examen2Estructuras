package org.cardona.estructuras.tests;

import org.cardona.estructuras.modelo.URLSS;
import org.cardona.estructuras.modelo.pila.Mipila;
import org.cardona.estructuras.modelo.pila.Pila;

public class TestPila {
    public static void main(String[] args) {


        URLSS google = new URLSS("http://www.google.com");
        URLSS face = new URLSS("facebook.com");
        URLSS insta = new URLSS("instagram.com");

        Pila pila = new Mipila();
        imprimirEstado(pila);
        pila.push(google);
        imprimirEstado(pila);
        pila.push(face);
        while (!pila.isEmpty()) {
            System.out.println("Sacando de la pila: " + pila.top().getUrl());
            pila.pop();
            imprimirEstado(pila);
        }
    }

    public static void imprimirEstado(Pila pila) {
        if (pila.isEmpty()){
            System.out.println("La pila esta vacia");
        } else {
            System.out.println("La Pilas.URL en la cima de la pila es: " + pila.top().getUrl());

        }
        System.out.println("El tamaño de la pila es: " + pila.size());
    }

}
