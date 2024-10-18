package org.cardona.estructuras.modelo.listacircular;

import org.cardona.estructuras.modelo.Libro;

public class ListaCircularDoble {
    private Nodo primero;
    private Nodo ultimo;
    private int tamanio;

    public ListaCircularDoble() {
        this.primero = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public boolean isEmpty() {
        return tamanio == 0;
    }

    public int size() {
        return tamanio;
    }



    public void insertarInicio(Libro libro) {
        Nodo nuevo = new Nodo(libro);
        if (isEmpty()) {
            primero = nuevo;
            ultimo = nuevo;
            primero.setSiguiente(primero);
            primero.setAnterior(ultimo);
        } else {
            nuevo.setSiguiente(primero);
            nuevo.setAnterior(ultimo);
            primero.setAnterior(nuevo);
            ultimo.setSiguiente(nuevo);
            primero = nuevo;
        }
        tamanio++;
    }



    public void insertarFinal(Libro libro) {
        Nodo nuevo = new Nodo(libro);
        if (isEmpty()) {
            insertarInicio(libro);
        } else {
            nuevo.setSiguiente(primero);
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
            primero.setAnterior(nuevo);
            ultimo = nuevo;
            tamanio++;
        }
    }

    public void insertarEnPosicion(int posicion, Libro libro) {
        if (posicion < 0 || posicion > tamanio) {
            throw new IndexOutOfBoundsException("Posición no válida");
        }
        if (posicion == 0) {
            insertarInicio(libro);
        } else if (posicion == tamanio) {
            insertarFinal(libro);
        } else {
            Nodo nuevo = new Nodo(libro);
            Nodo actual = primero;
            for (int i = 0; i < posicion - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            nuevo.setAnterior(actual);
            actual.getSiguiente().setAnterior(nuevo);
            actual.setSiguiente(nuevo);
            tamanio++;
        }
    }

    public Libro quitarInicio() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vacía");
        }
        Libro dato = primero.getDato();
        if (tamanio == 1) {
            primero = null;
            ultimo = null;
        } else {
            primero = primero.getSiguiente();
            primero.setAnterior(ultimo);
            ultimo.setSiguiente(primero);
        }
        tamanio--;
        return dato;
    }

    public Libro quitarFinal() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vacía");
        }
        Libro dato = ultimo.getDato();
        if (tamanio == 1) {
            quitarInicio();
        } else {
            ultimo = ultimo.getAnterior();
            ultimo.setSiguiente(primero);
            primero.setAnterior(ultimo);
            tamanio--;
        }
        return dato;
    }

    public Libro quitarEnPosicion(int posicion) {
        if (isEmpty() || posicion < 0 || posicion >= tamanio) {
            throw new IndexOutOfBoundsException("Posición no válida o lista vacía");
        }
        if (posicion == 0) {
            return quitarInicio();
        } else if (posicion == tamanio - 1) {
            return quitarFinal();
        } else {
            Nodo actual = primero;
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            actual.getAnterior().setSiguiente(actual.getSiguiente());
            actual.getSiguiente().setAnterior(actual.getAnterior());
            tamanio--;
            return actual.getDato();
        }
    }

    public void modificarEnPosicion(int posicion, Libro libro) {
        if (posicion < 0 || posicion >= tamanio) {
            throw new IndexOutOfBoundsException("Posición no válida");
        }
        Nodo actual = primero;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        actual.setDato(libro);
    }

    public String mostrarLista() {
        if (isEmpty()) {
            return "Lista vacía"; // Retorna un mensaje si la lista está vacía
        }

        StringBuilder sb = new StringBuilder();
        // Cabecera de la tabla
        sb.append("+----+-------------------------------+-----------------------------+-----------------------------+\n");
        sb.append("| No | Título                        | Autor                       | Editorial                   |\n");
        sb.append("+----+-------------------------------+-----------------------------+-----------------------------+\n");

        Nodo actual = primero; // Comienza desde el primer nodo
        int index = 1; // Contador para los índices

        // Iterar por toda la lista circular
        do {
            // Asegurarse de que los valores se ajusten a las columnas
            sb.append(String.format("| %-2d | %-29s | %-27s | %-27s |\n",
                    index,
                    truncarTexto(actual.getDato().getTitulo(), 29),
                    truncarTexto(actual.getDato().getAutor(), 27),
                    truncarTexto(actual.getDato().getEditorial(), 27)));

            actual = actual.getSiguiente(); // Moverse al siguiente nodo
            index++; // Incrementar el índice
        } while (actual != primero); // Termina cuando vuelve al nodo inicial

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




    public void vaciarLista() {
        primero = null;
        ultimo = null;
        tamanio = 0;
    }

    // Métodos adicionales
    public Nodo primer() {
        return primero;
    }

    public Nodo siguiente(Nodo nodo) {
        return nodo.getSiguiente();
    }

    public Nodo anterior(Nodo nodo) {
        return nodo.getAnterior();
    }

    public Nodo ultimo() {
        return ultimo;
    }

   public Libro[] obtenerLibros() {
    if (primero == null) {
        return new Libro[0];
    }
    Libro[] libros = new Libro[tamanio];
    Nodo actual = primero;
    int index = 0;
    do {
        libros[index++] = actual.getDato();
        actual = actual.getSiguiente();
    } while (actual != primero);
    return libros;
}

}


