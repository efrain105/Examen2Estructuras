package org.cardona.estructuras.listascirculardoble.modelo;

public class Nodo {

    private Libro dato;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo(Libro dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public Libro getDato() {
        return dato;
    }

    public void setDato(Libro dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}
