package tads;

public class NodoLista<T extends Comparable> {
    
    private T dato;
    private NodoLista siguiente;
    
    public NodoLista(T elDato){
        this.dato = elDato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
