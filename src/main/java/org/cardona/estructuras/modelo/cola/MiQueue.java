package org.cardona.estructuras.modelo.cola;

import org.cardona.estructuras.modelo.URLSS;

public class MiQueue implements Queue {

    public  class Nodo {

        public URLSS URLSS;
        public Nodo siguiente = null;

        public Nodo(URLSS URLSS) {
            this.URLSS = URLSS;
        }

    }

        private Nodo inicio = null;
        private Nodo fin = null;

        @Override
        public void enqueue(URLSS URLSS) {
            Nodo nuevo = new Nodo(URLSS);
            if (isEmpty()) {
                inicio = nuevo;
            } else {
                fin.siguiente = nuevo;
            }
            fin = nuevo;
        }


    @Override
        public void dequeue() {
            if (!isEmpty()) {
                Nodo aux = inicio;
                inicio = inicio.siguiente;
                if (inicio == null) {
                    fin = null;  // Si la cola queda vacía, fin también debe ser null
                }
                aux.siguiente = null;  // Desconectar nodo eliminado
            }
        }

        @Override
        public URLSS front() {
            if (isEmpty()) {
                return null;
            }
            return inicio.URLSS;
        }

        @Override
        public int size() {
            int contador = 0;
            Nodo aux = inicio;
            while (aux != null) {
                contador++;
                aux = aux.siguiente;
            }
            return contador;
        }

        @Override
        public boolean isEmpty() {
            return inicio == null;
        }

        @Override
        public void clear() {
            inicio = null;
            fin = null;
        }





}
