package dominio;
import tads.ListaNodos;

public class Estacion implements Comparable{
    private String nombre;
    private String barrio;
    private int capacidad;
    private int anclajesOcupados;
    private ListaNodos<Bicicleta> listaBicis;
    //private ColaNodos<Usuario> listaEsperaAlquiler;
    //private ColaNodos<Usuario> listaEsperaAnclaje;

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

    public ListaNodos<Bicicleta> getListaBicis() {
        return listaBicis;
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
        
    public void setListaBicis(ListaNodos<Bicicleta> listaBicis) {
        this.listaBicis = listaBicis;
    }
    

    public Estacion(String nombre, String barrio, int capacidad) {
        this.nombre = nombre;
        this.barrio = barrio;
        this.capacidad = capacidad;
        this.anclajesOcupados = 0;
        this.listaBicis = new ListaNodos<Bicicleta>();
    }

    // Metodos
    @Override
    public String toString() {
        return "Estacion:" + nombre + 
               ", barrio: " + barrio + 
               ", capacidad: " + capacidad + 
               ", anclajes ocupados: " + anclajesOcupados;
    }

    @Override
    public int compareTo(Object o) {
        Estacion e2 = (Estacion)o;
        return this.nombre.compareTo(e2.nombre);
    }
    
    @Override
    public boolean equals(Object obj) {
        Estacion e2 = (Estacion) obj;
        return this.nombre.equals(e2.nombre);
    }
}
