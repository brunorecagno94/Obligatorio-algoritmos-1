package dominio;

import tads.ListaNodos;

public class Barrio implements Comparable{
    
    private String nombre;
    private ListaNodos<Estacion> estaciones;
    private int porcentajeOcupacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaNodos<Estacion> getEstaciones() {
        return estaciones;
    }
    
    public void setEstaciones(ListaNodos<Estacion> estaciones) {
        this.estaciones = estaciones;
    }
    
    public int getPorcentajeOcupacion() {
        return porcentajeOcupacion;
    }

    public void setPorcentajeOcupacion(int porcentajeOcupacion) {
        this.porcentajeOcupacion = porcentajeOcupacion;
    }

    public Barrio(String nombre) {
        this.nombre = nombre;
        this.estaciones = new ListaNodos<Estacion>();
        this.porcentajeOcupacion = 0;
    }        
    
    @Override
    public int compareTo(Object o) {
        Barrio b2 = (Barrio) o;
        return this.nombre.compareTo(b2.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        final Barrio b2 = (Barrio) obj;
        return this.nombre.equals(b2.nombre);
    }

    @Override
    public String toString() {
        return nombre + "#" + porcentajeOcupacion;
    }
    
    public void agregarEstacion(Estacion estacion){
        this.estaciones.agregarOrd(estacion);
    }
    
    public void eliminarEstacion(Estacion estacion){
        this.estaciones.borrarElemento(estacion);
    }
    
    public boolean noTieneEstaciones(){
        return estaciones.esVacia();
    }
}
