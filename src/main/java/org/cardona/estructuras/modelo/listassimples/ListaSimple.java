package org.cardona.estructuras.modelo.listassimples;

import org.cardona.estructuras.modelo.Libro;
import org.cardona.estructuras.modelo.listacircular.Nodo;

//Cardona De luna Efrain Guadalupe
public class ListaSimple {


    private Nodo inicio = null;
    private Nodo aux = null;
    private int size = 0;

    public void setSize(int size) {
        this.size = size;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public Nodo getAux() {
        return aux;
    }

    public void setAux(Nodo aux) {
        this.aux = aux;
    }

    public int getSize() {
        return size;
    }

    public class Nodo {

        public Libro libros;
        public Nodo siguiente = null;

        public Nodo(Libro libros) {
            this.libros = libros;
        }

    }

    public void agregar(Libro libros) {
        Nodo nodoNuevo = new Nodo(libros);
        if (inicio == null) {//Si la lista esta vacia
            inicio = nodoNuevo;
            aux = nodoNuevo;
        } else {//Si la lista no esta vacia
           /* while (aux.siguiente != null){
                aux = aux.siguiente;
            }*/
            aux.siguiente = nodoNuevo;
            aux = nodoNuevo;
        }
        size++;
    }

    public void agregarEnPosicion(int index, Libro libros){
        if (index < 0 || index > size){
            System.out.println("Indice fuera de rango");
            return;
        }
        Nodo nodo = new Nodo(libros);
        if (index == 0){
            nodo.siguiente = inicio;
            inicio = nodo;
            size++;
            return;
        }
        Nodo aux = inicio;
        int contador = 0;
        while (contador < index - 1){
            aux = aux.siguiente;
            contador++;
        }
        nodo.siguiente = aux.siguiente;
        aux.siguiente = nodo;
        size++;
    }


    public void quitarLibro() {
    if (inicio == null) {
        throw new NullPointerException("La lista esta vacia");
    }
    if (inicio.siguiente == null) { // Si solo hay un elemento en la lista
        inicio = null;
        aux = null;
    } else {
        Nodo actual = inicio;
        while (actual.siguiente.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = null;
        aux = actual;
    }
    size--;
}

    public void quitarEnPosicion(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        if (index == 0) {
            inicio = inicio.siguiente;
            size--;
            return;
        }
        Nodo aux = inicio;
        int contador = 0;
        while (contador < index - 1) {
            aux = aux.siguiente;
            contador++;
        }
        aux.siguiente = aux.siguiente.siguiente;
        size--;
    }

    public String mostrar() {
        if (inicio == null) {
            return "Lista vacía"; // Retorna un mensaje si la lista está vacía
        }

        StringBuilder sb = new StringBuilder();
        // Cabecera de la tabla
        sb.append("+----+-------------------------------+-----------------------------+-----------------------------+\n");
        sb.append("| No | Título                        | Autor                       | Editorial                   |\n");
        sb.append("+----+-------------------------------+-----------------------------+-----------------------------+\n");

        Nodo aux = inicio;
        int index = 1;

        while (aux != null) {
            // Asegurarse de que los valores se ajusten a las columnas
            sb.append(String.format("| %-2d | %-29s | %-27s | %-27s |\n",
                    index,
                    truncarTexto(aux.libros.getTitulo(), 29),
                    truncarTexto(aux.libros.getAutor(), 27),
                    truncarTexto(aux.libros.getEditorial(), 27)));

            aux = aux.siguiente;
            index++;
        }

        sb.append("+----+-------------------------------+-----------------------------+-----------------------------+\n");

        return sb.toString(); // Retorna la representación en forma de tabla
    }

    // Método auxiliar para truncar texto si es demasiado largo
    private String truncarTexto(String texto, int longitudMaxima) {
        if (texto.length() > longitudMaxima) {
            return texto.substring(0, longitudMaxima - 3) + "..."; // Agregar "..." al final si se trunca
        }
        return texto;
    }

    public boolean estaVacia() {
        if (inicio == null) {
            return true;
        }
        return false;
    }

    public void vaciar() {
        inicio = null;
        aux = null;
        size = 0;
    }


    public int size() {
        return size;
    }

}
