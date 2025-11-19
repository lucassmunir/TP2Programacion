import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEnlazada<T> implements Iterable<T> {
    private Nodo<T> nodoInicial;
    private int size;

    public ListaEnlazada() {
        this.nodoInicial = null;
        this.size = 0;
    }

    public void agregarAlFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (this.nodoInicial == null) {
            this.nodoInicial = nuevoNodo;
        } else {
            Nodo<T> actual = this.nodoInicial; 
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        this.size++;
    }

    public Object[] convertirAArray() {
        Object[] arrayResultado = new Object[this.size];
        int i = 0;
        Nodo<T> actual = this.nodoInicial;

        while (actual != null) {
            arrayResultado[i] = actual.getDato();
            i++;
            actual = actual.getSiguiente();
        }
        return arrayResultado;
    }


    public static ListaEnlazada<Object> convertirArrayALista(Object[] array) {
        ListaEnlazada<Object> nuevaLista = new ListaEnlazada<>();
        if (array != null) {
            for (Object elemento : array) {
                nuevaLista.agregarAlFinal(elemento);
            }
        }
        return nuevaLista;
    }

    // --- Getters y Iterador ---
    public Nodo<T> getHead() { return this.nodoInicial; }
    public int getSize() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }

    @Override
    public Iterator<T> iterator() {
        return new IteradorLista(this.nodoInicial);
    }

    private class IteradorLista implements Iterator<T> {
        private Nodo<T> actual;

        public IteradorLista(Nodo<T> inicio) { this.actual = inicio; }

        @Override
        public boolean hasNext() { return actual != null; }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T dato = actual.getDato();
            actual = actual.getSiguiente();
            return dato;
        }
    }
}