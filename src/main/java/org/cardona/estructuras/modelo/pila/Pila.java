package org.cardona.estructuras.modelo.pila;


import org.cardona.estructuras.modelo.URLSS;

public interface Pila {


    void push(URLSS URLSS);
    void pop();
    URLSS top();
    int size();
    boolean isEmpty();
    void clear();


}
