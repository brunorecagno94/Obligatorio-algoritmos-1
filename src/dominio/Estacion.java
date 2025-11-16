package dominio;

import tads.ColaNodos;
import tads.ListaNodos;

public class Estacion implements Comparable {

    private String nombre;
    private String barrio;
    private int capacidad;
    private int anclajesOcupados;
    private ColaNodos<Alquiler> colaEsperaAlquiler;
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

    public ColaNodos<Alquiler> getColaEsperaAlquiler() {
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

    public void setColaEsperaAlquiler(ColaNodos<Alquiler> colaEsperaAlquiler) {
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
        this.colaEsperaAlquiler = new ColaNodos<Alquiler>(capacidad);
    }

    // Constructor para test
        public Estacion(String nombre, String barrio, int capacidad, int anclajesOcupados) {
        this.nombre = nombre;
        this.barrio = barrio;
        this.capacidad = capacidad;
        this.anclajesOcupados = anclajesOcupados;
        this.colaEsperaAlquiler = new ColaNodos<Alquiler>(capacidad);
    }
        
    // Metodos
    @Override
    public String toString() {
        return "Estacion:" + this.nombre
                + ", barrio: " + this.barrio
                + ", capacidad: " + this.capacidad
                + ", anclajes ocupados: " + this.anclajesOcupados;
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
    
    public void ponerAlquilerEnColaDeEspera(Alquiler alquiler){
        if(this.colaEsperaAlquiler.esLlena()){
            System.out.println("No hay lugar en la cola de espera, debes intentar alquilar más tarde");
        }else{
            this.colaEsperaAlquiler.encolar(alquiler);
            System.out.println("Alquiler en cola de espera");
        }       
    }
    
    public void ocuparAnclaje() {
        this.anclajesOcupados++;
    }
    
    public void liberarAnclaje() {
        this.anclajesOcupados--;
    }
}
