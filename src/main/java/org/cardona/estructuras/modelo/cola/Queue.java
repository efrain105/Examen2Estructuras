package org.cardona.estructuras.modelo.cola;

import org.cardona.estructuras.modelo.URLSS;

public interface Queue {

        void enqueue(URLSS URLSS);   // Agregar al final
        void dequeue();          // Sacar del principio
        URLSS front();             // Ver el primer elemento
        int size();              // Tamaño de la cola
        boolean isEmpty();       // Verificar si la cola está vacía
        void clear();            // Vaciar la cola

}
