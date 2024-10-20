package org.cardona.estructuras.modelo.pila;

import org.cardona.estructuras.modelo.URLSS;

public class Mipila implements Pila {

    private Nodo cima = null;


    public  class Nodo {

        public URLSS URLSS;
        public Nodo siguiente = null;

        public Nodo(URLSS URLSS) {
            this.URLSS = URLSS;
        }

    }

    @Override
    public void push(URLSS URLSS) {
        Nodo nuevo = new Nodo(URLSS);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    @Override
    public void pop() {
        if (cima != null) {
            Nodo aux = cima;
            cima = cima.siguiente;
            aux.siguiente = null;
        }
    }

    @Override
    public URLSS top() {
        if (cima == null) {
            return null;
        }
        return cima.URLSS;
    }

    @Override
    public int size() {
        int contador = 0;
        Nodo aux = cima;
        while (aux != null) {
            contador++;
            aux = aux.siguiente;
        }
        return contador;
    }

    @Override
    public boolean isEmpty() {
        if (cima == null) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        cima = null;
    }
}
