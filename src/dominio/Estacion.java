package dominio;

import tads.ColaNodos;
import tads.ListaNodos;

public class Estacion implements Comparable {

    private String nombre;
    private String barrio;
    private int capacidad;
    private int anclajesOcupados;
    private ColaNodos<Usuario> colaEsperaAlquiler;
    private ColaNodos<Usuario> colaEsperaAnclaje;

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getBarrio() {
        return barrio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getAnclajesOcupados() {
        return anclajesOcupados;
    }

    public ColaNodos<Usuario> getColaEsperaAlquiler() {
        return colaEsperaAlquiler;
    }

    public ColaNodos<Usuario> getColaEsperaAnclaje() {
        return colaEsperaAnclaje;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setAnclajesOcupados(int anclajesOcupados) {
        this.anclajesOcupados = anclajesOcupados;
    }

    public void setColaEsperaAlquiler(ColaNodos<Usuario> colaEsperaAlquiler) {
        this.colaEsperaAlquiler = colaEsperaAlquiler;
    }

    public void setColaEsperaAnclaje(ColaNodos<Usuario> colaEsperaAnclaje) {
        this.colaEsperaAnclaje = colaEsperaAnclaje;
    }

    public Estacion() {
    };
    
    public Estacion(String nombre) {
        this.nombre = nombre;
    }

    public Estacion(String nombre, String barrio, int capacidad) {
        this.nombre = nombre;
        this.barrio = barrio;
        this.capacidad = capacidad;
        this.anclajesOcupados = 0;
    }

    // Constructor para test
        public Estacion(String nombre, String barrio, int capacidad, int anclajesOcupados) {
        this.nombre = nombre;
        this.barrio = barrio;
        this.capacidad = capacidad;
        this.anclajesOcupados = anclajesOcupados;
    }
        
    // Metodos
    @Override
    public String toString() {
        return "Estacion:" + nombre
                + ", barrio: " + barrio
                + ", capacidad: " + capacidad
                + ", anclajes ocupados: " + anclajesOcupados;
    }

    @Override
    public int compareTo(Object o) {
        Estacion e2 = (Estacion) o;
        return this.nombre.compareTo(e2.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        Estacion e2 = (Estacion) obj;
        return this.nombre.equals(e2.nombre);
    }
}
