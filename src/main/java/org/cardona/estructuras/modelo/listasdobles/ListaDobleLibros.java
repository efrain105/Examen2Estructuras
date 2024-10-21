package org.cardona.estructuras.modelo.listasdobles;
import org.cardona.estructuras.modelo.Libro;

// Cardona De Luna Efrain Guadalupe
public class ListaDobleLibros {

    private Nodo inicio = null;
    private Nodo fin = null;
    private Nodo aux = null;
    private int size = 0;

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public Nodo getFin() {
        return fin;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
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

    public void setSize(int size) {
        this.size = size;
    }

    public Libro obtenerPorPosicion(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        Nodo aux = inicio;
        for (int j = 0; j < i; j++) {
            aux = aux.siguiente;
        }
        return aux.libro;
    }

    // Clase interna Nodo
    public class Nodo {
        public Libro libro;
        public Nodo siguiente = null;
        public Nodo anterior = null;

        public Nodo(Libro libro) {
            this.libro = libro;
        }
    }

    // 1. Insertar al inicio
    public void insertarInicio(Libro libro) {
        aux = new Nodo(libro);
        if (inicio == null) { // Lista vacía
            inicio = aux;
            fin = aux;
        } else {
            aux.siguiente = inicio;
            inicio.anterior = aux;
            inicio = aux;
        }
        size++;
    }

    // 2. Insertar al final
    public void insertarFinal(Libro libro) {
        aux = new Nodo(libro);
        if (fin == null) { // Lista vacía
            inicio = aux;
            fin = aux;
        } else {
            fin.siguiente = aux;
            aux.anterior = fin;
            fin = aux;
        }
        size++;
    }

    // 3. Insertar en posición específica
    public void insertarEnPosicion(int posicion, Libro libro) {
        if (posicion < 0 || posicion > size) {
            System.out.println("Posición fuera de rango");
            return;
        }
        if (posicion == 0) {
            insertarInicio(libro);
            return;
        }
        if (posicion == size) {
            insertarFinal(libro);
            return;
        }
        Nodo nuevoNodo = new Nodo(libro);
        Nodo aux = inicio;
        for (int i = 0; i < posicion - 1; i++) {
            aux = aux.siguiente;
        }
        nuevoNodo.siguiente = aux.siguiente;
        nuevoNodo.anterior = aux;
        aux.siguiente.anterior = nuevoNodo;
        aux.siguiente = nuevoNodo;
        size++;
    }

    // 4. Quitar al inicio
    public Libro quitarInicio() {
        if (inicio == null) {
            throw new NullPointerException("La lista está vacía");
        }
        Libro libroEliminado = inicio.libro;
        if (inicio.siguiente == null) { // Solo un elemento
            inicio = null;
            fin = null;
        } else {
            inicio = inicio.siguiente;
            inicio.anterior = null;
        }
        size--;
        return libroEliminado;
    }

    // 5. Quitar al final
    public Libro quitarFinal() {
        if (fin == null) {
            throw new NullPointerException("La lista está vacía");
        }
        Libro libroEliminado = fin.libro;
        if (fin.anterior == null) { // Solo un elemento
            inicio = null;
            fin = null;
        } else {
            fin = fin.anterior;
            fin.siguiente = null;
        }
        size--;
        return libroEliminado;
    }

    // 6. Quitar en posición específica
    public Libro quitarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        if (posicion == 0) {
            return quitarInicio();
        }
        if (posicion == size - 1) {
            return quitarFinal();
        }
        Nodo aux = inicio;
        for (int i = 0; i < posicion; i++) {
            aux = aux.siguiente;
        }
        Libro libroEliminado = aux.libro;
        aux.anterior.siguiente = aux.siguiente;
        aux.siguiente.anterior = aux.anterior;
        size--;
        return libroEliminado;
    }

    // 7. Modificar en posición específica
    public void modificarEnPosicion(int posicion, Libro libro) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        Nodo aux = inicio;
        for (int i = 0; i < posicion; i++) {
            aux = aux.siguiente;
        }
        aux.libro = libro;
    }

    // 8. Mostrar la lista
    public String mostrarLista() {
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
                    truncarTexto(aux.libro.getTitulo(), 29),
                    truncarTexto(aux.libro.getAutor(), 27),
                    truncarTexto(aux.libro.getEditorial(), 27)));

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

    // 9. Verificar si la lista está vacía
    public boolean isEmpty() {
        return inicio == null;
    }

    // 10. Tamaño de la lista
    public int size() {
        return size;
    }

    // 11. Vaciar la lista
    public void vaciarLista() {
        inicio = null;
        fin = null;
        size = 0;
    }
}
