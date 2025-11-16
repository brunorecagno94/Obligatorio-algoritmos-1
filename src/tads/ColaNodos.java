package tads;

public class ColaNodos<T> implements ICola<T> {

    private NodoCola<T> frente;
    private NodoCola<T> fondo;
    private int cantMax;
    private int cantidad;

    public ColaNodos() {
        this.frente = null;
        this.fondo = null;
        this.cantMax = 50;
        this.cantidad = 0;
    }

    public NodoCola<T> getFrente() {
        return frente;
    }

    public void setFrente(NodoCola<T> frente) {
        this.frente = frente;
    }

    public NodoCola<T> getFondo() {
        return fondo;
    }

    public void setFondo(NodoCola<T> fondo) {
        this.fondo = fondo;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public boolean esLlena() {
        return cantidad == cantMax;
    }

    @Override
    public void encolar(T dato) {
        NodoCola<T> nuevo = new NodoCola<>(dato);
        if (esLlena()) {
            System.out.println("La cola está llena, no puede agregar elemento");
        } else {
            if (esVacia()) {
                frente = fondo = nuevo;
            } else {
                fondo.setSiguiente(nuevo);
                fondo = nuevo;
            }
            cantidad++;
        }
    }

    @Override
    public void desencolar() {
        if (esVacia()) {
            System.out.println("La cola está vacía, no hay elementos para borrar");
        } else {
            if (cantidad == 1) {
                frente = fondo = null;
            } else {
                frente = frente.getSiguiente();
            }
            cantidad--;
        }
    }

    @Override
    public T frente() {
        if (!esVacia()) {
            return this.frente.getDato();
        }
        return null;
    }

    @Override
    public T fondo() {
        if (!esVacia()) {
            return this.fondo.getDato();
        }
        return null;
    }

    @Override
    public int cantElementos() {
        return this.cantidad;
    }

    @Override
    public int cantMaxElementos() {
        return this.cantMax;
    }
}
