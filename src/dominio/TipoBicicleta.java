package dominio;

import java.util.Objects;

public class TipoBicicleta implements Comparable{
    
    private TipoBiciEnum nombre;
    private int cantAlquileres;

    public TipoBiciEnum getNombre() {
        return nombre;
    }

    public int getCantAlquileres() {
        return cantAlquileres;
    }

    public void setCantAlquileres(int cantAlquileres) {
        this.cantAlquileres = cantAlquileres;
    }

    public TipoBicicleta(TipoBiciEnum nombre) {
        this.nombre = nombre;
        this.cantAlquileres = 0;
    }

    @Override
    public boolean equals(Object obj) {
        TipoBicicleta other = (TipoBicicleta) obj;
        return this.nombre.equals(other.nombre);
    }

    @Override
    public int compareTo(Object o) {
        TipoBicicleta other = (TipoBicicleta) o;
        
        if(other.cantAlquileres > this.cantAlquileres) return 1;        
        if(other.cantAlquileres < this.cantAlquileres) return -1;         
        return this.nombre.compareTo(other.nombre);       
    }

    @Override
    public String toString() {
        return nombre + "#" + cantAlquileres;
    }
    
    
    
}
